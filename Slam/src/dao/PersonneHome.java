package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

import modele.Personne;

public class PersonneHome implements Dao<Personne> {
  /** Temps maximum de validité du lien pour confirmer son inscription */
  public static String DELAI_VALIDATION = "1 hour";

  private static String sqlValider
      = "UPDATE personne SET est_inscrite = true"
      + " WHERE email = ? AND date_inscription = ?"
      + " AND date_add(date_inscription, interval "
      + DELAI_VALIDATION + ") > NOW()";

  /** Valider l'inscription. Renvoie true si c'est ok, false sinon
   * (délai expiré ou personne pas trouvée)
   *
   * @param id de l'utilisateur dont on souhaite valider l'inscription
   * @return true or false
   * @throws SQLException
   */
  public boolean valider(String email, Timestamp date) throws SQLException {
    Connection connection = DataBase.getConnection();
    PreparedStatement pValider = connection.prepareStatement(sqlValider);
    pValider.setString(1, email);
    pValider.setTimestamp(2, date);
    int nbAffectes = pValider.executeUpdate();
    return nbAffectes == 1;
  }

  private static String sqlFindById
      = "select * from personne where id_personne = ? ;";

  /** Renvoie la personne de l'id donnée, ou rien si elle n'existe pas
   *
   * @param id de la personne que l'on souhaite trouver
   * @return la personne correspondante
   * @throws SQLException
   */
  @Override
  public Personne findById(int id) throws SQLException {
    Connection connection = DataBase.getConnection();
    PreparedStatement pFindById = connection.prepareStatement(sqlFindById);
    Personne result = null;
    pFindById.setInt(1, id);
    ResultSet res = pFindById.executeQuery();
    if (res.next()) {
      result = new Personne(res.getInt("id_personne"),
          res.getString("civilite"), res.getString("prenom"),
          res.getString("nom"), res.getString("adresse"),
          res.getString("code_postal"), res.getString("ville"),
          res.getString("telephone"), res.getString("telephone2"),
          res.getString("email"), res.getString("mot_passe"),
          res.getTimestamp("date_inscription"),
          res.getBoolean("est_inscrite")
      );
    }
    res.close();
    pFindById.close();
    return result;
  }

  private static String sqlInsert
      = "INSERT INTO personne (civilite, prenom, nom,"
      + "adresse, code_postal, ville, telephone, telephone2,"
      + "email, mot_passe, date_inscription, est_inscrite) VALUES"
      + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

  /** Insère la personne dans la table personne de la base de donnée.
   * Positionne <code>id</code> et <code>dateInscription</code> dans
   * <code>personne</code>, de même que la procédure le fait dans la
   * base : base et bean sont ainsi synchrones.
   * <br/>Requiert personne != null.
   * <br/> Utilise une transaction pour insérer et lire la date
   * générée.
   *
   * @param la nouvelle personne à creer
   * @throws SQLException
   */
  @Override
  public void insert(Personne personne) throws SQLException {
    assert personne != null;
    Connection connection = DataBase.getConnection();
    // Commencer une transaction
    connection.setAutoCommit(false);
    PreparedStatement pInsert = null;
    try {
      pInsert = connection.prepareStatement(sqlInsert,
          Statement.RETURN_GENERATED_KEYS);
      pInsert.setString(1, personne.getCivilite());
      pInsert.setString(2, personne.getPrenom());
      pInsert.setString(3, personne.getNom());
      pInsert.setString(4, personne.getAdresse());
      pInsert.setString(5, personne.getCodePostal());
      pInsert.setString(6, personne.getVille());
      pInsert.setString(7, personne.getTelephone());
      pInsert.setString(8, personne.getTelephone2());
      pInsert.setString(9, personne.getEmail());
      pInsert.setString(10, personne.getMotPasse());
      pInsert.setTimestamp(11, personne.getDateInscription());
      pInsert.setBoolean(12, personne.isEstInscrite());
      pInsert.executeUpdate();
      // Recuperer le id, mis a jour par la BD
      ResultSet rs = pInsert.getGeneratedKeys();
      if (rs.next()) {
        personne.setIdPersonne(rs.getInt(1));
        System.out.println("id : " + personne.getIdPersonne());
      }
      // Recuperer la date d'inscription, mise a jour par la BD
      // Il faut refaire une requete sql en SELECT
      String sql = "SELECT date_inscription FROM personne WHERE id_personne="
          + personne.getIdPersonne();
      Statement stmt = connection.createStatement();
      rs = stmt.executeQuery(sql);
      rs.next(); // La personne est dans la BD
      personne.setDateInscription(rs.getTimestamp("date_inscription"));
      // Valider la transaction
      connection.commit();
    }
    catch (SQLException exc) {
      // Annuler toutes les opérations de la transaction
      connection.rollback();
      // Propager l'exception car la methode echoue
      throw exc;
    } finally {
      pInsert.close();
      connection.close();
    }
  }

  /** Insère la personne via la procédure stockée
   * <code>inserer_personne</code>. Positionne <code>id</code> et
   * <code>dateInscription</code> dans <code>personne</code>, de même
   * que la procédure le fait dans la base : base et bean sont ainsi
   * synchrones.
   * <br/>Requiert personne != null
   */
  public static void insertViaProcedureMySQL(Personne personne)
      throws SQLException {
    assert personne != null;
    Connection connection = DataBase.getConnection();
    String sql
        = "CALL inserer_personne(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    CallableStatement cs = connection.prepareCall(sql);
    cs.setString(2, personne.getCivilite());
    cs.setString(3, personne.getPrenom());
    cs.setString(4, personne.getNom());
    cs.setString(5, personne.getAdresse());
    cs.setString(6, personne.getCodePostal());
    cs.setString(7, personne.getVille());
    cs.setString(8, personne.getTelephone());
    cs.setString(9, personne.getTelephone2());
    cs.setString(10, personne.getEmail());
    cs.setString(11, personne.getMotPasse());
    cs.execute();
    int id = cs.getInt(1);
    Timestamp date = cs.getTimestamp(12);
    personne.setIdPersonne(id);
    personne.setDateInscription(date);
  }

  private static String sqlUpdate = "UPDATE personne SET civilite = ?,"
      + " prenom = ?, nom = ?, adresse = ?, code_postal = ?,"
      + " ville = ?, telephone = ?, telephone2 = ?, email = ?,"
      + " mot_passe = ?, date_inscription = ?, est_inscrite = ?"
      + " WHERE id_personne = ? ";

  /** Met à jour les données d'une personne dans la table personne de
   * la base de donnée.
   *
   * @param la personne que l'on souhaite mettre à jour
   * @return true or false
   * @throws SQLException
   */
  @Override
  public boolean update(Personne personne) throws SQLException {
    Connection connection = DataBase.getConnection();
    PreparedStatement pUpdate = connection.prepareStatement(sqlUpdate);
    pUpdate.setString(1, personne.getCivilite());
    pUpdate.setString(2, personne.getPrenom());
    pUpdate.setString(3, personne.getNom());
    pUpdate.setString(4, personne.getAdresse());
    pUpdate.setString(5, personne.getCodePostal());
    pUpdate.setString(6, personne.getVille());
    pUpdate.setString(7, personne.getTelephone());
    pUpdate.setString(8, personne.getTelephone2());
    pUpdate.setString(9, personne.getEmail());
    pUpdate.setString(10, personne.getMotPasse());
    pUpdate.setTimestamp(11, personne.getDateInscription());
    pUpdate.setBoolean(12, personne.isEstInscrite());
    pUpdate.setInt(13, personne.getIdPersonne());
    pUpdate.execute();
    pUpdate.close();
    connection.close();
    return true;
  }

  private static String sqlDelete
      = "DELETE FROM personne WHERE id_personne = ?";

  /** Supprime une personne de la table personne dans la base de
   * donnée, en donnant son id.
   *
   * @param id de la personne que l'on souhaite supprimer
   * @return true or false
   * @throws SQLException
   */
  @Override
  public boolean delete(int id) throws SQLException {
    Connection connection = DataBase.getConnection();
    PreparedStatement pDelete = connection.prepareStatement(sqlDelete);
    pDelete.setInt(1, id);
    pDelete.executeQuery();
    pDelete.close();
    connection.close();
    return true;
  }

}

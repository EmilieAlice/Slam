package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import modele.Personne;

public class PersonneHome implements Dao<Personne> {
  /** Temps maximum de validité du lien pour confirmer son inscription */
  public static String DELAI_VALIDATION = "1 minute";

  private static String sqlValider
      = "UPDATE personne SET est_inscrite = true"
      + " WHERE email = ? AND date_inscription = ?"
      + " AND date_add(date_inscription, interval "
      + DELAI_VALIDATION + ") > NOW()";
  private static PreparedStatement pValider = null;

  /** Valider l'inscription. Renvoie true si c'est ok, false sinon
   * (délai expiré ou personne pas trouvée)
   *
   * @param id de l'utilisateur dont on souhaite valider l'inscription
   * @return true or false
   * @throws SQLException
   */
  public boolean valider(String email, Timestamp date) throws SQLException {
    if (pValider == null) {
      Connection connection = DataBase.getConnection();
      pValider = connection.prepareStatement(sqlValider);
    }
    pValider.setString(1, email);
    pValider.setTimestamp(2, date);
    int nbAffectes = pValider.executeUpdate();
    return nbAffectes == 1;
  }

  private static String sqlFindById
      = "select * from personne where id_personne = ? ;";
  private static PreparedStatement pFindById = null;

  /** Renvoie la personne de l'id donnée, ou rien si elle n'existe pas
   *
   * @param id de la personne que l'on souhaite trouver
   * @return la personne correspondante
   * @throws SQLException
   */
  @Override
  public Personne findById(int id) throws SQLException {
    if (pFindById == null) {
      Connection connection = DataBase.getConnection();
      pFindById = connection.prepareStatement(sqlFindById);
    }
    Personne result = null;
    pFindById.setInt(1, id);
    ResultSet res = pFindById.executeQuery();
    if (res.next()) {
      result = new Personne(res.getInt("id_personne"), res.getString("civilite"), res.getString("prenom"),
          res.getString("nom"), res.getString("adresse"), res.getString("code_postal"),
          res.getString("ville"), res.getString("telephone"), res.getString("telephone2"),
          res.getString("email"), res.getString("mot_passe"), res.getTimestamp("date_inscription"),
          res.getBoolean("est_inscrite")
      );
    }
    return result;
  }

  private static String sqlInsert
      = "INSERT INTO personne (civilite, prenom, nom,"
      + "adresse, code_postal, ville, telephone, telephone2,"
      + "email, mot_passe, date_inscription, est_inscrite) VALUES"
      + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
  private static PreparedStatement pInsert = null;

  /** Creation d'une nouvelle personne dans la table personne de la
   * base de donnée.
   *
   * @param la nouvelle personne à creer
   * @throws SQLException
   */
  @Override
  public void insert(Personne personne) throws SQLException {
    if (pInsert == null) {
      Connection connection = DataBase.getConnection();
      pInsert = connection.prepareStatement(sqlInsert);
    }
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
  }

  private static String sqlUpdate = "UPDATE personne SET civilite = ?,"
      + " prenom = ?, nom = ?, adresse = ?, code_postal = ?,"
      + " ville = ?, telephone = ?, telephone2 = ?, email = ?,"
      + " mot_passe = ?, date_inscription = ?, est_inscrite = ?"
      + " WHERE id_personne = ? ";
  private static PreparedStatement pUpdate = null;

  /** Met à jour les données d'une personne dans la table personne de
   * la base de donnée.
   *
   * @param la personne que l'on souhaite mettre à jour
   * @return true or false
   * @throws SQLException
   */
  @Override
  public boolean update(Personne personne) throws SQLException {
    if (pUpdate == null) {
      Connection connection = DataBase.getConnection();
      pUpdate = connection.prepareStatement(sqlUpdate);
    }
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
    return true;
  }

  private static String sqlDelete
      = "DELETE FROM personne WHERE id_personne = ?";
  private static PreparedStatement pDelete = null;

  /** Supprime une personne de la table personne dans la base de
   * donnée, en donnant son id.
   *
   * @param id de la personne que l'on souhaite supprimer
   * @return true or false
   * @throws SQLException
   */
  @Override
  public boolean delete(int id) throws SQLException {
    if (pUpdate == null) {
      Connection connection = DataBase.getConnection();
      pUpdate = connection.prepareStatement(sqlUpdate);
    }
    pDelete.setInt(1, id);
    pDelete.executeQuery();
    return true;
  }

}

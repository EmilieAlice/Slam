package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.sql.PreparedStatement;

import modele.Personne;

public class PersonneDAO implements Dao<Personne>  {


	public boolean valider(Personne a) throws SQLException {
		String req = "update personne set est_inscrite =true where id_personne = ?"
				+ "and date_add(date_inscription, interval 1 HOUR > now())";
		Connection db = DataBase.getConnection();
		PreparedStatement str = db.prepareStatement(req);
		str.setInt(1, a.getIdPersonne());
		str.execute(req);
		return false;
	}

	/** Renvoie la personne de id donn�, ou null si pas trouv�e */
	@Override
	public Personne findById(int id) throws SQLException {
		Personne result = null;
		String req = "select * from personne where id_personne = ?;";
		Connection db = DataBase.getConnection();
		PreparedStatement str = db.prepareStatement(req);
		str.setInt(1, id);
		ResultSet res = str.executeQuery(req);
		if (res.next()){
			result = new Personne(res.getInt("id_personne"), res.getString("civilite"), res.getString("prenom"),
					res.getString("nom"), res.getString("adresse"), res.getString("code_postal"),
					res.getString("ville"), res.getString("telephone"), res.getString("telephone2"),
					res.getString("email"), res.getString("mot_passe"), res.getTimestamp("date_inscription"),
					res.getBoolean("est_inscrite")
					);
		}
		return result;
	}

	@Override
	public void insert(Personne p) throws SQLException {
		String req = "INSERT INTO personne (id_personne, civilite, prenom, nom,"
				+ "adresse, code_postal, ville, telephone, telephone2,"
				+ "email, mot_passe, date_inscription, est_inscrite) VALUES"
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Connection db = DataBase.getConnection();
		PreparedStatement ps = db.prepareStatement(req);
		ps.setInt(1, p.getIdPersonne());
		ps.setString(2, p.getCivilite());
		ps.setString(3, p.getPrenom());
		ps.setString(4, p.getNom());
		ps.setString(5, p.getAdresse());
		ps.setString(6, p.getCodePostal());
		ps.setString(7, p.getVille());
		ps.setString(8, p.getTelephone());
		ps.setString(9,p.getTelephone2());
		ps.setString(10, p.getEmail());
		ps.setString(11, p.getMotPasse());
		ps.setTimestamp(12, p.getDateInscription());
		ps.setBoolean(13, p.isEstInscrite());
	}

	@Override
	public boolean update(Personne a) throws SQLException {
		throw new UnsupportedOperationException("pas implemente");
	}

	@Override
	public boolean delete(int id) throws SQLException {
		throw new UnsupportedOperationException("pas implemente");
	}
	

}

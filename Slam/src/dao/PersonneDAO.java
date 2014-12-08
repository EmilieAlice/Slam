package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import modele.Personne;

public class PersonneDAO implements Dao<Personne>  {


	public boolean valider(Personne a) throws SQLException {
		Statement str = DataBase.getConnection().createStatement();
		String req = "update personne set est_inscrite =true where id_personne = '"+ a.getIdPersonne() +
				"' and date_add(date_inscription, interval 1 HOUR > now())";
		str.execute(req);
		return false;
	}

	/** Renvoie la personne de id donn�, ou null si pas trouv�e */
	@Override
	public Personne findById(int id) throws SQLException {
		Personne result = null;
		Statement str = DataBase.getConnection().createStatement();
		String req = "select * from personne where id_personne =" + id ;
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
	public int insert(Personne a) throws SQLException {
		throw new UnsupportedOperationException("pas implemente");
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

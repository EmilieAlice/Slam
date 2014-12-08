package dao;

import java.sql.SQLException;
import java.sql.Statement;
import modele.Personne;

public class PersonneDAO implements Dao<Personne>  {

	public PersonneDAO(Personne a){
		super();
	}

	public boolean valider(Personne a) throws SQLException {
		Statement str = DataBase.getConnection().createStatement();
		String req = "update personne set est_inscrite =true where id_personne = '"+ a.getIdPersonne() +"' and date_add(date_inscription, interval 1 HOUR > now())";
		str.execute(req);
		return false;
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

	@Override
	public Personne findById(int id) throws SQLException {
		throw new UnsupportedOperationException("pas implemente");
	}
	
}

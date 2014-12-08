package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modele.Personne;

public class PersonneDAO extends Personne implements Dao<Personne> {

	public PersonneDAO(Personne a){
		super(a);
	}
		
	@Override
	public boolean update(Personne a) throws SQLException {
		Statement str = DataBase.getConnection().createStatement();
		String req = "insert into personne(est_inscrite) values ("+a.getEstInscrite()+"')";
		str.execute(req);
		return true;
	}

	@Override
	public boolean valider(Personne a) throws SQLException {
		Statement str = DataBase.getConnection().createStatement();
		String req = "update "+ a +"set est_inscrit ='true' where id = '"+ a.getIdPersonne() +"' and date_add(date_inscription, interval 5 minutes > now()";
		str.execute(req);
		return false;
	}
	
}

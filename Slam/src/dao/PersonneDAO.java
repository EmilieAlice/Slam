package dao;

import java.Personne;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonneDAO extends Personne implements Dao<Personne> {

	public PersonneDAO(Personne a){
		super(a);
	}
	
	
	
	@Override
	public boolean update(Personne a) throws SQLException {
		Statement str = ConnectBase.con.createStatement();
		String req = "insert into personne(id, civilite, prenom, nom, adresse, code_postale, ville, telephone, telephone2, email, mot_passse) values (0,'"+a.getCivilite() +"','"+ a.getPrenom()+"','"+ a.getNom()+"','"+ a.getAdresse()+"','"+ a.getCode_postale()+"','"+ a.getVille()+"','"+ a.getTelephone()+"','"+ a.getTelephone2()+"','"+ a.getEmail()+"','"+ a.getMot_passe()+"')";
		str.execute(req);
		return true;
	}



	@Override
	public boolean valide(int i) throws SQLException {
		
		return false;
	}
	
}

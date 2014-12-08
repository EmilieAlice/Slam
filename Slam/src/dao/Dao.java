package dao;

import dao.Personne;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Dao<T>{ // interface générique
	
	//public int insert(T a) throws SQLException;
	public boolean valide(int i) throws SQLException;
	public boolean update(T a) throws SQLException;
	//public boolean update(int id) throws SQLException;
	//public T findById(int id) throws SQLException;
	//public ArrayList<T> findAll() throws SQLException;

	
}

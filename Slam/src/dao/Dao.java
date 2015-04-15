package dao;

import java.sql.SQLException;

public interface Dao<T>{
	
	public void insert(T personne) throws SQLException;
	public boolean update(T personne) throws SQLException;
	public boolean delete(int id) throws SQLException;
	public T findById(int id) throws SQLException;
	
}

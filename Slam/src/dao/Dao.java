package dao;

import java.sql.SQLException;

public interface Dao<T>{
	
	public void insert(T a) throws SQLException;
	public boolean update(T a) throws SQLException;
	public boolean delete(int id) throws SQLException;
	public T findById(int id) throws SQLException;

	
}

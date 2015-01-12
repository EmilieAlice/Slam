package dao;

import java.sql.SQLException;

public interface Dao<T>{
	
	public void insert(T personne) throws SQLException;
	public boolean isUpdate(T personne) throws SQLException;
	public boolean isDelete(int id) throws SQLException;
	public T findById(int id) throws SQLException;

	
}

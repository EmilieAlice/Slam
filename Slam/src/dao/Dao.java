package dao;

import java.sql.SQLException;

public interface Dao<T>{
	
	public void insert(T personne) throws SQLException;
	public boolean update(T personne) throws SQLException;
	public boolean delete(int id) throws SQLException;
	public T findById(int id) throws SQLException;
	T findByLoginPassword(String login, String pwd) throws SQLException;
	boolean updatePersonneInfo(int id, String adresse, String cp, String ville, String tel, String tel2, String email) throws SQLException;
	boolean updatePersonneInfo(int id, String adresse, String cp, String ville, String tel, String tel2, String email, String mdp) throws SQLException;

	
}

package dao;

import java.util.ArrayList;

public interface Dao<T> {

	public int insere(T a);
	public boolean delete (int id);
	public T findById(int id);
	public ArrayList<T> findAll();
}

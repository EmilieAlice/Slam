package dao;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import dao.DataBase;

public class DataBaseTest {
	
	@Test
	public void testGetConnection() throws SQLException {
		Connection instance = DataBase.getConnection();
		assertNotNull(instance);
	}
}

package dao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class SessionHomeTest {

	@Test
	public void testFindNomSessionById() {
		String nomSession = new String();
		SessionDao sessionDao = new SessionDao();
		nomSession = sessionDao.recupereleNomDeLaSession(1);
		
		System.out.println(nomSession);
	}

}

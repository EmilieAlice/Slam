package dao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class SessionHomeTest {

	@Test
	public void testFindNomSessionById() {
		String nomSession = new String();
		SessionHome sessionDao = new SessionHome();
		nomSession = sessionDao.recupereleNomDeLaSession(1);
		
		System.out.println(nomSession);
	}

}

package dao;

import org.junit.Test;

public class SessionDaoTest {

	@Test
	public void testFindNomSessionById() {
		String nomSession = new String();
		SessionDao sessionDao = new SessionDao();
		nomSession = sessionDao.recupereleNomDeLaSession(1);
		
		System.out.println(nomSession);
	}

}

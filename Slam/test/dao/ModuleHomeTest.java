package dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModuleHomeTest {

	@Test
	public void testRecupereLeNombreDeModules() {
		int nbreDeModules;
		ModuleHome moduleDao = new ModuleHome();
		nbreDeModules = moduleDao.recupereLeNombreDeModules();
		
		assertEquals(3, nbreDeModules);
	}


	@Test
	public void testRecupereLesModules() {
		fail("Not yet implemented");
	}
}

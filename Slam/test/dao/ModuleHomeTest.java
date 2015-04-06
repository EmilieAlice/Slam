package dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModuleHomeTest {

	@Test
	public void testRecupereLeNombreDeModules() {
		int nbreDeModules;
		ModuleDao moduleDao = new ModuleDao();
		nbreDeModules = moduleDao.recupereLeNombreDeModules();
		
		assertEquals(3, nbreDeModules);
	}


	@Test
	public void testRecupereIdModule() {
		ModuleDao moduleDao = new ModuleDao();
		int idModule = moduleDao.recupereIdModule(4);
		
		assertEquals(1, idModule);
	}
}

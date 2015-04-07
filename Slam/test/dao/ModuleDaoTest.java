package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modele.Module;
import modele.Session;

import org.junit.Test;

public class ModuleDaoTest {

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
	
	@Test
	public void testFindModule() {
		Module test = new Module();
		ModuleDao dao = new ModuleDao();

		test = dao.findModuleByNom("SI2");

		assertEquals(1, test.getIdModule());

	}

	@Test
	public void findModuleAvecHeures() {
		ArrayList<Module> listeModuleUn = new ArrayList<Module>();
		Module moduleUn = new Module(
				1,
				"SI2",
				"Enseigner aux élèves les bases sur le fonctionnement du réseau internet",
				"Des TP et des cours", 30,
				"Les prérequis sont le module SI1 et le binaire");
		Module moduleDeux = new Module(
				2,
				"Maths",
				"Enseigner aux élèves les notions de mathématiques nécessaires en informatique",
				"Des cours théoriques et du python", 50,
				"Les prérequis sont le BAC");
		listeModuleUn.add(moduleUn);
		listeModuleUn.add(moduleDeux);

		ArrayList<Module> listeModuleDeux = new ArrayList<Module>();
		Session session = new Session();
		session.setNomSession("BTS SIO 2016");
		ModuleDao dao = new ModuleDao();
		listeModuleDeux = dao.findModuleAvecHeures(2015, session);
		System.out.println(listeModuleDeux);

		Module expected = listeModuleUn.get(1);
		Module test = listeModuleDeux.get(1);

		assertEquals(expected.getIdModule(), test.getIdModule());

	}

}

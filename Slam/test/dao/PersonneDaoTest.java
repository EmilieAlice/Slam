package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import modele.Personne;
import org.junit.Test;

public class PersonneDaoTest extends AgriotesTestCase {

  /**
   * Test de la methode valider() : On ecrit la personne attendu, et
   * on compare avec la personne que l'on va trouver grace à la
   * methode
   */
  @Test
  public void testValider() throws SQLException {
    System.out.println("\nvalider");
    Date now = new Date();
    Timestamp time = new Timestamp(now.getTime());
    Personne personne = new Personne(7, "Mr", "Pascal", "Waille",
        "12 rue d'en haut", "62270", "Rebreuviette", "03210337338",
        "0646808184", "pascal@hot.fr", "papi", time, false);
    PersonneDao personneDao = new PersonneDao();
    personneDao.insert(personne);
    boolean ok = personneDao.valider("pascal@hot.fr", time);
    assertTrue(ok);
    Personne result = personneDao.findById(7);
    assertTrue(result.isEstInscrite());
    assertTrue(personne.isEstInscrite());
  }

  /**
   * Test de la methode findById() : On ecrit la personne attendu, et
   * on compare avec la personne que l'on va trouver grace à la
   * methode
   */
  @Test
  public void testFindById() throws SQLException {
    System.out.println("\nfindById");
    Timestamp time = Timestamp.valueOf("2014-12-17 11:29:18");
    Personne expected = new Personne(2, "Mle", "Emilie", "WAILLE",
        "25 Avenue de la gare", "92000", "NANTERRE", "0956789101",
        null, "waille@hotmail.fr", "walle", time, false);
    PersonneDao dao = new PersonneDao();
    Personne result = dao.findById(2);
    System.out.println("e: " + expected);
    System.out.println("r: " + result);
    assertEquals(expected, result);

  }

  /**
   * Test de la methode insert() : On ecrit la nouvelle personne, et
   * on l'insert dans la base de donnée grace à la methode
   */
  @Test
  public void testInsert() throws SQLException {
    System.out.println("\ninsert");
    Date date = new Date();
    Timestamp time = new Timestamp(date.getTime());
		// Suprimer la precision en-dessous des secondes,
    // pas prises en compte dans le Timestamp de MySQL
    time.setNanos(0);
    Personne expected = new Personne(0, "Mr", "Pascal", "Waille",
        "12 rue d'en haut", "62270", "Rebreuviette", "03210337338",
        "0646808184", "pascal@hot.fr", "papi", time, false);
    PersonneDao personneDao = new PersonneDao();
    personneDao.insert(expected);
    assertEquals(7, expected.getIdPersonne());
    Personne result = personneDao.findById(7);
    System.out.println("e: " + expected);
    System.out.println("r: " + result);
    assertEquals(expected, result);
  }

  @Test
  public void testInsertViaProcedureMySQL() throws SQLException {
    System.out.println("\ninsertViaProcedureMySQL");
    Date date = new Date();
    Timestamp time = new Timestamp(date.getTime());
		// Suprimer la precision en-dessous des secondes,
    // pas prises en compte dans le Timestamp de MySQL
    time.setNanos(0);
    Personne expected = new Personne(0, "Mr", "Pascal", "Waille",
        "12 rue d'en haut", "62270", "Rebreuviette", "03210337338",
        "0646808184", "pascal@hot.fr", "papi", time, false);
    PersonneDao personneDao = new PersonneDao();
    PersonneDao.insertViaProcedureMySQL(expected);
    assertEquals(7, expected.getIdPersonne());
    Personne result = personneDao.findById(7);
    System.out.println("e: " + expected);
    System.out.println("r: " + result);
    assertEquals(expected, result);
  }
}

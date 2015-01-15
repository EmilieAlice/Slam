package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import org.junit.Before;

/**
 * Ancetre des classes de test d'accès à la base de données.
 * <p>
 * Il faut réinitialiser la base avant chaque méthode, pour
 * avoir une situation répétable et prédictible. D'où la
 * méthode annotée @Before, qui s'exécute avant toute méthode
 * annotée @Test.</p>
 * @author plasse
 */
public class AgriotesTestCase {
  /** Rafraichit la base en faisant appel a la procedure
   * stockee refresh_base, qui reprend un jeu d'enregistrements
   * donnes, et reinitialise les sequences utilisees
   * dans les champs auto-incrementes.
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    System.out.println("refresh_base");
    Connection connexion = DataBase.getConnection();
    CallableStatement cs = connexion.prepareCall("CALL refresh_base()");
    cs.execute();
    cs.close();
    connexion.close();
  }
}

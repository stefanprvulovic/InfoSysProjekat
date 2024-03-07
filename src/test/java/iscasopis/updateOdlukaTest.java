package iscasopis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class updateOdlukaTest {
	public static AutorServiceImpl autorService;
	public static RadServiceImpl radService;
	public static OdlukaServiceImpl odlukaService;

	@BeforeClass
	public static void startTest() {
		autorService = new AutorServiceImpl();
		radService = new RadServiceImpl();
		odlukaService = new OdlukaServiceImpl();
		System.out.println("Test je uspešno pokrenut.");
	}

	@Before
	public void testPrecondition() {
		autorService.addAutor("Hari", "Poter", 84321);
		Autor a = autorService.getAutorJMBG(84321);
		assertNotNull(a);
		radService.addRad(a.getID(), 2022, "Tema1", "Naslov1");
		Rad r = radService.getRadByNaslov("Naslov1");
		assertNotNull(r);
		odlukaService.addOdluka(88, r.getID(), "Komentar1");
		Odluka o1 = odlukaService.getOdlukaByRecRad(88, r.getID());
		assertNotNull(o1);
		odlukaService.addOdluka(99, r.getID(), "Komentar2");
		Odluka o2 = odlukaService.getOdlukaByRecRad(96, r.getID());
		assertNotNull(o2);
		System.out.println("Preduslov je ispunjen.");
	}

	@Test
	public void executeTest() {
		Rad r = radService.getRadByNaslov("Naslov1");
		odlukaService.updateOdluka(88, r.getID(), Boolean.FALSE, "Nije prošao.");
		odlukaService.updateOdluka(99, r.getID(), Boolean.TRUE, "Prošao");
		System.out.println("Test je izvršen.");
	}

	@After
	public void testPostcondition() {
		Rad r = radService.getRadByNaslov("Naslov1");
		assertFalse(r.getStatus().booleanValue());
		System.out.println("Postuslov je ispunjen.");
	}

	@AfterClass
	public static void clearTest() {
		Rad r = radService.getRadByNaslov("Naslov1");
		radService.deleteRad(r.getID());
		Autor a = autorService.getAutorJMBG(84321);
		autorService.deleteAutorJMBG(a.getJMBG());
		System.out.println("Kraj testa.");

	}

}

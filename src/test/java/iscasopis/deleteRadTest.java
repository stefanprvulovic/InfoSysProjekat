package iscasopis;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class deleteRadTest {

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
	public void testPrecondition() { // getteri u ovoj funkciji su napisani zbog automatskog generisanja ID-ja za sve entitete,
									 // te nakon dodavanja ne moze da se zna ID koji je generisan
		radService.addRad(3, 2022, "Informatika", "Racunarske mreze");
		Rad r = radService.getRadByNaslov("Racunarske mreze");
		assertNotNull(r);
		odlukaService.addOdluka(88, r.getID(), "Odlièan rad");
		Odluka o = odlukaService.getOdlukaByRecRad(88, r.getID());
		assertNotNull(o);
		System.out.println("Preduslov je ispunjen.");
	}

	@Test
	public void executeTest() {
		Rad r = radService.getRadByNaslov("Racunarske mreze");
		radService.deleteRad(r.getID());
		System.out.println("Test je izvršen.");
	}

	@After
	public void testPostcondition() {
		Rad r = radService.getRadByNaslov("Racunarske mreze");
		assertNull(r);
		System.out.println("Postuslov je ispunjen.");
	}

	@AfterClass
	public static void clearTest() {
		System.out.println("Kraj testa.");

	}
}

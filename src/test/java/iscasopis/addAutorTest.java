package iscasopis;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class addAutorTest {

	public static AutorServiceImpl service;

	@BeforeClass
	public static void startTest() {
		service = new AutorServiceImpl();
		System.out.println("Test je uspešno pokrenut.");
	}

	@Before
	public void testPrecondition() {
		Autor a = service.getAutorJMBG(123456);
		assertNull(a);
		System.out.println("Preduslov je ispunjen.");

	}

	@Test
	public void testAssertions() {
		service.addAutor("Stefan", "Prvuloviæ", 123456);
		System.out.println("Test je izvršen.");

	}

	@After
	public void testPostcondition() {
		Autor a = service.getAutorJMBG(123456);
		assertNotNull(a);
		System.out.println("Postuslov je ispunjen.");
	}

	@AfterClass
	public static void clearTest() {
		service.deleteAutorJMBG(123456);
		System.out.println("Obrisani su test podaci. Kraj testa.");

	}

}

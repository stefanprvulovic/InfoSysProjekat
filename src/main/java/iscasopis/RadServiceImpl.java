package iscasopis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RadServiceImpl implements RadService {

	@PersistenceContext(name = "ISCasopisApp")

	private EntityManager em;

	private static final String USER_AGENT = "Mozilla/5.0";

	public void addRad(Rad rad) {
		em.persist(rad);
	}

	public void addRad(int autorID, int godina, String tema, String naslov) {
		em.getTransaction().begin();
		Rad r = new Rad(autorID, godina, tema, naslov);
		em.persist(r);
		em.getTransaction().commit();

	}

	public Rad getRad(int id) {
		Rad rad = em.find(Rad.class, id);
		return rad;
	}

	public Rad getRadByNaslov(String naslov) {
		try {
			TypedQuery<Rad> query = em.createQuery("SELECT r FROM Rad r WHERE r.naslov=" + naslov, Rad.class);
			Rad r = query.getSingleResult();
			return r;
		} catch (Exception e) {
			Rad r = null;
			System.out.println("Nije pronadjen rad.");
			return r;
		}
	}

	public List<Rad> returnAll() {
		TypedQuery<Rad> query = em.createQuery("SELECT r FROM Rad r", Rad.class);
		List<Rad> spisak = query.getResultList();
		return spisak;
	}

	public void deleteRad(int id) { // funkcija je implementirana tako da se brisanjem rada brisu podaci o
									// svim odlukama vezanim za taj rad
		Rad rad = em.find(Rad.class, id);
		TypedQuery<Odluka> query = em.createQuery("SELECT o FROM Odluka o WHERE o.radID=" + id, Odluka.class);
		List<Odluka> lista = query.getResultList();
		for (Odluka e : lista) {
			em.getTransaction().begin();
			em.remove(e); // brisanje svake odluke
			em.getTransaction().commit();
		}
		em.getTransaction().begin();
		em.remove(rad); // brisanje rada
		em.getTransaction().commit();
	}

	public void deleteRadID(int id) // za xhtml stranicu
	{
		Rad rad = em.find(Rad.class, id);
		TypedQuery<Odluka> query = em.createQuery("SELECT o FROM Odluka o WHERE o.radID=" + id, Odluka.class);
		List<Odluka> lista = query.getResultList();
		for (Odluka e : lista) {
			em.remove(e); // brisanje svake odluke
		}
		em.remove(rad); // brisanje rada
	}

	public void updateStatus(int id, Boolean status) {
		Rad rad = em.find(Rad.class, id);
		em.getTransaction().begin();
		rad.setStatus(status);
		em.getTransaction().commit();

	}

	public String checkStatus(String id) throws IOException {
		System.out.println("Pokrenuta provera statusa rada.");
		Rad rad = em.find(Rad.class, Integer.valueOf(id));
		URL obj = new URL("http://192.168.99.100/?status=" + rad.getStatus());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		String outcome = "";

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			outcome = response.toString();
			System.out.println(outcome);
			return response.toString();
		} else {
			System.out.println("GET request failure");
			return outcome;
		}

	}
}

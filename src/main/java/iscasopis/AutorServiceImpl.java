package iscasopis;

import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class AutorServiceImpl implements AutorService {

	@PersistenceContext(name = "ISCasopisApp")
	private EntityManager em;

	@Override
	public void addAutor(Autor autor) {
		em.persist(autor);
	}

	public void addAutor(String ime, String prezime, int jmbg) {
		em.getTransaction().begin();
		Autor a = new Autor(ime, prezime, jmbg);
		em.persist(a);
		em.getTransaction().commit();
	}

	public String listAllAutor() {
		String lista = "";
		TypedQuery<Autor> query = em.createQuery("SELECT a FROM Autor a", Autor.class);
		List<Autor> spisak = query.getResultList();
		for (Autor autor : spisak) {
			lista = lista + "ID: " + autor.getID() + "\nIme: " + autor.getIme() + "\nPrezime: " + autor.getPrezime()
					+ "\nJMBG:" + autor.getJMBG() + "\n";
		}
		return lista;
	}

	public List<Autor> returnAll() {
		TypedQuery<Autor> query = em.createQuery("SELECT a FROM Autor a", Autor.class);
		List<Autor> spisak = query.getResultList();
		return spisak;
	}

	public void deleteAutor(int id) {
		Autor autor = em.find(Autor.class, id);
		em.getTransaction().begin();
		em.remove(autor);
		em.getTransaction().commit();

	}

	public void deleteAutorJMBG(int jmbg) {
		TypedQuery<Autor> query = em.createQuery("SELECT a FROM Autor a WHERE a.jmbg=" + jmbg, Autor.class);
		Autor a = query.getSingleResult();
		em.getTransaction().begin();
		em.remove(a);
		em.getTransaction().commit();

	}

	public Autor getAutor(int id) {
		Autor autor = em.find(Autor.class, id);
		return autor;
	}

	public Autor getAutorJMBG(int jmbg) {
		try {
			TypedQuery<Autor> query = em.createQuery("SELECT a FROM Autor a WHERE a.jmbg=" + jmbg, Autor.class);
			Autor a = query.getSingleResult();
			return a;
		} catch (Exception e) {
			Autor a = null;
			System.out.println("Nije pronadjen autor.");
			return a;
		}
	}

}

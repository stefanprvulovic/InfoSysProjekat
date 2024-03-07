package iscasopis;

import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RecenzentServiceImpl implements RecenzentService {

	@PersistenceContext(name = "ISCasopisApp")
	private EntityManager em;

	public void addRecenzent(String ime, String prezime) {
		em.getTransaction().begin();
		Recenzent r = new Recenzent(ime, prezime);
		em.persist(r);
		em.getTransaction().commit();

	}

	public void deleteRecenzent(int id) {
		Recenzent r = em.find(Recenzent.class, id);
		em.getTransaction().begin();
		em.remove(r);
		em.getTransaction().commit();

	}

	public List<Recenzent> returnAll() {
		TypedQuery<Recenzent> query = em.createQuery("SELECT r FROM Recenzent r", Recenzent.class);
		List<Recenzent> spisak = query.getResultList();
		return spisak;
	}

}

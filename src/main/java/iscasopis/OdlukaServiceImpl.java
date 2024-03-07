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
public class OdlukaServiceImpl implements OdlukaService {

	@PersistenceContext(name = "ISCasopisApp")
	private EntityManager em;

	public void addOdluka(Odluka odluka) {
		em.persist(odluka);
	}

	public void addOdluka(int recenzentID, int radID, String komentar) {
		em.getTransaction().begin();
		Odluka o = new Odluka(recenzentID, radID, komentar);
		em.persist(o);
		em.getTransaction().commit();

	}

	public void deleteOdluka(int id) {
		Odluka o = em.find(Odluka.class, id);
		em.getTransaction().begin();
		em.remove(o);
		em.getTransaction().commit();

	}

	public void updateOdluka(int recenzentID, int radID, Boolean status, String komentar) {
		int prosao = 0;
		int nije_prosao = 0;
		TypedQuery<Odluka> query = em.createQuery(
				"SELECT o FROM Odluka o WHERE o.recenzentID=" + recenzentID + "AND o.radID=" + radID, Odluka.class);
		Odluka o = query.getSingleResult();
		em.getTransaction().begin();
		o.setStatus(status);
		em.getTransaction().commit();
		em.getTransaction().begin();
		o.setKom(komentar);
		em.getTransaction().commit();
		TypedQuery<Odluka> query2 = em.createQuery("SELECT o FROM Odluka o WHERE o.radID=" + radID, Odluka.class);
		List<Odluka> lista = query2.getResultList();
		for (Odluka e : lista) {
			if (e.getStatus() == Boolean.TRUE)
				prosao++;
			else if (e.getStatus() == Boolean.FALSE)
				nije_prosao++;
		}
		if (prosao > nije_prosao) {
			em.getTransaction().begin();
			Rad r = em.find(Rad.class, radID);
			r.setStatus(Boolean.TRUE);
			em.getTransaction().commit();
		} else if (nije_prosao >= prosao) {
			em.getTransaction().begin();
			Rad r = em.find(Rad.class, radID);
			r.setStatus(Boolean.FALSE);
			em.getTransaction().commit();
		}

	}

	public void updateOdluka(Odluka odluka) // za xhtml stranicu
	{
		int prosao = 0;
		int nije_prosao = 0;
		TypedQuery<Odluka> query = em.createQuery(
				"SELECT o FROM Odluka o WHERE o.recenzentID=" + odluka.getRecID() + "AND o.radID=" + odluka.getRadID(),
				Odluka.class);
		Odluka o = query.getSingleResult();
		o.setStatus(odluka.getStatus());
		o.setKom(odluka.getKom());
		TypedQuery<Odluka> query2 = em.createQuery("SELECT o FROM Odluka o WHERE o.radID=" + odluka.getRadID(),
				Odluka.class);
		List<Odluka> lista = query2.getResultList();
		for (Odluka e : lista) {
			if (e.getStatus() == Boolean.TRUE)
				prosao++;
			else if (e.getStatus() == Boolean.FALSE)
				nije_prosao++;
		}
		if (prosao > nije_prosao) {
			Rad r = em.find(Rad.class, odluka.getRadID());
			r.setStatus(Boolean.TRUE);
		} else if (nije_prosao >= prosao) {
			Rad r = em.find(Rad.class, odluka.getRadID());
			r.setStatus(Boolean.FALSE);
		}

	}

	public List<Odluka> returnAll() {
		TypedQuery<Odluka> query = em.createQuery("SELECT o FROM Odluka o", Odluka.class);
		List<Odluka> spisak = query.getResultList();
		return spisak;
	}

	public Odluka getOdluka(int id) {
		Odluka odluka = em.find(Odluka.class, id);
		return odluka;
	}

	public Odluka getOdlukaByRecRad(int recenzentID, int radID) {
		TypedQuery<Odluka> query = em.createQuery(
				"SELECT o FROM Odluka o WHERE o.radID=" + radID + "AND o.recenzentID=" + recenzentID, Odluka.class);
		Odluka o = query.getSingleResult();
		return o;
	}

}

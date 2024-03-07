package iscasopis;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;

@ManagedBean(name = "recenzentController")
public class RecenzentController {

	private Recenzent rec = new Recenzent();

	@EJB
	private RecenzentService service;

	public Recenzent getRec() {
		return rec;
	}

	public void setRec(Recenzent rec) {
		this.rec = rec;
	}

	public void addRecenzent(String ime, String prezime) {
		service.addRecenzent(ime, prezime);
	}

	public void deleteRecenzent(int id) {
		service.deleteRecenzent(id);
	}

	public List<Recenzent> returnAll() {
		List<Recenzent> lista = service.returnAll();
		return lista;
	}

}

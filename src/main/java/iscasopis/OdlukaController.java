package iscasopis;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;

@ManagedBean(name = "odlukaController")
public class OdlukaController {

	private Odluka odluka = new Odluka();

	@EJB
	private OdlukaService service;

	public Odluka getOdluka() {
		return odluka;
	}

	public void setOdluka(Odluka odluka) {
		this.odluka = odluka;
	}

	public void addOdluka(Odluka odluka) {
		service.addOdluka(odluka);
	}

	public void deleteOdluka(int id) {
		service.deleteOdluka(id);
	}

	public void updateOdluka(Odluka odluka) {
		service.updateOdluka(odluka);
	}

	public List<Odluka> returnAll() {
		List<Odluka> lista = service.returnAll();
		return lista;
	}
}

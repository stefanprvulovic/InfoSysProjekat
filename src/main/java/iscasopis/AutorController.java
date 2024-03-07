package iscasopis;

import javax.ejb.EJB;

import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "autorController")
public class AutorController {
	private Autor autor = new Autor();

	@EJB
	private AutorService service;

	public void addAutor(Autor autor) {
		service.addAutor(autor);
	}

	public Autor getAutor() {
		return this.autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public void deleteAutor(Autor autor) {
		service.deleteAutor(autor.getID());
	}

	public List<Autor> returnAll() {
		List<Autor> lista = service.returnAll();
		return lista;
	}

	public AutorService getService() {
		return service;
	}

	public void setService(AutorService service) {
		this.service = service;
	}

}

package iscasopis;

import javax.faces.bean.ManagedBean;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;

@ManagedBean(name = "radController")
public class RadController {
	private Rad rad = new Rad();
	private int id = 0;
	private String checkID = "";

	@EJB
	private RadService service;

	public Rad getRad() {
		return rad;
	}

	public void setRad(Rad rad) {
		this.rad = rad;
	}

	public int getID()

	{
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getCheckID()

	{
		return checkID;
	}

	public void setCheckID(String checkID) {
		this.checkID = checkID;
	}

	public void addRad(Rad rad) {
		service.addRad(rad);
	}

	public Rad getRad(int id) {
		return service.getRad(rad.getID());
	}

	public void deleteRad(int id) {
		service.deleteRadID(id);
	}

	public void updateStatus(int id, Boolean status) {
		service.updateStatus(id, status);
	}

	public List<Rad> returnAll() {
		List<Rad> lista = service.returnAll();
		return lista;
	}

	public void checkStatus(String id) {
		try {
			service.checkStatus(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

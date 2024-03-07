package iscasopis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "recenzent")
public class Recenzent {

	@TableGenerator(name = "rec_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "rec_gen")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "rec_gen")
	@Column(name = "ID")
	private int id;

	@Column(name = "Ime")
	private String ime;

	@Column(name = "Prezime")
	private String prezime;

	public Recenzent() {

	}

	public Recenzent(String ime, String prezime) {
		this.setIme(ime);
		this.setPrezime(prezime);
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

}

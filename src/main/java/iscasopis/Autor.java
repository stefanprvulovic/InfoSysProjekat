package iscasopis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "autor")
public class Autor {

	@TableGenerator(name = "autor_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "autor_gen")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "autor_gen")
	@Column(name = "ID")
	private int id;

	@Column(name = "Ime")
	private String ime;

	@Column(name = "Prezime")
	private String prezime;

	@Column(name = "JMBG")
	private int jmbg;

	public Autor() {

	}

	public Autor(String ime, String prezime, int jmbg) {
		this.setIme(ime);
		this.setPrezime(prezime);
		this.setJMBG(jmbg);
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

	public int getJMBG() {
		return jmbg;
	}

	public void setJMBG(int jmbg) {
		this.jmbg = jmbg;
	}

}

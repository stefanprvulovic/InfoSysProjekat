package iscasopis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "rad")
public class Rad {

	@TableGenerator(name = "rad_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "rad_gen")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "rad_gen")
	@Column(name = "ID")
	private int id;

	@Column(name = "AutorID")
	private int autorID;

	@Column(name = "Godina")
	private int godina;

	@Column(name = "`Tema rada`")
	private String tema;

	@Column(name = "Naslov")
	private String naslov;

	@Column(name = "Status")
	private Boolean status = null; // 0-nije prošao, 1-prošao

	public Rad() {

	}

	public Rad(int autorID, int godina, String tema, String naslov) {
		this.setAutorID(autorID);
		this.setGodina(godina);
		this.setTema(tema);
		this.setNaslov(naslov);
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getAutorID() {
		return autorID;
	}

	public void setAutorID(int autorID) {
		this.autorID = autorID;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}

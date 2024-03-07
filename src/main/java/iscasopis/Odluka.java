package iscasopis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "odluka")
public class Odluka {

	@TableGenerator(name = "odluka_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "odluka_gen")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "odluka_gen")
	@Column(name = "ID")
	private int id;

	@Column(name = "RecenzentID")
	private int recenzentID;

	@Column(name = "RadID")
	private int radID;

	@Column(name = "Status")
	private Boolean status = null; // 0-nije prošao, 1-prošao

	@Column(name = "Komentar")
	private String komentar;

	public Odluka() {

	}

	public Odluka(int recenzentID, int radID, String komentar) {
		this.setRecID(recenzentID);
		this.setRadID(radID);
		this.setKom(komentar);

	}

	public Odluka(int ID, int recenzentID, int radID, String komentar) {
		this.setID(ID);
		this.setRecID(recenzentID);
		this.setRadID(radID);
		this.setKom(komentar);
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getRecID() {
		return recenzentID;
	}

	public void setRecID(int recenzentID) {
		this.recenzentID = recenzentID;
	}

	public int getRadID() {
		return radID;
	}

	public void setRadID(int radID) {
		this.radID = radID;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getKom() {
		return komentar;
	}

	public void setKom(String komentar) {
		this.komentar = komentar;
	}

}

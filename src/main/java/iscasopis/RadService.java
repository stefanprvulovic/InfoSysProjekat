package iscasopis;

import java.io.IOException;
import java.util.List;

public interface RadService {
	public void addRad(Rad rad);

	public void addRad(int autorID, int godina, String tema, String naslov);

	public Rad getRad(int id);

	public Rad getRadByNaslov(String ime);

	public List<Rad> returnAll();

	public void deleteRadID(int id);

	public void deleteRad(int id); // brisanjem rada, brisu se iz baze i odluke vezane za taj rad.

	public void updateStatus(int id, Boolean status);

	public String checkStatus(String id) throws IOException;
}

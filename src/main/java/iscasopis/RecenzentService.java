package iscasopis;

import java.util.List;

public interface RecenzentService {
	public void addRecenzent(String ime, String prezime);

	public void deleteRecenzent(int id);

	public List<Recenzent> returnAll();
}

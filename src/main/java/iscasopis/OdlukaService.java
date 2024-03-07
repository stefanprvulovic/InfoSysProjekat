package iscasopis;

import java.util.List;

public interface OdlukaService {
	public void addOdluka(Odluka odluka);

	public void addOdluka(int recenzentID, int radID, String komentar);

	public void deleteOdluka(int id);

	public void updateOdluka(int recenzentID, int radID, Boolean status, String komentar);

	public void updateOdluka(Odluka odluka);

	public Odluka getOdluka(int id);

	public Odluka getOdlukaByRecRad(int recenzentID, int radID);

	public List<Odluka> returnAll();

}

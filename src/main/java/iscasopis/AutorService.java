package iscasopis;

import java.util.List;

public interface AutorService {

	public void addAutor(Autor autor);

	public void addAutor(String ime, String prezime, int jmbg);

	public String listAllAutor();

	public List<Autor> returnAll();

	public void deleteAutor(int id);

	public Autor getAutor(int id);

	public Autor getAutorJMBG(int jmbg);

}

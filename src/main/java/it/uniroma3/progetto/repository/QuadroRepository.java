package it.uniroma3.progetto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.progetto.model.Autore;
import it.uniroma3.progetto.model.Quadro;



public interface QuadroRepository extends CrudRepository<Quadro, Long> {

    Quadro findByTitolo(String title);

	List<Quadro> findByTecnica(String tecnica);
	List<Quadro> findByAutore(Autore autore);
	List<Quadro> findAll();
	public void delete(Quadro quadro);

    
}
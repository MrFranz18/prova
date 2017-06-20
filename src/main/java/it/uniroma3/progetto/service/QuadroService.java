package it.uniroma3.progetto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.progetto.model.Autore;
import it.uniroma3.progetto.model.Quadro;
import it.uniroma3.progetto.repository.QuadroRepository;


@Service
public class QuadroService {

    @Autowired
    private QuadroRepository quadroRepository; 

    public Iterable<Quadro> findAll() {
        return this.quadroRepository.findAll();
    }

    @Transactional
    public void add(final Quadro quadro) {
        this.quadroRepository.save(quadro);
    }

	public Quadro findbyId(Long id) {
		return this.quadroRepository.findOne(id);
	}
	@Transactional
	public void delete(Quadro quadro){
		this.quadroRepository.delete(quadro);
	}
	public Quadro getQuadrobyTitolo(String titolo) {
		Quadro quadro=this.quadroRepository.findByTitolo(titolo);
		return quadro;
	}
	public List<Quadro> getQuadroByTecnica(String tecnica){
		List<Quadro> quadri = quadroRepository.findByTecnica(tecnica);
		return quadri;
	}
	public List<Quadro> getQuadriByAutore(Autore autore){
		List<Quadro> quadri = quadroRepository.findByAutore(autore);
		return quadri;
	}

}

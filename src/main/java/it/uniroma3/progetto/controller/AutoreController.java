package it.uniroma3.progetto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.progetto.model.Autore;
import it.uniroma3.progetto.model.Quadro;
import it.uniroma3.progetto.service.AutoreService;
import it.uniroma3.progetto.service.QuadroService;

@Controller
public class AutoreController {
	
	@Autowired 
	private AutoreService autoreService;
	

	@Autowired 
	private QuadroService quadroService;
	
	
	@GetMapping(value = "/")
	public String home(){
		return "redirect:/index.html";
	}

    @GetMapping("/autore")
    public String showForm(Autore autore) {
        return "formAutore";
    }
	
    @PostMapping("/autore")
    public String checkAutoreInfo(@Valid @ModelAttribute Autore autore, 
    									BindingResult bindingResult, Model model) {
    	
        if (bindingResult.hasErrors()) {
            return "formAutore";
        }
        else {
//        	if(autore.getCognome()==autoreService.findByCognome(autore.getCognome()).getCognome())
//        		return "formAutore";
//        	else{
        	
        	
        	model.addAttribute(autore);
        	try{
        	autoreService.add(autore); 
        
        	}
        	catch(Exception e){
        		return "erroreInserimentoAutore";
        	}
//        	}
        	}
        return "confermaAutore";
    }                                      
	
    @GetMapping("/cancellaAutore")
    public String showFormRimuovi(Autore autore,Model model) {
    	List<Autore> autori = (List<Autore>) autoreService.findAll(); 
		model.addAttribute("autori",autori);
        return "RimuoviAutore";
    }
    @PostMapping("/cancellaAutore")
    public String RimuoviAutore(@ModelAttribute("cognome") String cognome,BindingResult bindingResult,Model model, @RequestParam(value = "autoriEsistenti", required = false) Long autoriEsistenti){
       if (bindingResult.hasErrors()) {
            return "RimuoviAutore";
        }
       else {try{
			Autore aut= autoreService.findbyId(autoriEsistenti);
			 List<Quadro> quadri=new ArrayList<Quadro>(aut.getQuadri());
		        for(Quadro q : quadri){
		        	quadroService.delete(q);
		        }
		        model.addAttribute(aut);
		        this.autoreService.delete(aut);
		        return "confermaRimozioneAutore";
	}catch(Exception e){
		List<Autore> autori = (List<Autore>) autoreService.findAll(); 
		model.addAttribute("autori",autori);
		return "formQuadro";
	}}

}
    	      	   
    
    @GetMapping("/selezionaAutore")
	public String selezionaAutore(Model model){
		List<Autore> autori= (List<Autore>) autoreService.findAll();
		model.addAttribute("autori",autori);
		return "selezionaAutore";
	}

	@PostMapping("/modificaAutore")
	public String modificaAutore(@RequestParam(value = "autoriEsistenti") Long autoreSelezionatoID, Model model){
		Autore autore= autoreService.findbyId(autoreSelezionatoID);
		model.addAttribute("autoreSelezionato",autore);
		return "modificaAutoreForm";
	}

	@PostMapping("/confermaModifica")
	public String modificaAutore(@Valid @ModelAttribute Autore autore,Model model,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "modificaAutoreForm";
		}
		else
		{
			try{
				autoreService.add(autore);
				return "confermaAutore";
			}catch(Exception e){
				model.addAttribute("autoreSelezionato",autore);
				return "modificaAutoreForm";
			}
		}
	}

}

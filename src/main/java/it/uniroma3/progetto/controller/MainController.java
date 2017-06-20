package it.uniroma3.progetto.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import it.uniroma3.progetto.model.Quadro;
import it.uniroma3.progetto.service.QuadroService;

// controller to access the login page
@Controller
public class MainController {
	@Autowired
	QuadroService quadroService;
	// Login form
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// Login form with error
	@RequestMapping("/login-error.html")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
	
//	@RequestMapping("/logout")
//	public String logout(HttpServletRequest request, HttpServletResponse response) {
//		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//		if(auth!=null){
//			new SecurityContextLogoutHandler().logout(request,response,auth);
//		}
//		return "redirect:/login.html";
//	}
	
	//Lista di quadri
		@GetMapping(value="/listaQuadri")
		public String showForm(Model model){
			List<Quadro> quadri = (List<Quadro>) quadroService.findAll(); 
			model.addAttribute("quadri",quadri);
			return "listaQuadri";
		}
		@GetMapping(value = "/dettagli")
		public String dettagliQuadro(@ModelAttribute("id") Long id, Model model){
			Quadro quadro = quadroService.findbyId(id);
			model.addAttribute("quadro", quadro);
			model.addAttribute("testo", "Dettagli di:");
			model.addAttribute("titolo", quadro.getTitolo());
			model.addAttribute("action", "/");
			return "VisualizzaQuadro";
	}
}

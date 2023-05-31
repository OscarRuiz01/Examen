package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.patronesDTO;

import org.springframework.ui.Model;



@Controller
@RequestMapping("/Patrones")
public class controladorPatrones {
	@GetMapping("{tr}")
	public String startMethod (@PathVariable("tr") String tr, Model model) {
		String titulo = "Pagina API con Spring Boot";
		model.addAttribute("name", tr);
		model.addAttribute("titulo", titulo);
		patronesDTO consultaPatrones = patrones (tr);
		model.addAttribute("consultaPatrones", consultaPatrones);
		return "Patron";
	}
	
	public patronesDTO patrones (String numID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<patronesDTO> resp =
				restTemplate
				.getForEntity(
						String.format("https://myappfb-4718b.firebaseio.com/campeche/patrones/%s.json", numID),
						patronesDTO.class); 
		return resp.getBody();
	
	
	}
}

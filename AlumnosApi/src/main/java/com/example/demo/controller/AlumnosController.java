package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AlumnosModel;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/API/Alumnos")

public class AlumnosController {
	@GetMapping("{sw}")
	public String viewalumnos(@PathVariable("sw") String sw, Model model) {
		String titulo = "P{agina API con SprinBoot";
		model.addAttribute("name", sw);
		model.addAttribute("titulo", titulo);
		AlumnosModel Alumnos = Alumnos (sw);
		model.addAttribute("Alumnos", Alumnos);
		return "veralumnos";
	}
	
	public AlumnosModel Alumnos(String sw) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<AlumnosModel> resp =
				restTemplate.getForEntity(
						String.format("https://myappfb-4718b.firebaseio.com/campeche/alumnosTSP/%s.json", sw), AlumnosModel.class);
		//model.addAttribute("respCesp", resp);
		return resp.getBody();
	}
	
}

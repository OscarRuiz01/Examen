package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.TramitesModel;

@Controller
@RequestMapping("/API/Tramites")


public class TramitesController {
	@GetMapping("{sw}")
	public String viewtramites(@PathVariable("sw") String sw, Model model) {
		String titulo = "P{agina API con SprinBoot";
		model.addAttribute("name", sw);
		model.addAttribute("titulo", titulo);
		TramitesModel Tramites = Tramites (sw);
		model.addAttribute("Tramites", Tramites);
		return "vertramite";
	}
	
	public TramitesModel Tramites(String sw) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<TramitesModel> resp =
				restTemplate.getForEntity(
						String.format("https://myappfb-4718b.firebaseio.com/campeche/tramites/%s.json", sw), TramitesModel.class);
		//model.addAttribute("respCesp", resp);
		return resp.getBody();
	}
}

package projeto.pi.lanchonete.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import projeto.pi.lanchonete.models.Lanchonete;
import projeto.pi.lanchonete.repositories.LanchoneteRepository;

@Controller
public class LanchonetesController {
	
	@Autowired
	private LanchoneteRepository lr;
	
	@RequestMapping("/lanchonetes/form")
	public String form() {
		return "lanchonetes/formLanchonete";
	}
	
	
	@RequestMapping("/lanchonetes")
	public String adicionar(Lanchonete lanchonete) {
		
		System.out.println(lanchonete);
		lr.save(lanchonete);
		return "lanchonetes/produto-adicionado";
	}

}

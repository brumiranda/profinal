package projeto.pi.lanchonete.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import projeto.pi.lanchonete.models.Lanchonete;
import projeto.pi.lanchonete.repositories.LanchoneteRepository;

@Controller
@RequestMapping("/lanchonetes")
public class LanchonetesController {

	@Autowired
	private LanchoneteRepository lr;

	@GetMapping("/form")
	public String form() {
		return "lanchonetes/formLanchonete";
	}

	@PostMapping
	public String adicionar(Lanchonete lanchonete) {

		System.out.println(lanchonete);
		lr.save(lanchonete);
		return "lanchonetes/produto-adicionado";
	}

	@GetMapping
	public ModelAndView listar() {

		List<Lanchonete> lanchonetes = lr.findAll();
		ModelAndView mv = new ModelAndView("lanchonetes/lista");
		mv.addObject("lanchonetes", lanchonetes);
		return mv;
		
	}
}

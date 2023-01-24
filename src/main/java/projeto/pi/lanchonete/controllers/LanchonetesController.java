package projeto.pi.lanchonete.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/{id}")
	public ModelAndView detalhar (@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Lanchonete> opt = lr.findById(id);
		if(opt.isEmpty()) {
			md.setViewName("redirect:/lanchonetes");
			return md;
		}
		
		md.setViewName("lanchonetes/detalhes");
		Lanchonete lanchonete = opt.get();
		
		md.addObject("lanchonete", lanchonete);
		return md;
	}
}

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
import projeto.pi.lanchonete.models.Produto;
import projeto.pi.lanchonete.repositories.LanchoneteRepository;
import projeto.pi.lanchonete.repositories.ProdutoRepository;

@Controller
@RequestMapping("/lanchonetes")
public class LanchonetesController {

	@Autowired
	private LanchoneteRepository lr;
	@Autowired
	private ProdutoRepository pr;
	
	
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
		
		List<Produto> produtos = pr.findByLanchonete(lanchonete);
		md.addObject("produtos", produtos);
		
		return md;
	}
	
	@PostMapping("/{idProduto}")
	public String salvarAtualizacao(@PathVariable Long idProduto, Produto produto) {
		System.out.println("Id do produto: " + idProduto);
		System.out.println(produto);
		
		Optional<Lanchonete> opt = lr.findById(idProduto);
		if(opt.isEmpty()) {
			return "redirect:/lanchonetes";
		}
		
		Lanchonete lanchonete = opt.get();
		produto.setLanchonete(lanchonete);
		
		pr.save(produto);
		
		return "redirect:/lanchonetes/{idProduto}";
	}
}

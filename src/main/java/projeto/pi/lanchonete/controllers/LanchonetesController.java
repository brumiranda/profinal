package projeto.pi.lanchonete.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
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
	
	//Criamos o metódo para o formulario
	@GetMapping("/form")
	public String form(Lanchonete lanchonete) {
		return "lanchonetes/formLanchonete";
	}

	//Criamos o metódo para salvar o produto adicionado
	@PostMapping
	public String salvar(@Valid Lanchonete lanchonete, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return form(lanchonete);
		}
		System.out.println(lanchonete);
		
		
		lr.save(lanchonete);
		attributes.addFlashAttribute("mensagem", "Produto Cadastrado!");
		return "redirect:/lanchonetes";
	}

	
	
	//Criamos o metódo para o produto adicionado
	@GetMapping
	public ModelAndView listar() {

		List<Lanchonete> lanchonetes = lr.findAll();
		ModelAndView mv = new ModelAndView("lanchonetes/lista");
		mv.addObject("lanchonetes", lanchonetes);
		return mv;
	}
	
	
	//Criamos o metódo para detalhar o produto adicionado
	@GetMapping("/{id}")
	public ModelAndView detalhar (@PathVariable Long id, Produto produto) {
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
	
	
	//Criamos o metódo para atualizar os detalhes do produto aqui
	@PostMapping("/{idLanchonete}")
	public String salvarAtualizacao(@PathVariable Long idLanchonete, Produto produto ) {
		System.out.println("Id do produto: " + idLanchonete);
		System.out.println(produto);
		
		Optional<Lanchonete> opt = lr.findById(idLanchonete);
		
		if(opt.isEmpty()) {
			return "redirect:/lanchonetes";
		}
		
		Lanchonete lanchonete = opt.get();
		produto.setLanchonete(lanchonete);
		
		pr.save(produto);
		
		return "redirect:/lanchonetes/{idLanchonete}";
	}
	
	
	
	//Criamos o metódo para apagar o produto da lista de adicionados
	@GetMapping("/{id}/remover")
	public String apagarProduto(@PathVariable Long id, RedirectAttributes attributes) {
		
		Optional<Lanchonete> opt = lr.findById(id);
		if(!opt.isEmpty()) {
			
			Lanchonete lanchonete = opt.get();
			
			List<Produto> produtos = pr.findByLanchonete(lanchonete);
			
			pr.deleteAll(produtos);
			lr.delete(lanchonete);
			attributes.addFlashAttribute("mensagem", "Produto removido com sucesso!");
		}
		
		return "redirect:/lanchonetes";
	}

	
	//Criamos o metódo para editar o produto da lista de adicionados
	@GetMapping ("/{id}/editarlista")
	public ModelAndView editarLista(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Lanchonete> opt = lr.findById(id);
		
		if(opt.isEmpty()) {
			md.setViewName("redirect:/lanchonetes");
			return md;
		}
		
		Lanchonete lanchonete = opt.get();
		md.setViewName("lanchonetes/formLanchonete");
		md.addObject("lanchonete", lanchonete);
		
		return md;
		
	}
	
	
	//Criamos o metódo para editar os detalhes dos produtos atualizado
	@GetMapping("/{idLanchonete}/produtos/{idProduto}/selecionar")
	public ModelAndView selecionarProduto(@PathVariable Long idLanchonete, @PathVariable Long idProduto) {
		ModelAndView md = new  ModelAndView();
		
		Optional<Lanchonete> optLanchonete = lr.findById(idLanchonete);
		Optional<Produto> optProduto = pr.findById(idProduto);
		
		if(optProduto.isEmpty() || optLanchonete.isEmpty()) {
			
			md.setViewName("redirect:/lanchonetes");
			return md ;
		}
		
		Lanchonete lanchonete = optLanchonete.get();
		Produto produto = optProduto.get();
		
		if(lanchonete.getId() != produto.getLanchonete().getId()) {
			md.setViewName("redirect:/lanchonetes");
			return md;
		}
		
		md.setViewName("lanchonetes/detalhes");
		md.addObject("produto", produto);
		md.addObject("lanchonete", lanchonete);
		md.addObject("produtos", pr.findByLanchonete(lanchonete));
		return md;
	}
	
}

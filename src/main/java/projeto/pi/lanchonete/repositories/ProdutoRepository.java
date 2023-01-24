package projeto.pi.lanchonete.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.pi.lanchonete.models.Lanchonete;
import projeto.pi.lanchonete.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	
	List<Produto> findByLanchonete(Lanchonete lanchonete);
	
	
}

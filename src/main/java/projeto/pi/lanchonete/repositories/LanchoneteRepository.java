package projeto.pi.lanchonete.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.pi.lanchonete.models.Lanchonete;

public interface LanchoneteRepository extends JpaRepository<Lanchonete, Long> {

}

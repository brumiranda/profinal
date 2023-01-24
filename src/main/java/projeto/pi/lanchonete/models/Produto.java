package projeto.pi.lanchonete.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private String preco;
	private String quantidade;
	private String data;
	
	@ManyToOne
	private Lanchonete lanchonete;

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public String getPreco() {
		return preco;
	}



	public void setPreco(String preco) {
		this.preco = preco;
	}



	public String getQuantidade() {
		return quantidade;
	}



	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}



	public String getData() {
		return data;
	}



	public void setData(String data) {
		this.data = data;
	}



	public Lanchonete getLanchonete() {
		return lanchonete;
	}



	public void setLanchonete(Lanchonete lanchonete) {
		this.lanchonete = lanchonete;
	}



	@Override
	public String toString() {
		return "Produto [id=" + id + ", produto=" + descricao + ", preco=" + preco + ", quantidade=" + quantidade
				+ ", data=" + data + ", lanchonete=" + lanchonete + "]";
	}




	
	
	
}

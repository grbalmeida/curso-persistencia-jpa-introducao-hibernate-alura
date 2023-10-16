package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.modelo.Produto;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		Produto celular = new Produto();
		celular.setNome("Xiaomi Redmi");
		celular.setDescricao("Muito legal");
		celular.setPreco(new BigDecimal("800"));
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("loja");
		
		EntityManager em = factory.createEntityManager();
		
		// Como não está usando JTA, e o servidor não gerencia automaticamente a transação
		// tem que iniciar e commitar a transação
		em.getTransaction().begin();
		em.persist(celular);
		em.getTransaction().commit();
		em.close();
		
	}
	
}

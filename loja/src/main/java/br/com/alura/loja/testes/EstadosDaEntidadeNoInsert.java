package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class EstadosDaEntidadeNoInsert {

	public static void main(String[] args) {
		
		Categoria celulares = new Categoria("CELULARES");
		// Estado transient, após a instanciação com o new
		// Não está sendo gerenciado pela JPA
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(celulares);
		// Estado Managed
		// JPA "está de olho" na entidade
		celulares.setNome("Celulares");
		// Será commitado a string "Celulares" e não "CELULARES"
		
		em.getTransaction().commit();
		// Estado BD - salva no banco de dados - commit() e flush()
		
		em.close();
		// Estado Detached - close() e clear()
		
		celulares.setNome("CELULARES");
		// Não irá fazer um segundo update, pois o estado da entidade está como detached
	}
	
}

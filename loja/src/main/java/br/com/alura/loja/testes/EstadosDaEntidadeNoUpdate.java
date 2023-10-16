package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class EstadosDaEntidadeNoUpdate {

	public static void main(String[] args) {
		
		Categoria celulares = new Categoria("CELULARES");
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(celulares);
		celulares.setNome("Celulares");
		
		// Sincroniza com o banco de dados
		em.flush();
		em.clear();
		
		// Sai do estado Detached para Managed
		// Não muda o estado da entidade atual para Managed, e sim uma nova entidade é devolvida
		// IMPORTANTE: É necessário ter o construtor default na entidade, pois é feito um SELECT
		celulares = em.merge(celulares);
		celulares.setNome("CELULARES");
		em.flush();
	}
	
}

package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class EstadosDaEntidadeNoDelete {
	
	public static void main(String[] args) {
		
		Categoria celulares = new Categoria("CELULARES");
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(celulares);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		celulares = em.find(Categoria.class, 1L);
		em.remove(celulares);
		// Estado REMOVED
		em.getTransaction().commit();
		
		em.close();
		
		// Hibernate: insert into categorias (id, Nome) values (null, ?)
		// Hibernate: delete from categorias where id=?
		
	}

}

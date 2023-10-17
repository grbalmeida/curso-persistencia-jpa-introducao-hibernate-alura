package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class LimitandoDadosDeUmaConsulta {
	
	public static void main(String[] args) {
		
		cadastrarProdutos();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		BigDecimal precoLivroRedes = produtoDao.buscarPrecoDoProdutoComNome("Redes de Computadores");
		System.out.println(precoLivroRedes); // 214.40
		
		BigDecimal precoLivroSistemas = produtoDao.buscarPrecoDoProdutoComNome("Sistemas Operacionais");
		System.out.println(precoLivroSistemas); // 270.00
		
		em.close();
		
	}
	
	public static void cadastrarProdutos() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		Categoria categoria = new Categoria("Livros");
		
		Produto livroSistemasOperacionais = new Produto(
			"Sistemas Operacionais Modernos",
			"",
			new BigDecimal("270.00"),
			categoria
		);
		
		Produto livroRedesComputadores = new Produto(
			"Redes de Computadores",
			"",
			new BigDecimal("214.40"),
			categoria
		);
		
		em.persist(categoria);
		em.persist(livroRedesComputadores);
		em.persist(livroSistemasOperacionais);
		
		em.getTransaction().commit();
		em.close();
		
	}

}

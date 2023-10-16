package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class ConsultandoEntidades {

	public static void main(String[] args) {
		
		cadastrarProdutos();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Produto produto;
		Categoria categoria;
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		produto = produtoDao.buscarPorId(1l);
		System.out.println(produto.getNome()); // iPhone 14 Pro Max
		
		produto = produtoDao.buscarPorId(2l);
		System.out.println(produto.getNome()); // Galaxy M54 5G
		
		produto = produtoDao.buscarPorId(3l);
		System.out.println(produto.getNome()); // Redmi Note 12
		
		produto = produtoDao.buscarPorId(4l);
		System.out.println(produto.getNome()); // Galaxy S23 Ultra
		
		produto = produtoDao.buscarPorId(5l);
		System.out.println(produto); // null
		
		categoria = categoriaDao.buscarPorId(1l);
		System.out.println(categoria.getNome()); // Celulares
		
		List<Produto> produtos = produtoDao.buscarTodos();
		List<Categoria> categorias = categoriaDao.buscarTodos();
		
		System.out.println(String.format("Quantidade de produtos: %d", produtos.size())); // 4
		System.out.println(String.format("Quantidade de categorias: %d", categorias.size())); // 1
		
		System.out.println("Produtos:\n-----------------------------");
		
		for (Produto p : produtos) {
			System.out.println(p.getNome());
		}
		
		System.out.println("Categorias:\n-----------------------------");
		
		for (Categoria c : categorias) {
			System.out.println(c.getNome());
		}
		
		em.close();
		
	}
	
	public static void cadastrarProdutos() {
		
		Categoria celulares = new Categoria("Celulares");
		
		Produto iphone = new Produto("iPhone 14 Pro Max", "Melhor celular", new BigDecimal("8000"), celulares);
		Produto galaxy = new Produto("Galaxy M54 5G", "Melhor celular custo-benefício", new BigDecimal("1800"), celulares);
		Produto redmi = new Produto("Redmi Note 12", "Melhor celular barato", new BigDecimal("800"), celulares);
		Produto melhorCamera = new Produto("Galaxy S23 Ultra", "Celular com melhor câmera", new BigDecimal("6000"), celulares);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(celulares);
		em.persist(iphone);
		em.persist(galaxy);
		em.persist(redmi);
		em.persist(melhorCamera);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}

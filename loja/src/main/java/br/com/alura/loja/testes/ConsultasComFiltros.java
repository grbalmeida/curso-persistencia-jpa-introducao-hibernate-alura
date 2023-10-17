package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class ConsultasComFiltros {

	public static void main(String[] args) {
		
		cadastrarProdutos();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		List<Produto> livros = produtoDao.buscarPorNome("Microsserviço");
		
		System.out.println(livros.size()); // 1
		System.out.println(livros.get(0).getNome()); // Criando Microsserviços
		
		livros = produtoDao.buscarPorDescricao("programa");
		System.out.println(livros.size()); // 2
		System.out.println(livros.get(0).getNome()); // Programador Autodidata
		System.out.println(livros.get(1).getNome()); // Como ser um programador melhor
		
		livros = produtoDao.buscarPorNomeDaCategoria("Livros");
		System.out.println(livros.size()); // 5
		
		for (Produto p : livros) {
			System.out.println("Nome do produto: " + p.getNome());
			System.out.println("Descrição do produto: " + p.getDescricao());
			System.out.println("Data de cadastro do produto: " + p.getDataCadastro());
			System.out.println("Nome da categoria: " + p.getCategoria().getNome());
			System.out.println("-----------------------------------------");
		}
				
		em.close();
		
	}
	
	public static void cadastrarProdutos() {
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		Categoria livros = new Categoria("LIVROS");
		
		Produto livroMicrosservicos1 = new Produto(
			"Criando Microsserviços",
			"Projetando Sistemas com Componentes Menores e Mais Especializados",
			new BigDecimal("133.11"),
			livros
		);
		
		Produto livroComoSerUmProgramadorMelhor = new Produto(
			"Como ser um programador melhor",
			"um Manual Para Programadores que se Importam com Código",
			new BigDecimal("87.24"),
			livros
		);
		
		Produto livroAprendendoTypescript = new Produto(
			"Aprendendo TypeScript",
			"Melhore suas habilidades de desenvolvimento web usando JavaScript Type-Safe",
			new BigDecimal("77.60"),
			livros
		);
		
		Produto livroIntroducaoAoPentest = new Produto(
			"Introdução ao Pentest",
			"",
			new BigDecimal("88.22"),
			livros
		);
		
		Produto livroProgramadorAutodidata = new Produto(
			"Programador Autodidata",
			"Guia Definitivo Para Programar Profissionalmente",
			new BigDecimal("64.11"),
			livros
		);
		
		em.persist(livros);
		em.persist(livroProgramadorAutodidata);
		em.persist(livroIntroducaoAoPentest);
		em.persist(livroAprendendoTypescript);
		em.persist(livroComoSerUmProgramadorMelhor);
		em.persist(livroMicrosservicos1);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}

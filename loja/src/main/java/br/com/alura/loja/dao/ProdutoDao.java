package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = this.em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return this.em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class)
				.getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome LIKE CONCAT('%', :nome, '%')";
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorDescricao(String descricao) {
		String jpql = "SELECT p FROM Produto p WHERE LOWER(p.descricao) LIKE CONCAT('%', LOWER(:descricao), '%')";
		return em.createQuery(jpql, Produto.class)
				.setParameter("descricao", descricao)
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nomeCategoria) {
		String jpql = "SELECT p FROM Produto p WHERE LOWER(p.categoria.nome) LIKE CONCAT('%', LOWER(:nomeCategoria), '%')";
		return em.createQuery(jpql, Produto.class)
				.setParameter("nomeCategoria", nomeCategoria)
				.getResultList();
	}

}

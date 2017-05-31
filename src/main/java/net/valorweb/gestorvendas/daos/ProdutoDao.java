package net.valorweb.gestorvendas.daos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import net.valorweb.gestorvendas.daos.filter.ProdutoFilter;
import net.valorweb.gestorvendas.models.Categoria;
import net.valorweb.gestorvendas.models.Produto;
import net.valorweb.serveces.NegocioException;

public class ProdutoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	EntityManager manager;

	@Transactional
	public Produto salvar(Produto produto) {

		try {
			manager.merge(produto);
			//manager.persist(produto);
		} catch (PersistenceException e) {
			Throwable t = e.getCause();
			if (t.getCause().getMessage().toLowerCase().contains("duplicate entry")) {
				throw new NegocioException("SKU já cadastrado");
			}
		}

		return produto;
	}

	public List<Categoria> inicializarCategorias() {

		return manager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	public Produto buscarPorSku(String sku) {

		try {
			return manager.createQuery("FROM Produto WHERE upper(sku) = :sku", Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Produto> buscarProdutos(ProdutoFilter filtro) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = builder.createQuery(Produto.class);
		List<Predicate> predicates = new ArrayList<>();

		Root<Produto> produtoRoot = criteriaQuery.from(Produto.class);
		Fetch<Produto, Categoria> categoriaJoin = produtoRoot.fetch("categoria", JoinType.INNER);
		categoriaJoin.fetch("categoriaPai", JoinType.INNER);

		if (StringUtils.isNoneBlank(filtro.getSku())) {
			predicates.add(builder.equal(produtoRoot.get("sku"), filtro.getSku()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			predicates.add(
					builder.like(builder.lower(produtoRoot.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));

		}

		criteriaQuery.select(produtoRoot);

		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		criteriaQuery.orderBy(builder.asc(produtoRoot.get("nome")));

		TypedQuery<Produto> query = manager.createQuery(criteriaQuery);

		return query.getResultList();

	}

	public Produto buscaPorId(Long id) {

		return manager.find(Produto.class, id);

	}
	
	@Transactional
	public void excluir(Produto produto){
		
		try {
			produto = buscaPorId(produto.getId());
			
			manager.remove(produto);
			
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
			throw new NegocioException("Produto não pode ser excluído.");
			
		}
		
	}
}

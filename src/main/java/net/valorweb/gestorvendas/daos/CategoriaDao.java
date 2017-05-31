package net.valorweb.gestorvendas.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.valorweb.gestorvendas.models.Categoria;

public class CategoriaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	EntityManager manager;

	public List<Categoria> inicializarCategorias() {

		return manager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	public Categoria buscarPorId(Long id) {
		return manager.find(Categoria.class, id);
	}

	public List<Categoria> buscarRaizes() {
		return manager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();
	}

	public List<Categoria> buscarSubcategoriasDe(Categoria categoriaPai) {
		return manager.createQuery("from Categoria where categoriaPai = :raiz", Categoria.class)
				.setParameter("raiz", categoriaPai).getResultList();
	}

}

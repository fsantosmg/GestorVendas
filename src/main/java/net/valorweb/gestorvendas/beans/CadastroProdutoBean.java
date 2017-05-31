package net.valorweb.gestorvendas.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.valorweb.gestorvendas.daos.CategoriaDao;
import net.valorweb.gestorvendas.daos.ProdutoDao;
import net.valorweb.gestorvendas.models.Categoria;
import net.valorweb.gestorvendas.models.Produto;
import net.valorweb.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;

	private Categoria categoriaPai;

	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;

	@Inject
	CategoriaDao categoriaDao;

	@Inject
	ProdutoDao produtoDao;

	public CadastroProdutoBean() {
		limplar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			categoriasRaizes = categoriaDao.buscarRaizes();
			if (this.categoriaPai != null) {
				carregarSubcategorias();
			}
		}

	}

	public void carregarSubcategorias() {
		subcategorias = categoriaDao.buscarSubcategoriasDe(categoriaPai);
	}

	public void salvar() {
		this.produto = produtoDao.salvar(this.produto);
		limplar();
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}

	public void limplar() {
		this.produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produtoDao.buscaPorId(produto.getId());
		categoriaPai = this.produto.getCategoria().getCategoriaPai();
		
		System.out.println("teste");
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	public void setCategoriasRaizes(List<Categoria> categoriasRaizes) {
		this.categoriasRaizes = categoriasRaizes;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Categoria> subcategorias) {
		this.subcategorias = subcategorias;
	}
	
	public boolean isEditando(){
		return this.produto.getId() != null;
	}

}

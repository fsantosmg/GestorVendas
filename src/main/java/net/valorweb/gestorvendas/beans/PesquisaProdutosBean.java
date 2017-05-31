package net.valorweb.gestorvendas.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.valorweb.gestorvendas.daos.ProdutoDao;
import net.valorweb.gestorvendas.daos.filter.ProdutoFilter;
import net.valorweb.gestorvendas.models.Produto;
import net.valorweb.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	ProdutoFilter filtro;
	private List<Produto> produtosFiltrados;

	@Inject
	ProdutoDao produtoDao;

	private Produto produtoSelecionado;

	public PesquisaProdutosBean() {

		this.filtro = new ProdutoFilter();
	}

	public void pesquisar() {
		produtosFiltrados = produtoDao.buscarProdutos(filtro);

	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ProdutoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	public void excluir() {
		produtoDao.excluir(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado);
	
		FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku() + " excluido com sucesso.");
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
	

}

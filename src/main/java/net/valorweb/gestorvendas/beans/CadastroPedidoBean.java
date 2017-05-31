package net.valorweb.gestorvendas.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import net.valorweb.gestorvendas.models.EnderecoEntrega;
import net.valorweb.gestorvendas.models.Pedido;
import net.valorweb.serveces.NegocioException;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pedido editado;

	private Pedido pedido;
	private List<Integer> itens;

	public CadastroPedidoBean() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
		itens = new ArrayList<>();
		itens.add(1);
	}

	public void salvar() {
		throw new NegocioException("nao implementado");
	}
	public List<Integer> getItens() {
		return itens;
	}

	public Pedido getPedido() {
		return pedido;
}
}

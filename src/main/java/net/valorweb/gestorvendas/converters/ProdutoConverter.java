package net.valorweb.gestorvendas.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import net.valorweb.gestorvendas.models.Produto;

@Named
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String produtoId) {

		if (produtoId == null || produtoId.trim().isEmpty())
			return null;

		Produto produto = new Produto();

		produto.setId(Long.valueOf(produtoId));

		return produto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object produtoObject) {
		if (produtoObject != null) {
			Produto produto = (Produto) produtoObject;
			return produto.getId() == null ? null : produto.getId().toString();
		}

		return "";
	}

}

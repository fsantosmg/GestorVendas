package net.valorweb.gestorvendas.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import net.valorweb.gestorvendas.models.Categoria;
//converter nao faz busca no banco ( consumo de recurso desnecessário) em caso de problemas futuros verificar item 12.4
//@FacesConverter(forClass=Categoria.class)
@FacesConverter("categoriaConverter")
public class CategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.trim().isEmpty())
			return null;
		Categoria categoria = new Categoria();

		categoria.setId(Long.valueOf(id));

		return categoria;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object idObject) {
		if (idObject == null)
			return null;
		Categoria categoria = (Categoria) idObject;
		return categoria.getId().toString();
	}

}

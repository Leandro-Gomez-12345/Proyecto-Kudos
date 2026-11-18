package co.edu.uco.kudos.negocio.negocio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dominio.CategoriaDominio;

public interface CategoriaNegocio {

	CategoriaDominio registrarCategoria(CategoriaDominio datos);

	List<CategoriaDominio> consultarCategorias(CategoriaDominio filtro);

	CategoriaDominio cambiarNombreCategoria(UUID id, CategoriaDominio datos);

	CategoriaDominio cambiarDescripcionCategoria(UUID id, CategoriaDominio datos);

	void activarCategoria(UUID id);

	void desactivarCategoria(UUID id);
}

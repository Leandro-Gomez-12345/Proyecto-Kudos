package co.edu.uco.kudos.negocio.fachada;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dto.CategoriaDTO;

public interface CategoriaFachada {

	CategoriaDTO registrarCategoria(CategoriaDTO datos);

	List<CategoriaDTO> consultarCategorias(CategoriaDTO filtro);

	CategoriaDTO cambiarNombreCategoria(UUID id, CategoriaDTO datos);

	CategoriaDTO cambiarDescripcionCategoria(UUID id, CategoriaDTO datos);

	void activarCategoria(UUID id);

	void desactivarCategoria(UUID id);
}

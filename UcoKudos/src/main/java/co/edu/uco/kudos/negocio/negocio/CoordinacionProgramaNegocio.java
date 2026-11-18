package co.edu.uco.kudos.negocio.negocio;

import java.util.List;

import co.edu.uco.kudos.dominio.CoordinacionProgramaDominio;

public interface CoordinacionProgramaNegocio {

	CoordinacionProgramaDominio asignarCoordinadorPrograma(CoordinacionProgramaDominio datos);

	void finalizarCoordinacionPrograma(CoordinacionProgramaDominio filtro, CoordinacionProgramaDominio datos);

	List<CoordinacionProgramaDominio> consultarCoordinacionesPrograma(CoordinacionProgramaDominio filtro);
}

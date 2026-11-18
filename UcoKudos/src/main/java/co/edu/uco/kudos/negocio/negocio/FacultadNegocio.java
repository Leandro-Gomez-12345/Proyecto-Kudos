package co.edu.uco.kudos.negocio.negocio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.dominio.FacultadDominio;

public interface FacultadNegocio {

	FacultadDominio registrarFacultad(FacultadDominio datos);

	List<FacultadDominio> consultarFacultades(FacultadDominio filtro);

	FacultadDominio cambiarNombreFacultad(UUID id, FacultadDominio datos);
}

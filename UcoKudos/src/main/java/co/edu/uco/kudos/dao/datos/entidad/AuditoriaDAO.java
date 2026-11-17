package co.edu.uco.kudos.dao.datos.entidad;

import java.util.UUID;

import co.edu.uco.kudos.dao.datos.ConsultarDAO;
import co.edu.uco.kudos.dao.datos.CrearDAO;
import co.edu.uco.kudos.entidad.AuditoriaEntidad;

public interface AuditoriaDAO extends CrearDAO<AuditoriaEntidad>, ConsultarDAO<AuditoriaEntidad, UUID> {

}

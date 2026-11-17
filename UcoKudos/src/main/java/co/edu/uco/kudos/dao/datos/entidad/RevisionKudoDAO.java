package co.edu.uco.kudos.dao.datos.entidad;

import java.util.UUID;

import co.edu.uco.kudos.dao.datos.ActualizarDAO;
import co.edu.uco.kudos.dao.datos.ConsultarDAO;
import co.edu.uco.kudos.dao.datos.CrearDAO;
import co.edu.uco.kudos.entidad.RevisionKudoEntidad;

public interface RevisionKudoDAO extends CrearDAO<RevisionKudoEntidad>, ConsultarDAO<RevisionKudoEntidad, UUID>,
		ActualizarDAO<RevisionKudoEntidad, UUID> {

}

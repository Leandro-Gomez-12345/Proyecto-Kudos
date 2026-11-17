package co.edu.uco.kudos.dao.datos.entidad;

import java.util.UUID;

import co.edu.uco.kudos.dao.datos.ActualizarDAO;
import co.edu.uco.kudos.dao.datos.ConsultarDAO;
import co.edu.uco.kudos.dao.datos.CrearDAO;
import co.edu.uco.kudos.entidad.RevisionReplicaEntidad;

public interface RevisionReplicaDAO extends CrearDAO<RevisionReplicaEntidad>,
		ConsultarDAO<RevisionReplicaEntidad, UUID>, ActualizarDAO<RevisionReplicaEntidad, UUID> {

}

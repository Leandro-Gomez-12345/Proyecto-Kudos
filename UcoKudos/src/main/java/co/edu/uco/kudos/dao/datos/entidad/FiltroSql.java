package co.edu.uco.kudos.dao.datos.entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.kudos.transversal.utilitarios.UtilFecha;
import co.edu.uco.kudos.transversal.utilitarios.UtilFechaHora;
import co.edu.uco.kudos.transversal.utilitarios.UtilNumero;
import co.edu.uco.kudos.transversal.utilitarios.UtilTexto;
import co.edu.uco.kudos.transversal.utilitarios.UtilUUID;

public final class FiltroSql {

	private final StringBuilder sentencia;
	private final List<Object> parametros = new ArrayList<>();

	public FiltroSql(String sentenciaBase) {
		sentencia = new StringBuilder(sentenciaBase).append(" WHERE 1=1");
	}

	public FiltroSql conId(String columna, UUID valor) {
		if (!UtilUUID.esValorDefecto(valor)) {
			agregar(columna, valor.toString());
		}
		return this;
	}

	public FiltroSql conTexto(String columna, String valor) {
		if (!UtilTexto.esVacia(valor)) {
			agregar(columna, valor);
		}
		return this;
	}

	public FiltroSql conEntero(String columna, int valor) {
		if (valor != UtilNumero.CERO) {
			agregar(columna, valor);
		}
		return this;
	}

	public FiltroSql conBooleano(String columna, boolean valor, boolean valorDefecto) {
		if (valor != valorDefecto) {
			agregar(columna, valor);
		}
		return this;
	}

	public FiltroSql conFecha(String columna, LocalDate valor) {
		if (!UtilFecha.esValorDefecto(valor)) {
			agregar(columna, valor);
		}
		return this;
	}

	public FiltroSql conFechaHora(String columna, LocalDateTime valor) {
		if (!UtilFechaHora.esValorDefecto(valor)) {
			agregar(columna, valor);
		}
		return this;
	}

	public String getSentencia() {
		return sentencia.toString();
	}

	public List<Object> getParametros() {
		return parametros;
	}

	private void agregar(String columna, Object valor) {
		sentencia.append(" AND ").append(columna).append(" = ?");
		parametros.add(valor);
	}
}

package co.edu.uco.kudos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.uco.kudos.transversal.logger.contexto.ContextoLog;

@SpringBootApplication
public class UcoKudosApplication {

	public static void main(String[] args) {
		ContextoLog.iniciarSolicitud();
		SpringApplication.run(UcoKudosApplication.class, args);
		ContextoLog.limpiar();
	}

}

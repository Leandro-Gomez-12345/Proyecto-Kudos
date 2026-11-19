package co.edu.uco.kudos.configuracion;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import co.edu.uco.kudos.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.kudos.transversal.logger.UcoKudosLogger;
import co.edu.uco.kudos.transversal.utilitarios.UtilCorreoInstitucional;

public final class OidcUsuarioInstitucionalService extends OidcUserService {

	private static final UcoKudosLogger LOGGER = UcoKudosLogger.obtener(OidcUsuarioInstitucionalService.class);
	private static final String CODIGO_ERROR_CORREO_NO_INSTITUCIONAL = "correo_no_institucional";

	@Override
	public OidcUser loadUser(OidcUserRequest solicitud) {
		var idToken = solicitud.getIdToken();
		var correoVerificado = Boolean.TRUE.equals(idToken.getEmailVerified());

		if (!correoVerificado || !UtilCorreoInstitucional.perteneceAlDominio(idToken.getEmail())) {
			LOGGER.advertencia("Ingreso rechazado: el correo no es institucional o no está verificado por Google");
			var error = new OAuth2Error(CODIGO_ERROR_CORREO_NO_INSTITUCIONAL,
					CatalogoMensajes.Seguridad.CORREO_NO_INSTITUCIONAL, null);
			throw new OAuth2AuthenticationException(error, error.getDescription());
		}

		return super.loadUser(solicitud);
	}
}

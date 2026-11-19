package co.edu.uco.kudos.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

import co.edu.uco.kudos.transversal.utilitarios.UtilCorreoInstitucional;

@Configuration
public class SeguridadConfiguracion {

	private static final String RUTA_API = "/api/**";
	private static final String RUTA_ERROR = "/error";
	private static final String RUTA_SALIDA = "/api/v1/autenticacion/salir";
	private static final String RUTA_AUTORIZACION_GOOGLE = "/oauth2/authorization";
	private static final String PARAMETRO_DOMINIO_GOOGLE = "hd";
	private static final String PARAMETRO_PROMPT_GOOGLE = "prompt";
	private static final String ELEGIR_CUENTA_GOOGLE = "select_account";
	private static final String RUTA_INICIO_FRONTEND = "/";
	private static final String RUTA_FALLO_FRONTEND = "/login?error";

	@Bean
	SecurityFilterChain filtrosDeSeguridad(HttpSecurity http, ClientRegistrationRepository clientes,
			@Value("${ucokudos.seguridad.url-frontend}") String urlFrontend) throws Exception {
		http
			.authorizeHttpRequests(reglas -> reglas
				.requestMatchers(RUTA_ERROR).permitAll()
				.anyRequest().authenticated())
			.oauth2Login(login -> login
				.authorizationEndpoint(
						endpoint -> endpoint.authorizationRequestResolver(crearResolverDeAutorizacion(clientes)))
				.userInfoEndpoint(info -> info.oidcUserService(new OidcUsuarioInstitucionalService()))
				.defaultSuccessUrl(urlFrontend + RUTA_INICIO_FRONTEND, true)
				.failureUrl(urlFrontend + RUTA_FALLO_FRONTEND))
			.exceptionHandling(errores -> errores
				.defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
						PathPatternRequestMatcher.withDefaults().matcher(RUTA_API)))
			.logout(salida -> salida
				.logoutUrl(RUTA_SALIDA)
				.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
			.csrf(CsrfConfigurer::spa)
			.addFilterAfter(new CsrfCookieFiltro(), CsrfFilter.class);
		return http.build();
	}

	private static OAuth2AuthorizationRequestResolver crearResolverDeAutorizacion(
			ClientRegistrationRepository clientes) {
		var resolver = new DefaultOAuth2AuthorizationRequestResolver(clientes, RUTA_AUTORIZACION_GOOGLE);
		resolver.setAuthorizationRequestCustomizer(solicitud -> solicitud.additionalParameters(parametros -> {
			parametros.put(PARAMETRO_DOMINIO_GOOGLE, UtilCorreoInstitucional.DOMINIO);
			parametros.put(PARAMETRO_PROMPT_GOOGLE, ELEGIR_CUENTA_GOOGLE);
		}));
		return resolver;
	}
}

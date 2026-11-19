package co.edu.uco.kudos.configuracion;

import java.io.IOException;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class CsrfCookieFiltro extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest peticion, HttpServletResponse respuesta,
			FilterChain cadena) throws ServletException, IOException {
		var tokenCsrf = (CsrfToken) peticion.getAttribute(CsrfToken.class.getName());
		if (tokenCsrf != null) {
			tokenCsrf.getToken();
		}
		cadena.doFilter(peticion, respuesta);
	}
}

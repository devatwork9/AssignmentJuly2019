package br.com.aali.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The JWT Login Filter. This handle loggin users. <br>
 * Intercept POST request on the '/login' path and attempt to authenticate user.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.1
 *
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		AccountCredentials credentials = new ObjectMapper().readValue(request.getInputStream(),
				AccountCredentials.class);

		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
				credentials.getUsername(), credentials.getPassword(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		TokenAuthenticationService.addAuthentication(response, authentication.getName());
	}
}

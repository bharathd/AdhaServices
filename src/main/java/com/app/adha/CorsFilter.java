package com.app.adha;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * Filter to set headers for Angular requests.
 *
 */
@Component
class CorsFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		// Default implementation ignored

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;

		response.setHeader("Access-Control-Allow-Origin", "*"); //$NON-NLS-1$ //$NON-NLS-2$
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE ,PUT"); //$NON-NLS-1$//$NON-NLS-2$
		response.setHeader("Access-Control-Max-Age", "3600"); //$NON-NLS-1$ //$NON-NLS-2$
		response.setHeader("Access-Control-Allow-Headers", //$NON-NLS-1$
				"Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,userSSO"); //$NON-NLS-1$
		response.setHeader("X-FRAME-OPTIONS", "DENY");
		response.setHeader("X-Content-Type-Options", "nosniff");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {

		// Default implementation ignored
	}

}

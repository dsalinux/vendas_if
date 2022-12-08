package br.edu.iftm.vendasif.filter;

import br.edu.iftm.vendasif.entity.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danilo
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        Usuario usuario = (Usuario) servletRequest.getSession().getAttribute("usuarioLogado");
        if (redirecionar(request, usuario)) {
            servletResponse.sendRedirect("login.xhtml");
        }
        if (usuario != null && usuario.getPerfil().equals(Usuario.Perfil.VENDEDOR)) {
            String uri = servletRequest.getRequestURI();
            String contextPath = servletRequest.getContextPath();
            if (!uri.endsWith("vender.xhtml") && !uri.startsWith(contextPath + "/javax.faces.resource") && !uri.endsWith("logout")) {
                servletResponse.sendRedirect("vender.xhtml");
            }
        }
        chain.doFilter(request, response);
    }

    public boolean redirecionar(ServletRequest request, Usuario usuario) {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String uri = servletRequest.getRequestURI();
        if (uri.endsWith("login.xhtml")) {
            return false;
        }
        String contextPath = servletRequest.getContextPath();
        if (uri.startsWith(contextPath + "/javax.faces.resource")) {
            return false;
        }

        if (usuario != null) {
            return false;
        }
        return true;
    }

    @Override
    public void destroy() {
    }

}

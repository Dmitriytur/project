package ua.nure.tur.web.filters;

import ua.nure.tur.entities.Role;
import ua.nure.tur.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessFilter implements Filter {

    private static final String URL_REGEXP = "(?<=page/)\\w*";
    private Set<String> underControl;
    private Set<String> authorizedAccess;
    private Set<String> adminAccess;
    private Pattern urlPattern;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authorizedAccess = toSet(filterConfig.getInitParameter("authorizedAccess"));
        adminAccess = toSet(filterConfig.getInitParameter("adminAccess"));

        underControl = new HashSet<>();
        underControl.addAll(authorizedAccess);
        underControl.addAll(adminAccess);

        urlPattern = Pattern.compile(URL_REGEXP);
    }

    private Set<String> toSet(String parameter) {
        return new HashSet<>(Arrays.asList(parameter.split(" ")));
    }

    private boolean isNotUnderControl(String resource) {
        return !underControl.contains(resource);
    }

    private boolean accessAllowed(String resource, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return false;
        }
        return authorizedAccess.contains(resource) ||
                (user.getRole() == Role.ADMIN && adminAccess.contains(resource));

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Matcher matcher = urlPattern.matcher(request.getRequestURI());
        String resource;
        if (matcher.find()) {
            resource = matcher.group();
            if (isNotUnderControl(resource) || accessAllowed(resource, request.getSession())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ((HttpServletResponse) servletResponse).setStatus(401);
            }
        }


    }

    @Override
    public void destroy() {

    }
}

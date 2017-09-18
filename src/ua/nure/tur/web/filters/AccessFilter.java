package ua.nure.tur.web.filters;

import ua.nure.tur.entities.Role;
import ua.nure.tur.entities.User;
import ua.nure.tur.utils.Pages;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessFilter implements Filter {

    private Set<String> commonAccess;

    private Set<String> authorizedAccess;

    private Map<Role, Set<String>> accessMap;

    private Pattern urlPattern;

    private static final String URL_REGEXP = "(?<=page/)\\w*";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        commonAccess = toSet(filterConfig.getInitParameter("commonAccess"));
        authorizedAccess = toSet(filterConfig.getInitParameter("authorizedAccess"));
        accessMap = new HashMap<>();
        accessMap.put(Role.ADMIN, toSet(filterConfig.getInitParameter("adminAccess")));
        accessMap.put(Role.CLIENT, toSet(filterConfig.getInitParameter("clientAccess")));

        urlPattern = Pattern.compile(URL_REGEXP);
    }

    private Set<String> toSet(String parameter) {
        return new HashSet<>(Arrays.asList(parameter.split(" ")));
    }

    private boolean accessAllowed(String resource, HttpSession session){
        if (commonAccess.contains(resource)){
            return true;
        }

        User user = (User) session.getAttribute("user");
        if (user == null){
            return false;
        }

        return authorizedAccess.contains(resource) ||
                accessMap.get(user.getRole()).contains(resource);


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Matcher matcher = urlPattern.matcher(request.getRequestURI());
        String resource;
        if (matcher.find()){
            resource = matcher.group();
            if (accessAllowed(resource, request.getSession())){
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ((HttpServletResponse)servletResponse).setStatus(401);
            }
        }


    }

    @Override
    public void destroy() {

    }
}

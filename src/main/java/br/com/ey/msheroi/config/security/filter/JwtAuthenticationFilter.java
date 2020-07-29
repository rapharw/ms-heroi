package br.com.ey.msheroi.config.security.filter;

import br.com.ey.msheroi.config.security.enums.PerfilUsuarioEnum;
import br.com.ey.msheroi.config.security.service.TokenService;
import br.com.ey.msheroi.config.security.vo.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeaderIsInvalid(authorizationHeader)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        UsernamePasswordAuthenticationToken token = createToken(authorizationHeader);

        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    private boolean authorizationHeaderIsInvalid(String authorizationHeader) {
        return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
    }


    private UsernamePasswordAuthenticationToken createToken(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        UserPrincipal userPrincipal = tokenService.parseToken(token);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(PerfilUsuarioEnum.ROLE_COMUM.name()));

        if (userPrincipal.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority(PerfilUsuarioEnum.ROLE_ADM.name()));
        }

        return new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities);
    }
}

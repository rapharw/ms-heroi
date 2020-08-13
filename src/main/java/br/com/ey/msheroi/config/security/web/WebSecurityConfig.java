package br.com.ey.msheroi.config.security.web;

import br.com.ey.msheroi.config.security.enums.PerfilUsuarioEnum;
import br.com.ey.msheroi.config.security.filter.JwtAuthenticationFilter;
import br.com.ey.msheroi.config.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                    .headers()
                        .frameOptions().sameOrigin()
                        .httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000)
                    .and()
                        .contentSecurityPolicy("script-src 'self' 'unsafe-inline' ");

        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.OPTIONS).permitAll()
                        .antMatchers("/auth/**", "/h2-console/**").permitAll()
                        .antMatchers("/api/**", "/token/**", "/usuario/**").hasAuthority(PerfilUsuarioEnum.ROLE_COMUM.name())
                        .antMatchers("/admin/**").hasAuthority(PerfilUsuarioEnum.ROLE_ADM.name()) //rotas para criar os dominios (PODER / UNIVERSO)

                    .anyRequest().authenticated()
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);

    }
}

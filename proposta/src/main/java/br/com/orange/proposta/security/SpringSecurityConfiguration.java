package br.com.orange.proposta.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequest ->
            authorizeRequest.antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_admin")
                    .antMatchers(HttpMethod.POST, "/propostas/**").hasAuthority("SCOPE_admin")
                    .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_admin")
                    .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                    .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}

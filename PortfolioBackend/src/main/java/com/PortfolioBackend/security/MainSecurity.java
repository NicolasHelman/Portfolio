package com.PortfolioBackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.PortfolioBackend.security.jwt.JwtEntryPoint;
import com.PortfolioBackend.security.jwt.JwtTokenFilter;
import com.PortfolioBackend.security.services.UserDetailsServiceImpl;

// esta clase organiza todas las demas clases de jwt
// el token se genera al hacer login con usuario y password, a partir de ahí se comprueba la validez del token en cada request
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // indica a que metodo puede acceder solo el admin, con preauthorize
public class MainSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable() // inhabilitamos csfr (si utilizamos cookies no es recomendable)
                .authorizeRequests() // ponemos los requests
                .antMatchers("/**").permitAll() // permitimos crear un nuevo usuario y realizar un login a todo el mundo
                .antMatchers("/").permitAll()
                .antMatchers("/auth/login").permitAll()
                .anyRequest().authenticated() // para acceder al resto hay que estar autenticado
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint) // para el control de sesiones
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // politica de sesion sin estado (no utilizamos cookies)
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); // antes de cada peticion ejecutamos el jwtTokenFilter
    }
}

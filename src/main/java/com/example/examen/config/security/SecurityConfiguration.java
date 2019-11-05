package com.example.examen.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/index.html").permitAll()
			.antMatchers("/usuario").authenticated() //.hasRole("ADMIN")
			.antMatchers("/usuario/register").permitAll()
			.antMatchers("/usuario/info/**").hasRole("GERENTE")
			.antMatchers("/usuario/edit/**").hasRole("GERENTE")
			.antMatchers("/usuario/del/**").hasRole("GERENTE")
			.antMatchers("/pedido/nuevo").hasRole("IDENTIFICADOS")
			.antMatchers("/pedido/info/**").hasRole("CAMAREROS")
			.antMatchers("/pedido/edit/**").hasRole("CAMAREROS")
			.antMatchers("/detallePedido/info/**").hasRole("IDENTIFICADOS")
			.antMatchers("/detallePedido/info/**").hasRole("CAMAREROS")
			.antMatchers("/detallePedido/edit/**").hasRole("CAMAREROS")
			.antMatchers("/plato/nuevo").hasRole("GERENTE")
			.antMatchers("/plato/info/**").hasRole("GERENTE")
			.antMatchers("/plato/edit/**").hasRole("GERENTE")
			.antMatchers("/plato/del/**").hasRole("GERENTE")
			.antMatchers("/tipoPlato/nuevo").hasRole("GERENTE")
			.antMatchers("/tipoPlato/info/**").hasRole("GERENTE")
			.antMatchers("/tipoPlato/edit/**").hasRole("GERENTE")
			.antMatchers("/tipoPlato/del/**").hasRole("GERENTE")
			
		.and()
		.formLogin()
			.loginProcessingUrl("/signin")
			.loginPage("/login").permitAll()
			.usernameParameter("inputUsername")
            .passwordParameter("inputPassword")
		.and()
        .logout()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        	.logoutSuccessUrl("/")
        .and()
        .rememberMe()
        	.tokenValiditySeconds(2592000)
        	.key("Cl4v3.")
        	.rememberMeParameter("checkRememberMe")
        	.userDetailsService(usuarioDetailsService)
        .and()
            .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder( ) {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.usuarioDetailsService);

        return daoAuthenticationProvider;
    }
	
}

package net.stone.blue.codetest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import net.stone.blue.codetest.entity.AuthorityName;
import net.stone.blue.codetest.service.StudentServiceImpl;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final StudentServiceImpl studentServiceImpl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(studentServiceImpl)
			.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/students").permitAll()
			.antMatchers("/students/all").permitAll()
			.antMatchers("/students/new").hasAnyAuthority(AuthorityName.ADMIN.name())
			.antMatchers("/students/**/update").hasAnyAuthority(AuthorityName.ADMIN.name())
			.antMatchers("/students/**/delete").hasAnyAuthority(AuthorityName.ADMIN.name())
			.anyRequest()
			.authenticated()
			.and()
			.csrf()
			.disable()
			.httpBasic();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

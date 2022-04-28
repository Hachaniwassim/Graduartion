package app.igesa.config;

import app.igesa.metiers.UserDetailsServiceImpl;
import app.igesa.security.jwt.AuthEntryPointJwt;
import app.igesa.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.server.authorization.AuthorizationWebFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	@Autowired
	DataSource dataSource;


	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private static final String[] AUTH_WHITELIST = {
			"/v2/api-docs",
			"/configuration/ui",
			"/swagger-resources/**",
			"/swagger*/**",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**"
	};

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("GET","POST","DELETE","OPTION","PUT");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().csrf().disable()
				.headers()
				.frameOptions()
				.deny()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				// dont authenticate this particular request
				.authorizeRequests()
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/api/test/**").permitAll()
				.antMatchers("/**").permitAll()

				.antMatchers(AUTH_WHITELIST).permitAll()
				.anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				//remember me configuration
		         .rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400)
				.key("rem-me-key")
				.rememberMeParameter("remember-me-param")
				.rememberMeCookieName("my-remember-me");

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
@Bean
	public PersistentTokenRepository persistentTokenRepository(){
	JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	db.setDataSource(dataSource);
	return db;

	}



}
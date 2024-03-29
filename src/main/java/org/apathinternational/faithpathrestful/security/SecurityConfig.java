package org.apathinternational.faithpathrestful.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private DatabaseUserDetailsService userDetailsService;

    @Autowired
    private DatabaseUserDetailsPasswordService userDetailsPasswordsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
         
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setUserDetailsPasswordService(userDetailsPasswordsService);
        authProvider.setPasswordEncoder(passwordEncoder());
     
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
            .csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((authorize) ->
                // List all endpoints that don't need to be authenticated here
                // The other endpoints will be authorized in each method using annotations like @PreAuthorize
                authorize
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/api/userAccount/getAccountByUsername/*").permitAll()
                    .requestMatchers("/api/admin/getReferences").permitAll()
                    .requestMatchers("/api/admin/getManagement").permitAll()
                    .requestMatchers("/api/student/register").permitAll()
                    .requestMatchers("/api/volunteer/register").permitAll()
                    .anyRequest().authenticated()
			)
            .sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .exceptionHandling((exception) ->
                exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
        
        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}


}
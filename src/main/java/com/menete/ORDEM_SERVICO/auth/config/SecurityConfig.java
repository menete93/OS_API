package com.menete.ORDEM_SERVICO.auth.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Value("${jwt.public.key}")
	private RSAPublicKey publicKey;
	
	@Value("${jwt.private.key}")
	private RSAPrivateKey rsaPrivateKey;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/register").permitAll()
				.requestMatchers(HttpMethod.POST,"/login").permitAll()
				.anyRequest().authenticated())
		.csrf(csrf -> csrf.disable())
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
		.sessionManagement(Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		return http.build();
		
	}
	
	
	private SecurityScheme createAPIKeyScheme() {
	    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
	        .bearerFormat("JWT")
	        .scheme("bearer");
	}
	
	
	@Bean
	public OpenAPI openAPI() {
	    return new OpenAPI().addSecurityItem(new SecurityRequirement().
	            addList("Bearer Authentication"))
	        .components(new Components().addSecuritySchemes
	            ("Bearer Authentication", createAPIKeyScheme()))
	        .info(new Info().title("My REST API")
	            .description("Some custom description of API.")
	            .version("1.0").contact(new Contact().name("Sallo Szrajbman")
	                .email( "www.baeldung.com").url("salloszraj@gmail.com"))
	            .license(new License().name("License of API")
	                .url("API license URL")));
	}
	
	
	@Bean
	public JwtDecoder jwtDecoder() {
		
		return NimbusJwtDecoder.withPublicKey(publicKey).build();
		
	}
	@Bean
	public JwtEncoder jwtEncoder() {
		
		JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(rsaPrivateKey).build();
		
		var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
	

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    

    
   
}

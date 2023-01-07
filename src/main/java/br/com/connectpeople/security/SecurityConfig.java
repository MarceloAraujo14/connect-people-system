package br.com.connectpeople.security;

import br.com.connectpeople.user.domain.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.DefaultMapOAuth2AccessTokenResponseConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(OAuth2ClientProperties.class)
public class SecurityConfig {

    public static final String LOGIN = "/login";

    @Value("${security.admin}")
    private String pass;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
//                .cors().disable()
//                .csrf().disable()
//                .authorizeHttpRequests().anyRequest().permitAll();
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(LOGIN, "/static/**", "/css/**", "/js/**", "/img/**", "/actuator/health").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().sameOrigin())
                .oauth2Login(oauth -> oauth
                        .loginPage(LOGIN)
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .tokenEndpoint(
                                token -> {
                                    var defaultMapConverter = new DefaultMapOAuth2AccessTokenResponseConverter();
                                    Converter<Map<String, Object>, OAuth2AccessTokenResponse> linkedinMapConverter = tokenResponse -> {
                                        var withTokenType = new HashMap<>(tokenResponse);
                                        withTokenType.put(OAuth2ParameterNames.TOKEN_TYPE, OAuth2AccessToken.TokenType.BEARER.getValue());
                                        return defaultMapConverter.convert(withTokenType);
                                    };

                                    var httpConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
                                    httpConverter.setAccessTokenResponseConverter(linkedinMapConverter);

                                    var restOperations = new RestTemplate(List.of(new FormHttpMessageConverter(), httpConverter));
                                    restOperations.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
                                    var client = new DefaultAuthorizationCodeTokenResponseClient();
                                    client.setRestOperations(restOperations);

                                    token.accessTokenResponseClient(client);
                                }
                        ))
                .formLogin(login -> login
                        .loginPage(LOGIN)
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .loginProcessingUrl(LOGIN)
                        .failureForwardUrl("/login?error=true")
                        .permitAll())
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                                .logoutSuccessUrl(LOGIN).permitAll()
                );

        return http.build();
    }


    @Bean
    public UserDetailsService users(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode(pass))
                .roles(Role.ADMIN.toString())
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}

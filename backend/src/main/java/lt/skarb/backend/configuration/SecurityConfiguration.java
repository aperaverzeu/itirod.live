package lt.skarb.backend.configuration;

import lt.skarb.backend.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;

@Configuration
public class SecurityConfiguration {
    private static final String POSTS_MATCH = "/api/v1/posts/**";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String AUTH_MATCH = "/api/v1/auth/**";
    private static final String USER_ENTITY = "user";
    private static final String USERS_MATCH = "/api/v1/users/{user}/**";

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(it -> it.securityContextRepository(new WebSessionServerSecurityContextRepository()))
                .authorizeExchange(it -> it
                        // todo add new sections
                        .pathMatchers(GET, POSTS_MATCH).permitAll()
                        .pathMatchers(DELETE, POSTS_MATCH).hasRole(ADMIN_ROLE)
                        .pathMatchers(POSTS_MATCH).authenticated()
                        .pathMatchers(AUTH_MATCH).authenticated()
                        .pathMatchers(USERS_MATCH).access(this::currentUserMatchesPath)
                        .anyExchange().permitAll())
                .build();
    }

    private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication,
                                                               AuthorizationContext context) {
        return authentication
                .map(a -> context
                        .getVariables()
                        .get(USER_ENTITY)
                        .equals(a.getName()))
                .map(AuthorizationDecision::new);
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(UserRepository users) {
        return username -> users
                .findByUsername(username)
                .map(u -> User
                        .withUsername(u.getUsername())
                        .password(u.getPassword())
                        .authorities(u.getRoles().toArray(new String[0]))
                        .accountExpired(!u.isActive())
                        .credentialsExpired(!u.isActive())
                        .disabled(!u.isActive())
                        .accountLocked(!u.isActive())
                        .build());
    }
}

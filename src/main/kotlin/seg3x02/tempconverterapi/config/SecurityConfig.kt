package seg3x02.tempconverterapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable() // Disable CSRF for simplicity in testing
            .authorizeRequests()
            .anyRequest().authenticated() // All API endpoints require authentication
            .and()
            .httpBasic() // Use Basic Authentication

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance() // Simple encoder (not for production)
    }

    // Remove @Bean annotation from this method and configure it properly
    @Throws(Exception::class)
    fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .inMemoryAuthentication()
            .withUser("user1").password("pass1").roles("USER")
            .and()
            .withUser("user2").password("pass2").roles("USER")
    }
}

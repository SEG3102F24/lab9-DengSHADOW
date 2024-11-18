package seg3x02.tempconverterapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // Modern way to disable CSRF
            .authorizeHttpRequests { auth ->
                auth.anyRequest().authenticated() // All endpoints require authentication
            }
            .httpBasic { } // Enable Basic Authentication

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        // Use BCrypt for secure password encoding
        return BCryptPasswordEncoder()
    }

    @Bean
    fun configureUsers(authConfig: AuthenticationConfiguration): InMemoryUserDetailsManager {
        val user1 = org.springframework.security.core.userdetails.User
            .withUsername("user1")
            .password(passwordEncoder().encode("pass1"))
            .roles("USER")
            .build()

        val user2 = org.springframework.security.core.userdetails.User
            .withUsername("user2")
            .password(passwordEncoder().encode("pass2"))
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user1, user2)
    }
}

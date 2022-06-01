package com.clatems.clatems

import com.clatems.clatems.security.AuthenticationFilter
import com.clatems.clatems.security.TokenProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@SpringBootApplication
class ClatemsApplication

fun main(args: Array<String>) {
    runApplication<ClatemsApplication>(*args)
}

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenProvider: TokenProvider,
) : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
            val configuration = CorsConfiguration()

            configuration.addAllowedOrigin("http://localhost:3000")
            configuration.addAllowedHeader("*")
            configuration.addAllowedMethod("*")
            configuration.setAllowCredentials(true)

            val source = UrlBasedCorsConfigurationSource()
            source.registerCorsConfiguration("/**", configuration)

            return source
        }

        http.cors().configurationSource(corsConfigurationSource())
            .and()
            .csrf().disable()
            .httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/security/login", "/security/signup", "/chat").permitAll()
            .antMatchers(HttpMethod.GET, "/artworks").permitAll()
//            .anyRequest().authenticated()
            .and()
            .addFilterBefore(
                AuthenticationFilter(tokenProvider),
                UsernamePasswordAuthenticationFilter::class.java,
            )
    }
}

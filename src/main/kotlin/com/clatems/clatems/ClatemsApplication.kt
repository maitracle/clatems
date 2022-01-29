package com.clatems.clatems

import com.clatems.clatems.security.AuthenticationFilter
import com.clatems.clatems.security.TokenProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@SpringBootApplication
class ClatemsApplication

fun main(args: Array<String>) {
    runApplication<ClatemsApplication>(*args)
}

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenProvider: TokenProvider
) : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        http.csrf().disable()
            .httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/security/login").permitAll()
            .anyRequest().authenticated().and().cors()
            .and()
            .addFilterBefore(
                AuthenticationFilter(tokenProvider),
                UsernamePasswordAuthenticationFilter::class.java,
            )
    }
}

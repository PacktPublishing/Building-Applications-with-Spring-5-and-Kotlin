package com.journaler.api.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.core.annotation.Order

@Configuration
@EnableWebSecurity
@Order(1)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("12345")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("12345")
                .roles("ADMIN")
    }

    override fun configure(http: HttpSecurity) {
        http
                .httpBasic().disable().authorizeRequests()
                .antMatchers("/notes").hasAnyRole("USER", "ADMIN")
                .antMatchers("/notes/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/todos").hasAnyRole("USER", "ADMIN")
                .antMatchers("/todos/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/users").hasAnyRole("ADMIN")
                .antMatchers("/users/**").hasAnyRole("ADMIN")
                .and()
                .csrf().disable()
    }

}

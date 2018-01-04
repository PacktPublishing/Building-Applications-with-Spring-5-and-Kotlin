package com.journaler.api.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.core.annotation.Order

@Configuration
@EnableWebSecurity
@Order(1)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .httpBasic().disable().authorizeRequests()
                .antMatchers("/notes").hasAnyRole("USER", "ADMIN")
                .antMatchers("/notes/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/todos").hasAnyRole("USER", "ADMIN")
                .antMatchers("/todos/**").hasAnyRole("USER", "ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
    }

}

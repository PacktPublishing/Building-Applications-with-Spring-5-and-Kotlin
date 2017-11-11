package com.journaler.api.security

import com.journaler.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.vote.AuthenticatedVoter
import org.springframework.security.access.vote.RoleVoter
import org.springframework.security.access.vote.UnanimousBased
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.expression.WebExpressionVoter
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import java.util.*


@Configuration
@EnableWebSecurity
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var service: UserService

    /**
     * Will be resolved into: WebSecurityEntryPoint injected instance.
     */
    @Autowired
    lateinit var unauthorizedHandler: AuthenticationEntryPoint

    @Autowired
    lateinit var successHandler: WebSecurityAuthSuccessHandler

    @Autowired
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider())
    }

    override fun configure(http: HttpSecurity?) {
        http
                ?.csrf()?.disable()
                ?.exceptionHandling()
                ?.authenticationEntryPoint(unauthorizedHandler)
                ?.and()
                ?.authorizeRequests()
                /**
                 * Access to Notes and Todos API calls is given to any authenticated system user.
                 */
                ?.antMatchers("/notes")?.authenticated()
                ?.antMatchers("/notes/**")?.authenticated()
                ?.antMatchers("/todos")?.authenticated()
                ?.antMatchers("/todos/**")?.authenticated()
                /**
                 * Access to User API calls is given only to Admin user.
                 */
                ?.antMatchers("/users")?.hasAnyAuthority("ADMIN")
                ?.antMatchers("/users/**")?.hasAnyAuthority("ADMIN")
                ?.and()
                ?.formLogin()
                ?.successHandler(successHandler)
                ?.failureHandler(SimpleUrlAuthenticationFailureHandler())
                ?.and()
                ?.logout()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(service)
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder(11)

    @Bean
    fun accessDecisionManager(): AccessDecisionManager {
        val decisionVoters = Arrays.asList(
                WebExpressionVoter(),
                RoleVoter(),
                AuthenticatedVoter()
        )
        return UnanimousBased(decisionVoters)
    }

}
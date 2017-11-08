package com.journaler.api.security

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "user")
open class User(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "varchar(36)")
        var id: String,

        @Column(unique = true, nullable = false)
        @NotNull
        @Email
        var email: String,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotBlank
        var pwd: String,

        @NotBlank
        var firstName: String,

        @NotBlank
        var lastName: String,

        var roles: String,
        var enabled: Boolean,
        var accountNonExpired: Boolean,
        var accountNonLocked: Boolean,
        var credentialsNonExpired: Boolean,

        @CreationTimestamp
        var created: Date,

        @UpdateTimestamp
        var modified: Date
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        roles
                .split(",")
                .forEach { it ->
                    authorities.add(
                            SimpleGrantedAuthority(
                                    it.trim()
                            )
                    )
                }
        return authorities
    }

    override fun isEnabled() = enabled

    override fun getUsername() = email

    override fun isCredentialsNonExpired() = credentialsNonExpired

    override fun getPassword() = pwd

    override fun isAccountNonExpired() = accountNonExpired

    override fun isAccountNonLocked() = accountNonLocked

}
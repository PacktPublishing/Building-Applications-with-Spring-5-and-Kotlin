package com.journaler.api.service

import com.journaler.api.repository.UserRepository
import com.journaler.api.security.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Repository

@Repository
class UserService : UserDetailsService {

    @Autowired
    lateinit var repository: UserRepository

    override fun loadUserByUsername(email: String): User? {
        return repository.findOneByEmail(email) ?: throw RuntimeException("User not found: $email")
    }

    fun save(user: User): User = repository.save(user)

}
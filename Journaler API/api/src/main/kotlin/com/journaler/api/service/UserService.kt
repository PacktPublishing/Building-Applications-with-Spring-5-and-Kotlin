package com.journaler.api.service

import com.journaler.api.repository.UserRepository
import com.journaler.api.security.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserService : UserDetailsService {

    @Autowired
    lateinit var repository: UserRepository

    override fun loadUserByUsername(email: String): User? {
        return repository.findOneByEmail(email) ?: throw RuntimeException("User not found: $email")
    }

    fun save(user: User): User = repository.save(user)

    fun getUsers(): Iterable<User> = repository.findAll()

    fun deleteUser(id: String) = repository.deleteById(id)

    fun updateUser(toSave: User): User? {
        val user = repository.findOneByEmail(toSave.email)
        user?.let {
            user.firstName = toSave.firstName
            user.lastName = toSave.lastName
            user.modified = Date()
            return repository.save(user)
        }
        return null
    }

}
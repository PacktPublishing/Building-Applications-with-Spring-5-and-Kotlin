package com.journaler.api

import com.journaler.api.security.Admin
import com.journaler.api.service.UserService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.junit4.SpringRunner
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
class SecurityInitializationTest {

    @Autowired
    private lateinit var userService: UserService

    private val encoder = BCryptPasswordEncoder(11)

    private val password = "12345"
    private val email = "admin@example.com"

    @Test
    fun testInitialization() {
        try {
            val admin = userService.loadUserByUsername(email)
            if (admin is Admin) {
                println("Admin user exists: ${admin.id}")
            } else {
                Assert.fail("Admin is not an admin.")
            }
        } catch (e: RuntimeException){
            val toSave = Admin(
                    "",
                    email,
                    encoder.encode(password),
                    "admin",
                    "admin",
                    "ADMIN, USER",
                    true,
                    true,
                    true,
                    true,
                    Date(),
                    Date()
            )
            val saved = userService.save(toSave)
            println("Admin user inserted: ${saved.id}")
        }
    }

}
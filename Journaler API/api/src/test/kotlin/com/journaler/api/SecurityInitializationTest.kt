package com.journaler.api

import com.journaler.api.security.Admin
import com.journaler.api.security.Member
import com.journaler.api.security.UserDTO
import com.journaler.api.service.UserService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SecurityInitializationTest {

    @Autowired
    private lateinit var userService: UserService

    private val encoder = BCryptPasswordEncoder(11)

    private val password = "12345"
    private val adminEmail = "admin@example.com"
    private val memberEmail = "member@example.com"

    @Test
    fun initAdmin() {
        try {
            val admin = userService.loadUserByUsername(adminEmail)
            if (admin is Admin) {
                println("Admin user exists: ${admin.id}")
            } else {
                Assert.fail("Admin is not an admin.")
            }
        } catch (e: RuntimeException) {
            val toSave = UserDTO(
                    adminEmail,
                    encoder.encode(password),
                    "admin",
                    "admin"
            )
            val saved = userService.saveAdmin(toSave)
            println("Admin user inserted: ${saved.id}")
        }
    }

    @Test
    fun initMember() {
        try {
            val member = userService.loadUserByUsername(memberEmail)
            if (member is Member) {
                println("Member user exists: ${member.id}")
            } else {
                Assert.fail("Member is not an member.")
            }
        } catch (e: RuntimeException) {
            val toSave = UserDTO(
                    memberEmail,
                    encoder.encode(password),
                    "member",
                    "member"
            )
            val saved = userService.saveMember(toSave)
            println("Member user inserted: ${saved.id}")
        }
    }

}
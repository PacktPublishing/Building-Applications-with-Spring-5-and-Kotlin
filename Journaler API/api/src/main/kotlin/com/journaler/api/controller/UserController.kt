package com.journaler.api.controller

import com.journaler.api.security.User
import com.journaler.api.security.UserDTO
import com.journaler.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var service: UserService

    @GetMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getUsers() = service.getUsers()

    @PutMapping(
            value = "/admin",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertAdmin(
            @RequestBody user: UserDTO
    ) = service.saveAdmin(user)

    @PutMapping(
            value = "/member",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertMember(
            @RequestBody user: UserDTO
    ) = service.saveMember(user)


    @DeleteMapping(
            value = "/{id}",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteUser(
            @PathVariable(name = "id") id: String
    ) = service.deleteUser(id)

    @PostMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateUser(
            @RequestBody user: User
    ): User? = service.updateUser(user)

}
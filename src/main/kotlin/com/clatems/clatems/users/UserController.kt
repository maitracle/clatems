package com.clatems.clatems.users

import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService, private val modelMapper: ModelMapper) {

    @GetMapping
    fun getUserList() = ResponseEntity.ok(
        userService.findAll()
            .stream().map { user ->
                modelMapper.map(user, UserResponseDto::class.java)
            }
    )

    @PostMapping
    fun createUser(@RequestBody body: CreateUserDto): ResponseEntity<UserResponseDto> {
        val newUser = userService.saveUser(
            User(email = body.email, password = body.password)
        )
        return ResponseEntity.ok(modelMapper.map(newUser, UserResponseDto::class.java))
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<Any> {
        val updatedUser = userService.updateUser(id, user)
        return ResponseEntity.ok(modelMapper.map(updatedUser, UserResponseDto::class.java))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Any> {
        userService.deleteUser((id))
        return ResponseEntity.ok().build()
    }
}

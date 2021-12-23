package com.clatems.clatems.users

import com.clatems.clatems.commons.DtoConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService, private val dtoConverter: DtoConverter<User>) {

    @GetMapping
    fun getUserList() = ResponseEntity.ok(
        dtoConverter.mapEntityListToDtoList(userService.findAll(), UserResponseDto::class.java)

    )

    @PostMapping
    fun createUser(@RequestBody body: CreateUserDto): ResponseEntity<UserResponseDto> {
        val newUser = userService.saveUser(
            User(email = body.email, password = body.password)
        )
        return ResponseEntity.ok(
            dtoConverter.mapEntityToDto(newUser, UserResponseDto::class.java)
        )
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<UserResponseDto> {
        val updatedUser = userService.updateUser(id, user)
        return ResponseEntity.ok(
            dtoConverter.mapEntityToDto(updatedUser, UserResponseDto::class.java)
        )
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        userService.deleteUser((id))
        return ResponseEntity.ok().build()
    }
}

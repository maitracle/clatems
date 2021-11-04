package com.clatems.clatems.users

import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService, private val modelMapper: ModelMapper) {

  @PostMapping
  fun createUser(@RequestBody body: CreateUserDto): ResponseEntity<UserResponseDto>{
    val newUser = userService.save(
      User(email = body.email, password = body.password)
    )
    return ResponseEntity.ok(modelMapper.map(newUser, UserResponseDto::class.java))
  }


}
package com.clatems.clatems.users

import java.time.LocalDateTime

class CreateUserDto(
  val email: String,
  val password: String,
) {}

class UserResponseDto {
  val id: Long? = null
  val email: String = ""
  val password: String = ""
  val createdAt: LocalDateTime? = null
  val updatedAt: LocalDateTime? = null
}
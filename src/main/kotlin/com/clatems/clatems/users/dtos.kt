package com.clatems.clatems.users

import java.time.LocalDateTime

class CreateUserDto(
    var email: String,
    var password: String,
) {}

class UserResponseDto {
    val id: Long? = null
    var email: String = ""
    val createdAt: LocalDateTime? = null
    val updatedAt: LocalDateTime? = null
}

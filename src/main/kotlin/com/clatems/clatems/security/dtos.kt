package com.clatems.clatems.security

import com.clatems.clatems.users.UserResponseDto

class LoginRequestDto(
    val id: Long,
    val email: String,
    val password: String,
)

class LoginResponseDto(
    val user: UserResponseDto,
    val token: String,
)

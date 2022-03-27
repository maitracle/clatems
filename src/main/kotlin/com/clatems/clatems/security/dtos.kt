package com.clatems.clatems.security

import com.clatems.clatems.users.UserResponseDto


class LogInRequestDto(
    val email: String,
    val password: String,
)

class SignUpRequestDto(
    val email: String,
    val password: String,
)

class UserAndTokenResponseDto(
    val user: UserResponseDto,
    val token: String,
)

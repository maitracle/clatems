package com.clatems.clatems.security

import com.clatems.clatems.commons.DtoConverter
import com.clatems.clatems.users.User
import com.clatems.clatems.users.UserRepository
import com.clatems.clatems.users.UserResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/security")
class SecurityController(
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider,
    private val dtoConverter: DtoConverter<User>,
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginRequestDto): ResponseEntity<LoginResponseDto> {
        val target: User = userRepository.getById(loginRequestDto.id)

        if (target.authenticate(loginRequestDto.email, loginRequestDto.password)) {
            return ResponseEntity
                .ok()
                .body(
                    LoginResponseDto(
                        user = dtoConverter.mapEntityToDto(target, UserResponseDto::class.java),
                        token = tokenProvider.generateToken(loginRequestDto),
                    )
                )
        }

        return ResponseEntity(HttpStatus.UNAUTHORIZED)
    }
}

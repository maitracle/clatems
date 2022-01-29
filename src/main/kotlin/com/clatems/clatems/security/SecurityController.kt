package com.clatems.clatems.security

import com.clatems.clatems.commons.DtoConverter
import com.clatems.clatems.users.User
import com.clatems.clatems.users.UserRepository
import com.clatems.clatems.users.UserResponseDto
import com.clatems.clatems.users.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
@RequestMapping("/security")
class SecurityController(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider,
    private val dtoConverter: DtoConverter<User>,
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LogInRequestDto): ResponseEntity<UserAndTokenResponseDto> {
        val target: User = userRepository.getById(loginRequestDto.id)

        if (target.authenticate(loginRequestDto.email, loginRequestDto.password)) {
            return ResponseEntity
                .ok()
                .body(
                    UserAndTokenResponseDto(
                        user = dtoConverter.mapEntityToDto(target, UserResponseDto::class.java),
                        token = tokenProvider.generateToken(loginRequestDto.id),
                    )
                )
        }

        return ResponseEntity(HttpStatus.UNAUTHORIZED)
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequestDto: SignUpRequestDto): ResponseEntity<UserAndTokenResponseDto> {
        val user = userService.signUp(signUpRequestDto.email, signUpRequestDto.password)

        return ResponseEntity
            .created(
                // Todo(maitracle): user profile 조회 api를 만들고, uri를 세팅한다.
                URI("users/$user.id")
            )
            .body(
                UserAndTokenResponseDto(
                    dtoConverter.mapEntityToDto(user, UserResponseDto::class.java),
                    tokenProvider.generateToken(user.id!!),
                )
            )
    }
}

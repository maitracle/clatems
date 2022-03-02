package com.clatems.clatems.artworks

import com.clatems.clatems.users.UserResponseDto
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank


class CreateArtworkDto(
    @field:NotBlank val title: String = "",
    @field:NotBlank val description: String = "",
) {}


class ArtworkResponseDto(
    val id: Long? = null,
    val user: UserResponseDto? = null,
    val title: String? = null,
    val description: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
) {}


class UpdateArtworkDto(val id: Long) {}

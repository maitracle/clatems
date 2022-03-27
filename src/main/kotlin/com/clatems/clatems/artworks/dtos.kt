package com.clatems.clatems.artworks

import com.clatems.clatems.users.UserResponseDto
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank


class CreateArtworkDto(
    @field:NotBlank val title: String = "",
    @field:NotBlank val description: String = "",
    @field:NotBlank val metadataUrl: String = "",
    @field:NotBlank val imageUrl: String = "",
) {}


class ArtworkResponseDto(
    val id: Long? = null,
    val user: UserResponseDto? = null,
    val title: String = "",
    val description: String = "",
    val metadataUrl: String = "",
    val imageUrl: String = "",
    val transactionHash: String = "",
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
) {}


class UpdateArtworkDto(val id: Long) {}

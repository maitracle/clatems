package com.clatems.clatems.artworks

import java.time.LocalDateTime

class ArtworkResponseDto {
    val id: Long? = null
    val createdAt: LocalDateTime? = null
    val updatedAt: LocalDateTime? = null
}


class CreateArtworkDto {}

class UpdateArtworkDto(val id: Long) {}

package com.clatems.clatems.artworks

import java.time.LocalDateTime

class ArtworkResponseDto {
    val id: Long? = null
    val createdAt: LocalDateTime? = null
    val updatedAt: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArtworkResponseDto

        if (id != other.id) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}


class CreateArtworkDto {}

class UpdateArtworkDto(val id: Long) {}

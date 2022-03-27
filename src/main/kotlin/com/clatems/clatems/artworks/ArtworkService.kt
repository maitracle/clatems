package com.clatems.clatems.artworks

import com.clatems.clatems.users.User
import org.springframework.stereotype.Service


@Service
class ArtworkService(private val artworkRepository: ArtworkRepository) {
    fun findAll() = artworkRepository.findAll()

    fun findMyArtworks(user: User) = artworkRepository.findByUser(user)

    fun getById(id: Long): Artwork? {
        return this.artworkRepository.getById(id)
    }

    fun save(artwork: Artwork): Artwork {
        return artworkRepository.save(artwork)
    }

    fun deleteById(id: Long) {
        return artworkRepository.deleteById(id)
    }
}

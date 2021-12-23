package com.clatems.clatems.artworks

import org.springframework.stereotype.Service


@Service
class ArtworkService(private val artworkRepository: ArtworkRepository) {
    fun findAll() = artworkRepository.findAll()

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

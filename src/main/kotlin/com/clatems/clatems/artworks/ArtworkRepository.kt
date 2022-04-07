package com.clatems.clatems.artworks

import com.clatems.clatems.users.User
import org.springframework.data.jpa.repository.JpaRepository

interface ArtworkRepository : JpaRepository<Artwork, Long> {
    fun findByUser(user: User): List<Artwork>
}

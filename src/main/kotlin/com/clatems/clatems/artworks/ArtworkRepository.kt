package com.clatems.clatems.artworks

import org.springframework.data.jpa.repository.JpaRepository

interface ArtworkRepository : JpaRepository<Artwork, Long> {}

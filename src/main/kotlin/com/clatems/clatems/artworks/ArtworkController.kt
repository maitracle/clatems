package com.clatems.clatems.artworks

import com.clatems.clatems.commons.DtoConverter
import com.clatems.clatems.users.User
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid


@RestController
@RequestMapping("/artworks")
class ArtworkController(
    private val artworkService: ArtworkService,
    private val dtoConverter: DtoConverter<Artwork>,
) {

    @GetMapping
    fun getArtworkList() = ResponseEntity.ok(
        dtoConverter.mapEntityListToDtoList(artworkService.findAll(), ArtworkResponseDto::class.java)
    )

    @GetMapping(path = ["/{artworkId}"])
    fun retrieveArtwork(@PathVariable("artworkId") artworkId: Long): ResponseEntity<ArtworkResponseDto> {

        val foundArtwork = artworkService.getById(artworkId)
            ?: return ResponseEntity.badRequest().build()

        return ResponseEntity
            .ok()
            .body(dtoConverter.mapEntityToDto(foundArtwork, ArtworkResponseDto::class.java))
    }

    @PostMapping
    fun createArtwork(
        @RequestBody @Valid body: CreateArtworkDto,
        authentication: Authentication,
    ): ResponseEntity<ArtworkResponseDto> {
        val createdArtwork = artworkService.save(
            Artwork(
                user = authentication.principal as User,
                title = body.title,
                description = body.description,
            )
        )

        val uri: URI = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{artworkId}")
            .buildAndExpand(createdArtwork.id)
            .toUri()

        return ResponseEntity.created(uri)
            .body(dtoConverter.mapEntityToDto(createdArtwork, ArtworkResponseDto::class.java))
    }

   // @PatchMapping(path = ["/{artworkId}"])
   // fun updateArtwork(
   //     @PathVariable("artworkId") artworkId: Long,
   //     @RequestBody body: UpdateArtworkDto
   // ): ResponseEntity<ArtworkResponseDto> {
   //     val updatedArtwork = artworkService.save(
   //         Artwork(id = artworkId)
   //     )
   //
   //     return ResponseEntity.ok(
   //         dtoConverter.mapEntityToDto(updatedArtwork, ArtworkResponseDto::class.java)
   //     )
   // }

    @DeleteMapping(path = ["/{artworkId}"])
    fun deleteArtwork(@PathVariable("artworkId") artworkId: Long): ResponseEntity<Unit> {
        artworkService.deleteById(artworkId)

        return ResponseEntity.noContent()
            .build()
    }
}

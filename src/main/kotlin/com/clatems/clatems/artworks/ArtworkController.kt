package com.clatems.clatems.artworks

import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/artworks")
class ArtworkController(private val artworkService: ArtworkService, private val modelMapper: ModelMapper) {

//    @GetMapping
//    fun getArtworkList() = ResponseEntity.ok(
//        artworkService.findAll()
//            .
//    )
}

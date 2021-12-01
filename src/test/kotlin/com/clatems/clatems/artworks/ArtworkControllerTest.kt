package com.clatems.clatems.artworks

import com.clatems.clatems.commons.DtoConverter
import com.clatems.clatems.commons.ModelMapperConfig
import org.junit.jupiter.api.Test
import org.mockito.Mockito

internal class ArtworkControllerTest {
    private val mockArtworkService = Mockito.mock(ArtworkService::class.java)
    private val modelMapper = ModelMapperConfig().modelMapper()
    private val dtoConverter = DtoConverter<Artwork>(modelMapper)
    private val artworkController = ArtworkController(mockArtworkService, dtoConverter)


    @Test
    fun `should get asdf`() {
        artworkController.retrieveArtwork(5)
    }
}
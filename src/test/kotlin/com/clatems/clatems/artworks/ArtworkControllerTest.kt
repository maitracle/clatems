package com.clatems.clatems.artworks

import com.clatems.clatems.commons.DtoConverter
import com.clatems.clatems.commons.ModelMapperConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import kotlin.streams.toList

internal class ArtworkControllerTest {
    private val mockArtworkService = Mockito.mock(ArtworkService::class.java)
    private val modelMapper = ModelMapperConfig().modelMapper()
    private val dtoConverter = DtoConverter<Artwork>(modelMapper)
    private val artworkController = ArtworkController(mockArtworkService, dtoConverter)


    @Test
    fun `should get artwork list`() {
        val expectedArtworkCount = 3
        val serviceArtworkListResponse = listOf(
            Artwork(id = 1),
            Artwork(id = 2),
            Artwork(id = 3),
        )
        val expectedArtworkList = serviceArtworkListResponse
            .stream().map { artwork ->
                modelMapper.map(artwork, ArtworkResponseDto::class.java)
            }.toList()

        Mockito.`when`(mockArtworkService.findAll()).thenReturn(serviceArtworkListResponse)

        val actual = artworkController.getArtworkList()

        Assertions.assertEquals(HttpStatus.OK, actual.statusCode)

        val actualArtworkList = actual.body!!.toList()

        Assertions.assertEquals(expectedArtworkCount, actualArtworkList.count())

        expectedArtworkList.zip(actualArtworkList).forEach { pair ->
            Assertions.assertEquals(pair.first.id, pair.second.id)
            Assertions.assertEquals(pair.first.createdAt, pair.second.createdAt)
            Assertions.assertEquals(pair.first.updatedAt, pair.second.updatedAt)
        }
    }

    @Test
    fun `should retrieve artwork`() {
        val artworkIdParam: Long = 1
        val expectedArtwork = Artwork(id = artworkIdParam)

        Mockito.`when`(mockArtworkService.getById(artworkIdParam)).thenReturn(expectedArtwork)

        val actual = artworkController.retrieveArtwork(artworkIdParam)

        Assertions.assertEquals(HttpStatus.OK, actual.statusCode)

        val actualArtwork = actual.body!!

        Assertions.assertEquals(expectedArtwork.id, actualArtwork.id)
        Assertions.assertEquals(expectedArtwork.createdAt, actualArtwork.createdAt)
        Assertions.assertEquals(expectedArtwork.updatedAt, actualArtwork.updatedAt)
    }

    @Test
    fun `should not retrieve artwork with wrong artwork id`() {
    }

    @Test
    fun `should update artwork`() {
        val artworkIdParam: Long = 1
        val updateArtworkDto = UpdateArtworkDto(id = artworkIdParam)

        val actual = artworkController.updateArtwork(artworkIdParam, updateArtworkDto)
    }

    @Test
    fun `should not update artwork with wrong artwork id`() {
    }

    @Test
    fun `should delete artwork`() {
        val artworkIdParam: Long = 1

        val actual = artworkController.deleteArtwork(artworkIdParam)
    }

    @Test
    fun `should not delete artwork with wrong artwork id`() {
    }
}
package com.clatems.clatems.artworks

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

internal class ArtworkServiceTest {
    private val mockArtworkRepository = Mockito.mock(ArtworkRepository::class.java)
    private val artworkService = ArtworkService(mockArtworkRepository)

    @Test
    fun `should get artwork list`() {
        val expectedArtworkList: List<Artwork> = listOf(
            Artwork(id = 1),
            Artwork(id = 2),
            Artwork(id = 3),
        )
        Mockito.`when`(mockArtworkRepository.findAll()).thenReturn(expectedArtworkList)

        val actualArtworkList = artworkService.findAll()

        Assertions.assertEquals(expectedArtworkList, actualArtworkList)
    }

    @Test
    fun `should get artwork with id`() {
        val idArgument: Long = 1
        val expectedArtwork = Artwork(id = idArgument)
        Mockito.`when`(mockArtworkRepository.getById(idArgument)).thenReturn(expectedArtwork)

        val retrievedArtwork = artworkService.getById(idArgument)

        Assertions.assertEquals(expectedArtwork, retrievedArtwork)
    }

    @Test
    fun `should not get artwork with id when id is not existed`() {
        val wrongIdArgument: Long = 2
        val notFetchedArtwork = null
        Mockito.`when`(mockArtworkRepository.getById(wrongIdArgument)).thenReturn(notFetchedArtwork)

        val retrievedDummy = artworkService.getById(wrongIdArgument)

        Assertions.assertEquals(notFetchedArtwork, retrievedDummy)
    }

    @Test
    fun `should save artwork`() {
        val expectedId: Long = 1
        val expectedArtwork = Artwork(id = expectedId)

        Mockito.`when`(mockArtworkRepository.save(expectedArtwork)).thenReturn(
            expectedArtwork
        )

        val savedArtwork = artworkService.save(expectedArtwork)

        Assertions.assertEquals(expectedArtwork, savedArtwork)
    }

    @Test
    fun `should delete artwork`() {
        val deleteArtworkParam: Long = 1
        val expectedResponse = Unit

        val actualResponse = artworkService.deleteById((deleteArtworkParam))

        Assertions.assertEquals(expectedResponse, actualResponse)
    }
}

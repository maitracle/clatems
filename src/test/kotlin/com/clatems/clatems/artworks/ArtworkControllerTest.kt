package com.clatems.clatems.artworks

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import kotlin.streams.toList

@SpringBootTest
internal class ArtworkControllerTest(
    @Autowired private val artworkController: ArtworkController,
    @Autowired private val artworkRepository: ArtworkRepository,
) {

    @Test
    fun `should get artwork list`() {
        val expectedArtworkCount = 3
        val expectedArtworkList = artworkRepository.saveAll(
            listOf(
                Artwork(id = 1),
                Artwork(id = 2),
                Artwork(id = 3),
            )
        )

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
        val expectedArtwork = artworkRepository.save(Artwork(id = artworkIdParam))

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
        // Todo(maitracle): artwork에 수정할 만한 field가 추가된 후 test를 보완한다.
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
        artworkRepository.save(Artwork(id = artworkIdParam))

        val actual = artworkController.deleteArtwork(artworkIdParam)

        Assertions.assertEquals(HttpStatus.NO_CONTENT, actual.statusCode)
    }

    @Test
    fun `should not delete artwork with wrong artwork id`() {
    }
}
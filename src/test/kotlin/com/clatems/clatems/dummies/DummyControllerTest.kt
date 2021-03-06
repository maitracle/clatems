package com.clatems.clatems.dummies

import com.clatems.clatems.commons.DtoConverter
import com.clatems.clatems.commons.ModelMapperConfig
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus
import kotlin.streams.toList

internal class DummyControllerTest {
    private val mockDummyService = Mockito.mock(DummyService::class.java)
    private val modelMapper = ModelMapperConfig().modelMapper()
    private val dtoConverter = DtoConverter<Dummy>(modelMapper)
    private val dummyController = DummyController(mockDummyService, dtoConverter)

    @Test
    fun `should get Hello string`() {
        val expected = "Hello"

        val actualResponse = dummyController.hello()

        assertEquals(expected, actualResponse)
    }

    @Test
    fun `should get dummy list`() {
        val expectedDummyCount = 3
        val serviceDummyListResponse = listOf(
            Dummy(id = 1, stringField = "test string 1", numberField = 1),
            Dummy(id = 2, stringField = "test string 2", numberField = 2),
            Dummy(id = 3, stringField = "test string 3", numberField = 3),
        )
        val expectedDummyList = serviceDummyListResponse
            .stream().map { dummy ->
                modelMapper.map(dummy, DummyResponseDto::class.java)
            }.toList()

        `when`(mockDummyService.findAll()).thenReturn(serviceDummyListResponse)

        val actual = dummyController.getDummyList()

        assertEquals(HttpStatus.OK, actual.statusCode)

        val actualDummyList = actual.body!!.toList()

        assertEquals(expectedDummyCount, actualDummyList.count())

        expectedDummyList.zip(actualDummyList).forEach { pair ->
            assertEquals(pair.first, pair.second)
        }
    }
}

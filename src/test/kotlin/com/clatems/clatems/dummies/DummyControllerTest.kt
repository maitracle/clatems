package com.clatems.clatems.dummies

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
    private val dummyController = DummyController(mockDummyService, modelMapper)

    @Test
    fun `should get Hello string`() {
        val expected = "Hello"

        val actualResponse = dummyController.hello()

        assertEquals(expected, actualResponse)
    }

    @Test
    fun `should get dummy list`() {
        val expectedDummyCount = 3
        val serviceDummyResponse = listOf(
            Dummy(id = 1, stringField = "test string 1", numberField = 1),
            Dummy(id = 2, stringField = "test string 2", numberField = 2),
            Dummy(id = 3, stringField = "test string 3", numberField = 3)
        )
        val expectedDummyList = serviceDummyResponse
            .stream().map { dummy ->
                modelMapper.map(dummy, DummyResponseDto::class.java)
            }.toList()

        `when`(mockDummyService.findAll()).thenReturn(serviceDummyResponse)

        val actual = dummyController.getDummyList()

        assertEquals(HttpStatus.OK, actual.statusCode)

        val actualDummyList = actual.body!!.toList()

        assertEquals(expectedDummyCount, actualDummyList.count())

        expectedDummyList.zip(actualDummyList).forEach { pair ->
            assertEquals(pair.first, pair.second)
        }
    }
}

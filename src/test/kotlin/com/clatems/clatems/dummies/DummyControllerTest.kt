package com.clatems.clatems.dummies

import com.clatems.clatems.commons.ModelMapperConfig
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.stream.Stream
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
        val serviceDummyResponse = listOf(
            Dummy(id = 1, stringField = "test string 1", numberField = 1),
            Dummy(id = 2, stringField = "test string 2", numberField = 2),
            Dummy(id = 3, stringField = "test string 3", numberField = 3)
        )
        val expectedDummyList: Stream<DummyResponseDto> = serviceDummyResponse
            .stream().map { dummy ->
                modelMapper.map(dummy, DummyResponseDto::class.java)
            }

        `when`(mockDummyService.findAll()).thenReturn(serviceDummyResponse)

        val actualDummyList: Stream<DummyResponseDto> = dummyController.getDummyList()

        expectedDummyList.toList().zip(actualDummyList.toList()).forEach { pair ->
            assertEquals(pair.first, pair.second)
        }
    }
}

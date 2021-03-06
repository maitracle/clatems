package com.clatems.clatems.dummies

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


internal class DummyServiceTest {
    private val mockDummyRepository = mock(DummyRepository::class.java)
    private val dummyService = DummyService(mockDummyRepository)

    @Test
    fun `should get dummy list`() {
        val expectedDummyList: List<Dummy> = listOf(
            Dummy(id = 1, stringField = "test string 1", numberField = 1),
            Dummy(id = 2, stringField = "test string 2", numberField = 2),
            Dummy(id = 3, stringField = "test string 3", numberField = 3)
        )
        `when`(mockDummyRepository.findAll()).thenReturn(expectedDummyList)

        val actualDummyList = dummyService.findAll()

        assertEquals(expectedDummyList, actualDummyList)
    }

    @Test
    fun `should get dummy with id`() {
        val idArgument: Long = 1
        val expectedDummy = Dummy(id = idArgument, stringField = "test string 1", numberField = 1)
        `when`(mockDummyRepository.getById(idArgument)).thenReturn(expectedDummy)

        val retrievedDummy = dummyService.getById(idArgument)

        assertEquals(expectedDummy, retrievedDummy)
    }

    @Test
    fun `should not get dummy with id when id is not existed`() {
        val wrongIdArgument: Long = 2
        val notFetchedDummy = null
        `when`(mockDummyRepository.getById(wrongIdArgument)).thenReturn(notFetchedDummy)

        val retrievedDummy = dummyService.getById(wrongIdArgument)

        assertEquals(notFetchedDummy, retrievedDummy)
    }

    @Test
    fun `should save dummy`() {
        val expectedId: Long = 1
        val expectedStringField = "test string 1"
        val expectedNumberField = 1
        val expectedDummy = Dummy(id = expectedId, stringField = expectedStringField, numberField = expectedNumberField)

        `when`(mockDummyRepository.save(expectedDummy)).thenReturn(
            expectedDummy
        )

        val createdDummy = dummyService.save(expectedDummy)

        assertEquals(expectedDummy, createdDummy)
    }
}

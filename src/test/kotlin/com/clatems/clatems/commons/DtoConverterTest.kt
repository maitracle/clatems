package com.clatems.clatems.commons

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.modelmapper.MappingException
import kotlin.streams.toList

internal class DtoConverterTest {
    class SomeEntity(
        override val id: Long? = null,
    ) : BaseEntity(id) {}

    class SomeDtoClass(val id: Long? = null)

    private val modelMapper = ModelMapperConfig().modelMapper()

    private val dtoConverter = DtoConverter<SomeEntity>(modelMapper)

    @Test
    fun `should convert entity list to dto stream`() {
        val givenEntityList = listOf<SomeEntity>(SomeEntity(id=1), SomeEntity(id=2), SomeEntity(id=3))
        val expectedEntityList = givenEntityList.stream()
            .map { someEntity -> modelMapper.map(someEntity, SomeDtoClass::class.java) }
            .toList()

        val actualList = dtoConverter.mapEntityListToDtoList(givenEntityList, SomeDtoClass::class.java)
            .toList()

        assertEquals(expectedEntityList.count(), actualList.count())

        expectedEntityList.zip(actualList).forEach { pair ->
            assertEquals(pair.first.id, pair.second.id)
        }
    }

    @Test
    fun `should raise exception when converto to wrong dto class`() {
        class WrongDtoClass(val id: Long? = null, val anotherField: String)

        val givenEntityList = listOf<SomeEntity>(SomeEntity(id=1), SomeEntity(id=2), SomeEntity(id=3))

        assertThrows(MappingException::class.java) {
            dtoConverter.mapEntityListToDtoList(givenEntityList, WrongDtoClass::class.java)
        }
    }
}

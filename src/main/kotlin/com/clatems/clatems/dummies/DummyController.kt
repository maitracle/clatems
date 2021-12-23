package com.clatems.clatems.dummies

import com.clatems.clatems.commons.DtoConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dummies")
class DummyController(
    private val dummyService: DummyService,
    private val dtoConverter: DtoConverter<Dummy>
) {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello"
    }

    @GetMapping
    fun getDummyList() = ResponseEntity.ok(
        dtoConverter.mapEntityListToDtoList(dummyService.findAll(), DummyResponseDto::class.java)
    )

    @PostMapping
    fun createDummy(@RequestBody body: CreateDummyDto): ResponseEntity<DummyResponseDto> {
        val createdDummy = dummyService.save(
            Dummy(stringField = body.stringField, numberField = body.numberField)
        )

        return ResponseEntity.ok(
            dtoConverter.mapEntityToDto(createdDummy, DummyResponseDto::class.java)
        )
    }
}

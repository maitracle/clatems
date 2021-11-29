package com.clatems.clatems.dummies

import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dummies")
class DummyController(private val dummyService: DummyService, private val modelMapper: ModelMapper) {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello"
    }

    @GetMapping
    fun getDummyList() = ResponseEntity.ok(
        dummyService.findAll()
            .stream().map { dummy ->
                modelMapper.map(dummy, DummyResponseDto::class.java)
            }
    )

    @PostMapping
    fun createDummy(@RequestBody body: CreateDummyDto): ResponseEntity<DummyResponseDto> {
        val createdDummy = dummyService.save(
            Dummy(stringField = body.stringField, numberField = body.numberField)
        )

        return ResponseEntity.ok(
            modelMapper.map(createdDummy, DummyResponseDto::class.java)
        )
    }
}

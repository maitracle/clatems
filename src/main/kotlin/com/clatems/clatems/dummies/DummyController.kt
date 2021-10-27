package com.clatems.clatems.dummies

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dummies")
class DummyController(private val dummyService: DummyService) {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello"
    }

    @GetMapping
    fun getDummyList() = dummyService.findAll()

    @PostMapping
    fun createDummy(@RequestBody body: CreateDummyDto): ResponseEntity<RetrieveDummyDto> {
        val createdDummy = dummyService.save(
            Dummy(stringField = body.stringField, numberField = body.numberField)
        )

        println(1234)

        return ResponseEntity.ok(
            RetrieveDummyDto(createdDummy.id, createdDummy.stringField, createdDummy.numberField)
        )
    }
}

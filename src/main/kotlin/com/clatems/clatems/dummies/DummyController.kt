package com.clatems.clatems.dummies

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dummies")
class DummyController(private val dummyService: DummyService) {
    @GetMapping
    fun getDummyList() = dummyService.findAll()

    @PostMapping
    fun createDummy(@RequestBody body: CreateDummyDto): ResponseEntity<Dummy> {
        val dummy = Dummy(stringField = body.stringField, numberField = body.numberField)

        return ResponseEntity.ok(dummyService.save(dummy))
    }
}

package com.clatems.clatems.dummies

import java.time.LocalDateTime

class CreateDummyDto(
    val stringField: String,
    val numberField: Number,
) {}

class DummyResponseDto {
    val id: Long? = null
    val stringField: String = ""
    val numberField: Number = 0
    val createdAt: LocalDateTime? = null
    val updatedAt: LocalDateTime? = null
}

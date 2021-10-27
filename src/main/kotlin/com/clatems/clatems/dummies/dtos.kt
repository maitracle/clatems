package com.clatems.clatems.dummies

class CreateDummyDto(
    val stringField: String,
    val numberField: Number,
) {}

class RetrieveDummyDto(
    val id: Long?,
    val stringField: String,
    val numberField: Number,
) {}

class DummyResponseDto(
    val id: Long?,
    val stringField: String,
    val numberField: Number,
) {}
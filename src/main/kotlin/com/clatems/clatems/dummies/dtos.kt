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

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as DummyResponseDto

    if (id != other.id) return false
    if (stringField != other.stringField) return false
    if (numberField != other.numberField) return false
    if (createdAt != other.createdAt) return false
    if (updatedAt != other.updatedAt) return false

    return true
  }

  override fun hashCode(): Int {
    return id?.hashCode() ?: 0
  }
}

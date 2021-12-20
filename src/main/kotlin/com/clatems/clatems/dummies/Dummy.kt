package com.clatems.clatems.dummies

import com.clatems.clatems.commons.BaseEntity
import javax.persistence.Entity

@Entity
class Dummy(
  override val id: Long? = null,
  val stringField: String,
  val numberField: Number,
) : BaseEntity(id) {}

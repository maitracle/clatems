package com.clatems.clatems.dummies

import com.clatems.clatems.commons.BaseEntity
import javax.persistence.Entity

@Entity
class Dummy(
    val stringField: String,
    val numberField: Number,
) : BaseEntity() {}

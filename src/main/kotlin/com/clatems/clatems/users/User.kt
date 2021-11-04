package com.clatems.clatems.users

import com.clatems.clatems.commons.BaseEntity
import javax.persistence.Entity

@Entity
class User(
  val email: String,
  val password: String
) : BaseEntity() {}

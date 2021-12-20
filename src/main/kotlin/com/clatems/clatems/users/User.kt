package com.clatems.clatems.users

import com.clatems.clatems.commons.BaseEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name="application_user")
class User(
  override val id: Long? = null,
  var email: String,
  var password: String,
) : BaseEntity(id) {}

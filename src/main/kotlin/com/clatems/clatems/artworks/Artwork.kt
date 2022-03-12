package com.clatems.clatems.artworks

import com.clatems.clatems.commons.BaseEntity
import com.clatems.clatems.users.User
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class Artwork(
    override val id: Long? = null,
    @ManyToOne var user: User,
    var title: String,
    var description: String,
    var transactionHash: String? = null,
) : BaseEntity(id) {}

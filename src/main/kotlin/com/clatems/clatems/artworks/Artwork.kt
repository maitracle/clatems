package com.clatems.clatems.artworks

import com.clatems.clatems.commons.BaseEntity
import javax.persistence.Entity

@Entity
class Artwork(
    override val id: Long? = null,
) : BaseEntity(id) {}

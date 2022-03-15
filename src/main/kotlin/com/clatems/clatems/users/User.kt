package com.clatems.clatems.users

import com.clatems.clatems.commons.BaseEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "application_user")
class User(
    override val id: Long? = null,
    @Column(unique = true) var email: String,
) : BaseEntity(id), UserDetails {

    @Column(name = "password")
    internal var password = ""
        get() = field
        set(value) {
            field = BCryptPasswordEncoder().encode(value)
        }

    @Column
    var isActive: Boolean = true

    fun authenticate(email: String, password: String): Boolean {
        return email == this.email && comparePassword(password)
    }

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("ROLE_USER"))
        return authorities
    }

    override fun getPassword() = this.password

    override fun getUsername() = this.email

    override fun isAccountNonExpired() = this.isActive

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}

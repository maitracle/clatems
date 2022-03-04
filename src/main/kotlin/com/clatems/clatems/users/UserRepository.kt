package com.clatems.clatems.users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    abstract fun getByEmail(email: String): User?

//    Todo(harin): email을 가지고 user 가져오는 method 추가
//    fun findByEmail(email: String): User?
//    fun existByEmail(email: String): Boolean
}

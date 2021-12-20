package com.clatems.clatems.users

import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class UserService(private val userRepository: UserRepository) {

    fun findAll() = userRepository.findAll()

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    fun getById(id: Long): User {
        return this.userRepository.getById(id)
    }


    fun updateUser(id: Long, user: User) {
        val target: User = getById(id)
        target.email = user.email
        target.password = user.password
        userRepository.save(target)
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}

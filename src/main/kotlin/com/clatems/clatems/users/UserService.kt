package com.clatems.clatems.users

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findAll() = userRepository.findAll()

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    fun getById(id: Long): User? {
        return this.userRepository.getById(id)
    }

    fun getByEmail(email: String): User? {
        return this.userRepository.getByEmail(email)
    }

    fun updateUser(id: Long, user: User): User {
        val target = getById(id)
            ?: throw Exception()

        target.email = user.email
        target.password = user.password
        return userRepository.save(target)
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    fun signUp(email: String, password: String): User {
        val user = User(
            email = email
        )
        user.password = password

        return this.saveUser(user)
    }
}

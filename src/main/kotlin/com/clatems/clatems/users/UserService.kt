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

    // Todo(harin): email을 가지고 user 가져오는 method 추가

//    @Transactional(readOnly = true)
//    fun getUserInfo(email: String): User?{
//        return userRepository.findByEmail(email)?: throw RuntimeException("유저 정보가 없습니다.")
//    }

//    fun getMyInfo(): Optional<User> {
//        return userRepository.findById(SecurityUtil.getCurrentMemberId)?: throw RuntimeException("로그인 유저 정보가 없습니다.")
//    }
}

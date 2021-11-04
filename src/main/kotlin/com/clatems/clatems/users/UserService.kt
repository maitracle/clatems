package com.clatems.clatems.users

import com.clatems.clatems.dummies.Dummy
import com.clatems.clatems.dummies.DummyRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
  fun save(user: User): User{
    return userRepository.save(user)
  }
}
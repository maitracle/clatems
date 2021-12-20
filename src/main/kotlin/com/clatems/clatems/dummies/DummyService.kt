package com.clatems.clatems.dummies

import org.springframework.stereotype.Service


@Service
class DummyService(private val dummyRepository: DummyRepository) {
    fun findAll() = dummyRepository.findAll()

    fun save(member: Dummy): Dummy {
        return dummyRepository.save(member)
    }

    fun getById(id: Long): Dummy? {
        return this.dummyRepository.getById(id)
    }
}

package com.clatems.clatems.dummies
import org.springframework.data.jpa.repository.JpaRepository

interface DummyRepository : JpaRepository<Dummy, Long> {}

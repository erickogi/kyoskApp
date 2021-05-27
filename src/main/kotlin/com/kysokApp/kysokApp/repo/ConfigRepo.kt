package com.kysokApp.kysokApp.repo

import com.kysokApp.kysokApp.entities.Config
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
 interface ConfigRepo : JpaRepository<Config,Long> {
     fun findByName(name: String): Optional<Config>
}

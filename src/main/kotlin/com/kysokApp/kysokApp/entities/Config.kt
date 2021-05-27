package com.kysokApp.kysokApp.entities

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.kysokApp.kysokApp.utils.MetaDataConverter
import org.hibernate.annotations.Type
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import javax.persistence.*

@Entity
@EnableAutoConfiguration
class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "name", unique = true)
    var name: String? = null


    @Column(name = "metadata", columnDefinition = "json")
    @Convert(converter = MetaDataConverter::class)
    var metadata: Metadata? = null


    constructor(id: Long?, name: String?, metadata: Metadata?) {
        this.id = id
        this.name = name
        this.metadata = metadata
    }

    constructor()

    fun update(configUpdate: Config): Config {
        name = configUpdate.name
        metadata = configUpdate.metadata

        return this
    }
}
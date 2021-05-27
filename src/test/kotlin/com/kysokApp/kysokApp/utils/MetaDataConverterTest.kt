package com.kysokApp.kysokApp.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.kysokApp.kysokApp.entities.Config
import com.kysokApp.kysokApp.entities.Metadata
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
internal class MetaDataConverterTest {

    var metaDataConverter: MetaDataConverter? = null

    @BeforeEach
    open fun initUseCase() {
        metaDataConverter = MetaDataConverter()
    }

    @Test
    fun convertToDatabaseColumn_success() {
        val objectMapper = ObjectMapper()


          val config =  Config(
                2,
                "datacenter-2",
                objectMapper.readValue("{\"monitoring\": {\"enabled\": \"true\"},\"limits\": {\"cpu\": {\"enabled\": \"true\",\"value\": \"250m\"}}}",
                    object : TypeReference<Metadata>() {})
            )

        val result: String? = metaDataConverter?.convertToDatabaseColumn(config.metadata)
        assertThat(result).isEqualTo("{\"monitoring\":{\"enabled\":\"false\"},\"limits\":{\"cpu\":{\"enabled\":\"true\",\"value\":\"255m\"}}}")

    }

    @Test
    fun convertToDatabaseColumn_failure() {
        val objectMapper = ObjectMapper()


        val config =  Config(
            2,
            "datacenter-2",
            objectMapper.readValue("{\"monitoring\": {\"enabled\": \"false\"},\"limits\": {\"cpu\": {\"enabled\": \"true\",\"value\": \"250m\"}}}",
                object : TypeReference<Metadata>() {})
        )

        val result: String? = metaDataConverter?.convertToDatabaseColumn(config.metadata)
        assertThat(result).isNotEqualTo("{\"monitoring\":{\"enabled\":\"false\"},\"limits\":{\"cpu\":{\"enabled\":\"true\",\"value\":\"255m\"}}}")

    }

    @Test
    fun convertToEntityAttribute_success() {
        val objectMapper = ObjectMapper()


        val config =  Config(
            2,
            "datacenter-2",
            objectMapper.readValue("{\"monitoring\": {\"enabled\": \"true\"},\"limits\": {\"cpu\": {\"enabled\": \"true\",\"value\": \"250m\"}}}",
                object : TypeReference<Metadata>() {})
        )

        val result: Metadata? = metaDataConverter?.convertToEntityAttribute("{\"monitoring\":{\"enabled\":\"false\"},\"limits\":{\"cpu\":{\"enabled\":\"true\",\"value\":\"255m\"}}}")
        assertThat(result).isEqualTo(config.metadata)

    }

    @Test
    fun convertToEntityAttribute_failure() {
        val objectMapper = ObjectMapper()


        val config =  Config(
            2,
            "datacenter-2",
            objectMapper.readValue("{\"monitoring\": {\"enabled\": \"true\"},\"limits\": {\"cpu\": {\"enabled\": \"true\",\"value\": \"250m\"}}}",
                object : TypeReference<Metadata>() {})
        )

        val result: Metadata? = metaDataConverter?.convertToEntityAttribute("{\"falingtest\":{\"enabled\":\"true\"},\"limits\":{\"cpu\":{\"enabled\":\"true\",\"value\":\"255m\"}}}")
        assertThat(result).isNotEqualTo(config.metadata)

    }
}
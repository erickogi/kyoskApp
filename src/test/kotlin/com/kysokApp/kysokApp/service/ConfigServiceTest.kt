package com.kysokApp.kysokApp.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.kysokApp.kysokApp.entities.Config
import com.kysokApp.kysokApp.entities.Metadata
import com.kysokApp.kysokApp.entities.response.RestResponse
import com.kysokApp.kysokApp.repo.ConfigRepo
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*


@ExtendWith(MockitoExtension::class)
internal class ConfigServiceTest {
    @Mock
    private val configRepo: ConfigRepo? = null

    var configService: ConfigService? = null

    @BeforeEach
    open fun initUseCase() {
        configService = ConfigServiceImpl(configRepo)
    }

    @Test
    fun listConfigs() {
        val objectMapper = ObjectMapper()
        val configs: ArrayList<Config> = ArrayList();
        configs.add(
            Config(
                1,
                "datacenter-1",
                objectMapper.readValue("{\"monitoring\": {\"enabled\": \"true\"},\"limits\": {\"cpu\": {\"enabled\": \"false\",\"value\": \"300m\"}}}",
                    object : TypeReference<Metadata>() {})
            )
        )
        configs.add(
            Config(
                2,
                "datacenter-2",
                objectMapper.readValue("{\"monitoring\": {\"enabled\": \"true\"},\"limits\": {\"cpu\": {\"enabled\": \"true\",\"value\": \"250m\"}}}",
                    object : TypeReference<Metadata>() {})
            )
        )
        `when`(configRepo?.findAll()).thenReturn(configs)

        val allConfigs: RestResponse? = configService?.listConfigs()
        assertThat((allConfigs?.body as List<*>).size).isGreaterThanOrEqualTo(1)

    }

    @Test
    fun createConfig() {
        val objectMapper = ObjectMapper()
        val config = Config(
            1,
            "datacenter-1",
            objectMapper.readValue("{\"monitoring\": {\"enabled\": \"true\"},\"limits\": {\"cpu\": {\"enabled\": \"false\",\"value\": \"300m\"}}}",
                object : TypeReference<Metadata>() {})
        )

        `when`(configRepo?.save(any(Config::class.java))).thenReturn(config)

        val savedConfig: RestResponse? = configService?.createConfig(config)
        assertThat(savedConfig?.body).isNotNull()
    }

    @Test
    fun getConfig() {
        val objectMapper = ObjectMapper()
        val config = Config(
            1,
            "datacenter-1",
            objectMapper.readValue("{\"monitoring\": {\"enabled\": \"true\"},\"limits\": {\"cpu\": {\"enabled\": \"false\",\"value\": \"300m\"}}}",
                object : TypeReference<Metadata>() {})
        )

        `when`(configRepo?.findByName("datacenter-1")).thenReturn(Optional.of(config))
        val savedConfig: RestResponse? = configService?.getConfig("datacenter-1")
        assertThat(savedConfig).isEqualTo(config)

    }

    @Test
    fun updateConfig() {
        val objectMapper = ObjectMapper()
        val updatedMockConfig = Config(
            1,
            "datacenter-1",
            objectMapper.readValue("{\"monitoring\": {\"enabled\": \"true\"},\"limits\": {\"cpu\": {\"enabled\": \"false\",\"value\": \"300m\"}}}",
                object : TypeReference<Metadata>() {})
        )

        `when`(configRepo?.findByName("datacenter-1")).thenReturn(Optional.of(updatedMockConfig))
        val savedConfig: RestResponse? = configService?.updateConfig("datacenter-1", updatedMockConfig)
        assertThat(savedConfig).isEqualTo(updatedMockConfig)

    }

    @Test
    fun deleteConfig() {

    }
}
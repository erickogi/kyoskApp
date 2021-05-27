package com.kysokApp.kysokApp.repo


import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.kysokApp.kysokApp.entities.Config
import com.kysokApp.kysokApp.entities.Metadata
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer


@ExtendWith(MockitoExtension::class)
@DataJpaTest
internal class ConfigRepoTest {

    @Autowired
    private val configRepo: ConfigRepo? = null

    @BeforeEach
    fun initUseCase() {
        val objectMapper = ObjectMapper()
        val configs: List<Config> = Arrays.asList<Config>(
            Config(
                1,
                "datacenter-1",
                objectMapper.readValue("{\"monitoring\": {\"enabled\": \"true\"},\"limits\": {\"cpu\": {\"enabled\": \"false\",\"value\": \"300m\"}}}",
                    object : TypeReference<Metadata>() {})
            )
        )
        configRepo?.saveAll(configs)
    }

    @AfterEach
    fun destroyAll() {
        configRepo?.deleteAll()
    }

    @Test
    fun saveAll_success() {
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
        val allCustomer: MutableList<Config>? = configRepo?.saveAll(configs)
        val validIdFound = AtomicInteger()
        allCustomer?.forEach(Consumer<Config> { config: Config ->
            if (config.id!! > 0) {
                validIdFound.getAndIncrement()
            }
        })
        assertThat(validIdFound.toInt()).isEqualTo(3)
    }
    @Test
    fun findByName() {
        val allConfigs: Optional<Config>? = configRepo?.findByName("datacenter-1")
        assertThat(allConfigs?.get()).isNotNull()
    }

    @Test
    fun findAll_success() {
        val allConfigs: MutableList<Config>? = configRepo?.findAll()
        assertThat(allConfigs?.size).isGreaterThanOrEqualTo(1)
    }
}
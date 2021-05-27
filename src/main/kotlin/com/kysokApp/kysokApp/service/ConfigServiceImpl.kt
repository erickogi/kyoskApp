package com.kysokApp.kysokApp.service

import com.kysokApp.kysokApp.entities.Config
import com.kysokApp.kysokApp.entities.Metadata
import com.kysokApp.kysokApp.entities.response.RestResponse
import com.kysokApp.kysokApp.repo.ConfigRepo
import com.kysokApp.kysokApp.repo.CustomNativeQueryCreator
import com.kysokApp.kysokApp.utils.MetaDataConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


/**
 * @implNote This service has operations
 * to various config properties
 */
@Service
class ConfigServiceImpl(
    @Autowired
    private var configRepo: ConfigRepo?
) : ConfigService {

    @PersistenceContext
    private val em: EntityManager? = null

    override fun listConfigs(): RestResponse {
        return RestResponse(configRepo?.findAll(), HttpStatus.OK)
    }

    override fun createConfig(config: Config): RestResponse {
        return RestResponse(configRepo?.save(config), HttpStatus.OK)
    }

    override fun getConfig(name: String): RestResponse {
        return RestResponse(configRepo?.findByName(name)?.get(), HttpStatus.OK)
    }

    override fun updateConfig(name: String, config: Config): RestResponse {
        val updatedConfig = (configRepo?.findByName(name)?.get())?.update(config)
        if (updatedConfig != null) {
            return RestResponse(configRepo?.saveAndFlush(updatedConfig), HttpStatus.OK)
        }
        return RestResponse(null, HttpStatus.NOT_FOUND)
    }

    override fun deleteConfig(name: String): RestResponse {
        return RestResponse(configRepo?.findByName(name)?.get()?.let { configRepo?.delete(it) }, HttpStatus.OK)
    }

    override fun searchConfig(config: Config?): RestResponse {
        if (config != null) {
            var resultsList: List<Any?>? = null
            resultsList = CustomNativeQueryCreator().createQuery(config = config, em = em)?.resultList?.toList()
            val result = resultsList?.map { it ->
                val lst = it as Array<*>
                Config(
                    (lst[0]).toString().toLong(),
                    lst[2] as? String,
                    MetaDataConverter().convertToEntityAttribute(lst[1] as? String) as? Metadata,
                )
            }
            return RestResponse(result, HttpStatus.OK)
        }
        return RestResponse(null, HttpStatus.BAD_REQUEST)
    }


}
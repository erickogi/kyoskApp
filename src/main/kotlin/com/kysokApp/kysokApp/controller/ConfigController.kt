package com.kysokApp.kysokApp.controller

import com.kysokApp.kysokApp.entities.Config
import com.kysokApp.kysokApp.entities.Metadata
import com.kysokApp.kysokApp.entities.response.RestResponse
import com.kysokApp.kysokApp.service.ConfigService
import com.kysokApp.kysokApp.service.ConfigServiceImpl
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

@Component
@RequestMapping("/api")
class ConfigController {

    @Autowired
    private val configService: ConfigService? = null

    @GetMapping("/configs")
    @ApiOperation("List all Configs")
    fun list(): RestResponse? {
        return configService?.listConfigs()
    }
    @PostMapping("/configs")
    @ApiOperation("Create a Config")
    fun create(@RequestBody config: Config): RestResponse? {
        println("create")
        println("config")
       return configService?.createConfig(config)
    }
    @GetMapping("/configs/{name}")
    @ApiOperation("Get a Config by name")
    fun get(@PathVariable (value = "name") name: String): RestResponse? {
        return configService?.getConfig(name)
    }

    @PutMapping("/configs/{name}")
    @ApiOperation("Update a Config by name")
    fun update(@PathVariable (value = "name") name: String,@RequestBody config: Config): RestResponse? {
        return configService?.updateConfig(name,config)
    }


    @DeleteMapping("/configs/{name}")
    @ApiOperation("Delete a Config by name")
    fun delete(@PathVariable (value = "name") name: String): RestResponse?{
        return configService?.deleteConfig(name)
    }

    @GetMapping("/search")
    @ApiOperation("Search for Configs by metadata key-values")
    fun search(metadata: Config): RestResponse?{
        println("search")
        println(metadata.toString())
        return configService?.searchConfig(metadata)
    }
}
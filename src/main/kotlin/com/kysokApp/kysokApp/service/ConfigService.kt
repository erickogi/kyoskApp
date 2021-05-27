package com.kysokApp.kysokApp.service

import com.kysokApp.kysokApp.entities.Config
import com.kysokApp.kysokApp.entities.response.RestResponse


interface ConfigService {
    /**
     * List all configs
     * @return RestResponse(List<Configs>,HTTP Status)
     */
    fun listConfigs(): RestResponse
    /**
     * Create a new config
     * @return RestResponse(Configs,HTTP Status)
     */
    fun createConfig(config: Config): RestResponse
    /**
     * Get a config by name
     * @param name
     * @return RestResponse(Configs,HTTP Status)
     */
    fun getConfig(name: String): RestResponse
    /**
     * Update a config by name
     * @param name
     * @return RestResponse(Configs,HTTP Status)
     */
    fun updateConfig(name: String,config: Config): RestResponse
    /**
     * Delete a config by name
     * @param name
     * @return RestResponse(HTTP Status)
     */
    fun deleteConfig(name: String): RestResponse
    /**
     * Search for a configs by metadata key-values
     * @param config
     * @return RestResponse(List<Configs>,HTTP Status)
     */
    fun searchConfig(config: Config?): RestResponse
}
package com.kysokApp.kysokApp.repo

import com.kysokApp.kysokApp.entities.Config
import javax.persistence.EntityManager

class CustomNativeQueryCreator {

    /**
     * Create a query for the search operations
     * @param config Config request params that will be used for the seach
     * @param em Entity manager
     * @return query
     */
    fun createQuery(config: Config, em: EntityManager?): javax.persistence.Query? {
        //TODO : Create a query builder capable of searching by multiple values
        var query: javax.persistence.Query? = null
        if(config.metadata?.monitoring?.enabled != null){
            query = em?.createNativeQuery(
                "SELECT * " +
                        "FROM Config " +
                        "where JSON_CONTAINS(metadata->'\$.monitoring.enabled', '\"?1\"') "
            )?.setParameter(1, "\"" +config.metadata?.monitoring?.enabled+ "\"")

        }
        if(config.metadata?.limits?.cpu?.enabled != null){
            query = em?.createNativeQuery(
                "SELECT * " +
                        "FROM Config " +
                        "where JSON_CONTAINS(metadata->'\$.limits.cpu.enabled', ?1) "
            )?.setParameter(1, "\"" +config.metadata?.limits?.cpu?.enabled+ "\"")

        }
        if(config.metadata?.limits?.cpu?.value != null){
            query = em?.createNativeQuery(
                "SELECT * " +
                        "FROM Config " +
                        "where JSON_CONTAINS(metadata->'\$.limits.cpu.value', '\"?1\"') "
                , Config::class.java)?.setParameter(1, "\"" +config.metadata?.limits?.cpu?.value+ "\"")

        }

        return query
    }

}
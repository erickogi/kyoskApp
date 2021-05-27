package com.kysokApp.kysokApp.utils


import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.kysokApp.kysokApp.entities.Metadata
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
 class MetaDataConverter : AttributeConverter<Metadata, String> {
    private val objectMapper = ObjectMapper()

    override fun convertToDatabaseColumn(attribute: Metadata?):String {
        return if(attribute !=null ){
            objectMapper.writeValueAsString(attribute)
        }else{
            ""
        }
    }

    override fun convertToEntityAttribute(dbData: String?): Metadata {
        return if(dbData != null && dbData.isNotEmpty()){
            objectMapper.readValue(dbData,object : TypeReference<Metadata>() {})
        }else{
            Metadata()
        }
    }

}

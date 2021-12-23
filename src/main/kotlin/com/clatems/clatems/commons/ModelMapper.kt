package com.clatems.clatems.commons

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.util.stream.Stream

@Configuration
class ModelMapperConfig {

    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()

        modelMapper.configuration.isFieldMatchingEnabled = true
        modelMapper.configuration.fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PRIVATE
        return modelMapper
    }
}


@Component
class DtoConverter<EntityType>(private val modelMapper: ModelMapper) {

    fun <DtoType> mapEntityListToDtoList(list: List<EntityType>, dtoType: Class<DtoType>): Stream<DtoType> {
        return list.stream()
            .map { dummy ->
                modelMapper.map(dummy, dtoType)
            }
    }

    fun <DtoType> mapEntityToDto(entity: EntityType, dtoType: Class<DtoType>): DtoType {
        return modelMapper.map(entity, dtoType)
    }
}

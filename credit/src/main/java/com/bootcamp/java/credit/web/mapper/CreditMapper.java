package com.bootcamp.java.credit.web.mapper;

import com.bootcamp.java.credit.domain.Credit;
import com.bootcamp.java.credit.web.model.CreditModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CreditMapper {
    //Permite crear un modelo a una entidad de dominio
    Credit modelToEntity (CreditModel model);
    //de un dominio a una entidad de modelo para navegar entre las capas
    CreditModel entityToModel (Credit event);
    //actualizar una entidad existente con otra
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Credit entity, Credit updateEntity);
}

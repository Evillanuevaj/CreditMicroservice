package com.bootcamp.java.credit.web.mapper;

import com.bootcamp.java.credit.domain.Credit;
import com.bootcamp.java.credit.web.model.CreditModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-02T13:45:40-0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
)
@Component
public class CreditMapperImpl implements CreditMapper {

    @Override
    public Credit modelToEntity(CreditModel model) {
        if ( model == null ) {
            return null;
        }

        Credit.CreditBuilder credit = Credit.builder();

        credit.id( model.getId() );
        credit.identityNumber( model.getIdentityNumber() );
        credit.name( model.getName() );
        credit.lastName( model.getLastName() );
        credit.businessName( model.getBusinessName() );
        credit.email( model.getEmail() );
        credit.phoneNumber( model.getPhoneNumber() );
        credit.birthday( model.getBirthday() );
        credit.type( model.getType() );

        return credit.build();
    }

    @Override
    public CreditModel entityToModel(Credit event) {
        if ( event == null ) {
            return null;
        }

        CreditModel.CreditModelBuilder creditModel = CreditModel.builder();

        creditModel.id( event.getId() );
        creditModel.identityNumber( event.getIdentityNumber() );
        creditModel.name( event.getName() );
        creditModel.lastName( event.getLastName() );
        creditModel.businessName( event.getBusinessName() );
        creditModel.email( event.getEmail() );
        creditModel.phoneNumber( event.getPhoneNumber() );
        creditModel.birthday( event.getBirthday() );
        creditModel.type( event.getType() );

        return creditModel.build();
    }

    @Override
    public void update(Credit entity, Credit updateEntity) {
        if ( updateEntity == null ) {
            return;
        }

        entity.setIdentityNumber( updateEntity.getIdentityNumber() );
        entity.setName( updateEntity.getName() );
        entity.setLastName( updateEntity.getLastName() );
        entity.setBusinessName( updateEntity.getBusinessName() );
        entity.setEmail( updateEntity.getEmail() );
        entity.setPhoneNumber( updateEntity.getPhoneNumber() );
        entity.setBirthday( updateEntity.getBirthday() );
        entity.setType( updateEntity.getType() );
    }
}

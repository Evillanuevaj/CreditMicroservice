package com.bootcamp.java.credit.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;

@Data//genera get y set
@Builder//
@ToString//permite generar to String
@EqualsAndHashCode(of = {"identityNumber"})//permite comparacion
@AllArgsConstructor//constructor vacio con todas las propiedades
@NoArgsConstructor//
@Document(value = "credit")
public class Credit {
    @Id
    private String id;
    @NotNull
    @Indexed(unique = true)//valores unicos
    private String identityNumber;
    @NotNull
    private String idClient;
    @NotNull
    private String typeCredit;
    @NotNull
    private String balance;
    @NotNull
    @Indexed(unique = true)
    private LocalDate dateOpening;

}

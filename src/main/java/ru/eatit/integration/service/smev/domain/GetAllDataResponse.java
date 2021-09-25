package ru.eatit.integration.service.smev.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Данные, якобы полученные от СМЭВ (в рамках хакатона отдаем все данные которые нам нужны для профиля бедности)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllDataResponse {
    //Информация о пользователе
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthDate;
    private String birthPlace;
    private String address;
    private String mobilePhone;
    private String email;
    private Passport passport;
    private String snils;
    private String inn;
    private String gender;

    //Служебное поле
    //По нему мы понимаем надо ли обновлять поля
    private Long updateDate;

    // Поля необходимы для заполнения профиля бедности

    private Boolean признакБеременности;
    private Boolean признакМалоимущести;
    private Boolean признакПенсионера;
    private Boolean признакНаличияИнвалидности;
    private Boolean признакНаличияИнвалидностиIгруппы;
    private Boolean признакНаличияИнвалидностиIIгруппы;
    private Boolean признакНаличияИнвалидностиIIIгруппы;
    private Boolean признакТрудоспособности;
    private Boolean признакНахожденияВДекретномОтпуске;
    private Boolean признакНаличияСтатусаБезработного;
    private Boolean признакПожилогоВозраста;
    private Boolean признакОдинокийРодитель;
    private Boolean признакЖертваРепрессий;
    private Boolean признакВетеранТруда;
    private Boolean признакТруженикТыла;
    private Boolean признакНаличияНесовершеннолетнегоРебенка;
    private Boolean признакЖенатостиЗамужнести;
    private Boolean признакНаличияРебенкаИнвалида;
    private Boolean признакУклоненияОтАлиментовВторогоРодителя;
    private Boolean признакОпекунства;
    private Boolean признакПопечительства;

}


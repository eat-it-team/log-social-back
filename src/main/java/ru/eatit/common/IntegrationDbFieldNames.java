package ru.eatit.common;

/**
 * Константы для представления полей из общей базы данных.
 * Содержат только НОРМАЛИЗОВАННЫЕ данные, приведенные к единому формату.
 * <p>
 * Эти названия полей попадают в названия полей в json на фронт,
 * в ru.eatit.common.entity.kafkaKafkaRequest.details,
 * в ru.eatit.poor_regisry.repository.mongo.entity.User.details
 * <p>
 * Для ускорения разработки - в виде enum, в дальнейшем - перенос в бд
 */
public enum IntegrationDbFieldNames {
    USER_ID("esia_id", "Уникальные идентификатор пользователя из ЕСИА", LogicalType.STRING),
    USER_FIO("fio", "Нормализованные ФИО гражданина", LogicalType.STRING),
    USER_AGE("age", "Нормализованный возраст", LogicalType.AGE),
    USER_INN("inn", "ИНН в формате ", LogicalType.INN),
    USER_BIRTH_DATE("birth_date", "Дата в формате 31.11.2000", LogicalType.DATE),
    USER_SNILS("snils", "СНИЛС", LogicalType.SNILS),
    COMMENT("comment", "Комментарий", LogicalType.STRING),
    PASSPORT("passport", "Паспорт в формате 3456 345678", LogicalType.STRING),
    SALARY("salary", "Зарплата в месяц", LogicalType.MONEY),
    FIRST_NAME("firstName", "Имя", LogicalType.STRING),
    LAST_NAME("lastName", "Фамилия", LogicalType.STRING),
    MIDDLE_NAME("secondName", "Отчество", LogicalType.STRING),
    ADDRESS("address", "Адрес", LogicalType.STRING),
    GEO_ADDRESS("geoAddress", "ГеоТочки адреса", LogicalType.GEO),
    BIRTH_PLACE("birth_place", "Место рождения", LogicalType.STRING),
    EMAIL("email", "email", LogicalType.STRING),
    MOBILE_PHONE("mobilePhone", "Мобильный телефон", LogicalType.STRING),
    GENDER("gender", "Пол в Формате Male/Female", LogicalType.STRING),

    UPDATE_DATE("modified_at", "Дата пследнего обновления данных", LogicalType.STRING),

    //TODO: переименовать потом
    признакБеременности("признакБеременности", "", LogicalType.BOOLEAN),
    признакМалоимущести("poverty", "", LogicalType.BOOLEAN),
    признакПенсионера("pensioner", "", LogicalType.BOOLEAN),
    признакНаличияИнвалидности("признакНаличияИнвалидности", "", LogicalType.BOOLEAN),
    признакНаличияИнвалидностиIгруппы("признакНаличияИнвалидностиIгруппы", "", LogicalType.BOOLEAN),
    признакНаличияИнвалидностиIIIгруппы("признакНаличияИнвалидностиIIIгруппы", "", LogicalType.BOOLEAN),
    признакТрудоспособности("признакТрудоспособности", "", LogicalType.BOOLEAN),
    признакНахожденияВДекретномОтпуске("признакНахожденияВДекретномОтпуске", "", LogicalType.BOOLEAN),
    признакНаличияСтатусаБезработного("worker", "", LogicalType.BOOLEAN),
    признакПожилогоВозраста("признакПожилогоВозраста", "", LogicalType.BOOLEAN),
    признакОдинокийРодитель("признакОдинокийРодитель", "", LogicalType.BOOLEAN),
    признакЖертваРепрессий("признакЖертваРепрессий", "", LogicalType.BOOLEAN),
    признакВетеранТруда("признакВетеранТруда", "", LogicalType.BOOLEAN),
    признакТруженикТыла("признакТруженикТыла", "", LogicalType.BOOLEAN),
    признакНаличияНесовершеннолетнегоРебенка("haveChild", "", LogicalType.BOOLEAN),
    признакЖенатостиЗамужнести("married", "", LogicalType.BOOLEAN),
    признакНаличияРебенкаИнвалида("признакНаличияРебенкаИнвалида", "", LogicalType.BOOLEAN),
    признакУклоненияОтАлиментовВторогоРодителя("признакУклоненияОтАлиментовВторогоРодителя", "", LogicalType.BOOLEAN),
    признакОпекунства("признакОпекунства", "", LogicalType.BOOLEAN),
    признакПопечительства("признакПопечительства", "", LogicalType.BOOLEAN),
    признакНаличияИнвалидностиIIгруппы("признакНаличияИнвалидностиIIгруппы", "", LogicalType.BOOLEAN);

    private String name;
    private String description;
    private LogicalType logicalType;

    IntegrationDbFieldNames(String name, String description, LogicalType logicalType) {
        this.name = name;
        this.description = description;
        this.logicalType = logicalType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LogicalType getLogicalType() {
        return logicalType;
    }
}

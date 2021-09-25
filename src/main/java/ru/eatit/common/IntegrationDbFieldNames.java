package ru.eatit.common;

/**
 * Константы для представления полей из общей базы данных.
 * Содержат только НОРМАЛИЗОВАННЫЕ данные, приведенные к единому формату.
 * <p>
 * Эти названия полей попадают в названия полей в json на фронт,
 * в ru.eatit.common.entity.kafkaKafkaRequest.details,
 * в ru.eatit.poor_regisry.repository.mongo.entity.User.details
 */
public enum IntegrationDbFieldNames {
    USER_ID("id", "Уникальные идентификатор пользователя. Возможно СНИЛС", LogicalType.SNILS),
    USER_FIO("fio", "Нормализованные ФИО гражданина", LogicalType.FIO),
    USER_AGE("age", "Нормализованный возраст", LogicalType.AGE),
    USER_INN("inn", "ИНН в формате ", LogicalType.INN),
    USER_DATE("birth_date", "Дата в формате 31.11.2000", LogicalType.DATE),
    USER_SNILS("snils", "СНИЛС", LogicalType.SNILS),
    COMMENT("comment", "Комментарий", LogicalType.STRING),
    PASSPORT("passport", "Паспорт в формате 3456 345678", LogicalType.PASSPORT),
    SALARY("salary", "Зарплата в месяц", LogicalType.MONEY);

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

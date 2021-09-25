package ru.eatit.common;

/**
 * Класс для представления, данных из внешнего источника.
 * Каждое поле из таблицы бд мы сопостовляем с конкретным типом,
 * и в зависимости от типа проводим те или иные процедуры валидации или нормализации данных
 */
public enum LogicalType {
    INN("inn"),
    FIO("fio"),
    DATETIME("datetime"),
    DATE("date"),
    SNILS("snils"),
    STRING("string"),
    MONEY("money"),
    INTEGER("integer"),
    PASSPORT("passport"),
    AGE("age"),
    PHONE("phone"),
    ADDRESS("address"),
    EMAIL("email");


    private String name;

    LogicalType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

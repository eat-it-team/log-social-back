package ru.eatit.common.api;

import ru.eatit.common.entity.extract.ExtractResult;

import java.util.List;

/**
 * Мы делаем запрос к какому-то РЕСТ сервису или бд. "Select * from ...."
 * На выходе получаем List<Object> с возможно с какими-то вложенными структурами внутри (в случае REST или SOAP)
 * Мы должны преобразовать ее в Map<String, Map<String, Object>, чтобы получилась плоская структура,
 * где ключем первой мапы будет идентификатор пользователя, ключем второй мапы будет название поля,
 * занчением второй мапы будет значением из этого поля
 * <p>
 * Допустим сделали запрос "Select id, inn, snils from db1"
 * Получили
 * id  inn    snils
 * 1   inn1   snils1
 * 2   inn2   snils2
 * <p>
 * После преобразования получили
 * < 1, map < (inn = inn1), (snils = snils1))>  >
 * < 1, map < (inn = inn2), (snils = snils2))>  >
 * <p>
 * <p>
 * Cделали REST запрос
 * Получили
 * <p>
 * [
 * {
 * "id": 1,
 * "passport: {
 * "series": 1234,
 * "number": 123456
 * }
 * },
 * {
 * *    "id": 3,
 * *    "passport: {
 * *        "series": 4321,
 * *        "number": 123456
 * *    }
 * * }
 * ]
 * <p>
 * После преобразования получили Map<String, Map<String, Object>
 * < 1, map < (passport.series = 1234), (passport.number = 123456))>  >
 * < 3, map < (passport.series = 4321), (passport.number = 123456))>  >
 * <p>
 * В рамках класса описывается мапа соответствия
 * <p>
 * поле из бд -> поле в итоговой таблице
 * MAP<String of db.fieldName, IntegrationDbFieldNames>
 * <p>
 * На выходе будет струкура JSONObject details с успешными полями
 * и структура <db.fieldName => <Значение + ошибка валидации>
 */
public interface CustomOutsideServiceExtractor<R, T> {
    /**
     * Внутри проходится по rawData, пытается нормализовать и провалидировать данные
     * Формирует две структуры одну с успешными данными, вторую с ошибками
     *
     * @param serviceName название внешнего сервис
     * @param rawData     данные полученные с внешнего сервис
     * @return результат попытки нормализации данных
     */
    /**
     *  Внутри проходится по request/response, пытается нормализовать и провалидировать данные
     *  Формирует две структуры одну с успешными данными, вторую с ошибками
     * @param serviceName название внешнего сервис
     * @param request  запрос к внешнему сервису
     * @param response  ответ от внешнего сервиса
     * @return результат нормализации
     */
    ExtractResult extract(String serviceName, R request, T response);

}

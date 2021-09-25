package ru.eatit.common.entity.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

/**
 * Класс для передачи значений через kafka.
 * Предполагается, что сервис интеграции будет её заполнять, переводить в json,
 * а другие сервисы слушать (с разными groupId) этот топик и обновлять свои бд.
 * Реестр малоимущих - бд  с реестром, сервис общих данных - бд с event sourcing
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KafkaRequest {

    //список полей и их значений из источника, которые надо обновить
    private JSONObject details;
    //идентификатор обновления. По шедулеру начнется процесс обновления из первичной бд (у него будет какой-то порядковый номер)
    private String processId;
    //на будущее, если с фронта админ что-то решит поправить
    private String comment;
    //дата обновления строки в первичной бд в формате "yyyy-MM-dd'T'HH:mm:ss.SSS"
    private String createdAt;
    //источник данных (название бд, логин пользователя, технического сервиса)
    private String createdBy;
    //тип события. поначалу один - обновление из первичной бд, потом могут добавиться другие (обновление админом)
    private String eventType;
    //мапа служебных полей для поддержки новых типов событий в будущем. туда пойдут поля, которым не хватило места
    private JSONObject additional;

    public void addField(String key, Object value) {
        if (details == null) {
            details = new JSONObject();
        }
        details.put(key, value);
    }
}

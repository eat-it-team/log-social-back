package ru.eatit.common.api;

/**
 * Синхронная реализация получения данных с внешнего сервиса
 * В дальнейшем планируетс Асинхронная(возвращает обертку в Future)
 * и на основе пуллинга
 * (отправляет запрос, получает taskId, раз В N времени пытается получить данные по taskId)
 *
 * @param <R> request
 * @param <T> response
 */
public interface CustomOutsideServiceSyncClient<R, T> {

    /**
     * Синхронно получает данные с внешнего сервиса
     *
     * @param request request
     * @return response
     */
    T getData(R request);
}

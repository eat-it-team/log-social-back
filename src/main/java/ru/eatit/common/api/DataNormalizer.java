package ru.eatit.common.api;

import ru.eatit.common.LogicalType;
import ru.eatit.common.entity.extract.NormalizeResult;


public interface DataNormalizer{

    /**
     * Валидирует, нормализует данные
     *
     * @param rawValue ненормализованные данные
     * @return в случае успехы success = true, в случае ошибки валидации - successFalse
     */
    NormalizeResult normalize(String rawValue);
}

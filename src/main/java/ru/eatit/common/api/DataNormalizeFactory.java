package ru.eatit.common.api;

import ru.eatit.common.LogicalType;

public interface DataNormalizeFactory {

    DataNormalizer getDataNormalizer(LogicalType logicalType);
}

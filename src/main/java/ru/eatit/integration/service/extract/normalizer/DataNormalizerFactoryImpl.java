package ru.eatit.integration.service.extract.normalizer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.eatit.common.LogicalType;
import ru.eatit.common.api.DataNormalizeFactory;
import ru.eatit.common.api.DataNormalizer;

@Service
@AllArgsConstructor
public class DataNormalizerFactoryImpl implements DataNormalizeFactory {

    private final InnDataNormalizer innDataNormalizer;
    private final FioDataNormalizer fioDataNormalizer;
    private final StringDataNormalizer stringDataNormalizer;
    private final SnilsDataNormalizer snilsDataNormalizer;
    @Override
    public DataNormalizer getDataNormalizer(LogicalType logicalType) {
        switch (logicalType) {
            case INN:
                return innDataNormalizer;
            case STRING:
                return stringDataNormalizer;
            case FIO:
                return fioDataNormalizer;
            case SNILS:
                return snilsDataNormalizer;

            default:
                throw new UnsupportedOperationException(logicalType.getName() + "еще не реализован!");
        }
    }
}

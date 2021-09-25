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
    private final BooleanDataNormalizer booleanDataNormalizer;
    private final DateDataNormalizer dateNormalizer;
    private final PassportDataNormalizer passportDataNormalizer;
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
            case BOOLEAN:
                return booleanDataNormalizer;
            case DATE:
                return dateNormalizer;
            case PASSPORT:
                return passportDataNormalizer;

            default:
                throw new UnsupportedOperationException(logicalType.getName() + "еще не реализован!");
        }
    }
}

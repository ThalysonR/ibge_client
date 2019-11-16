package br.com.evoluum.ibge.services.IBGEDataMappers;

public enum IBGEDataTypeInput {
    json(new JsonMapper()),
    csv(new CsvMapper());

    public IBGEDataMapper mapper;
    IBGEDataTypeInput(IBGEDataMapper mapper) {
        this.mapper = mapper;
    }
}
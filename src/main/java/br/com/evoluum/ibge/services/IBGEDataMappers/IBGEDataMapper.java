package br.com.evoluum.ibge.services.IBGEDataMappers;

import java.io.OutputStream;

import br.com.evoluum.ibge.domain.Municipio;

public interface IBGEDataMapper {
    public void writeStart(OutputStream outputStream);
    public void writeItem(OutputStream oStream, Municipio municipio);
    public void writeEnd(OutputStream outputStream);
}
package com.pnu.db.Graduates.Application.helpers;

public interface Mapper<E, D> {
    D mapEntityToDto(E e);
    E mapDtoToEntity(D d);
}

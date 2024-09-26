package ir.maktabsharif115.springboot.mapper;

import java.util.List;

public interface BaseMapper<E, D> {

    E convertDtoToEntity(D d);


    D convertEntityToDTO(E e);

    List<E> convertDtoToEntity(List<D> d);


    List<D> convertEntityToDTO(List<E> e);
}

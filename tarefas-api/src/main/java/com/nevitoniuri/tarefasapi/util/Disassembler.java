package com.nevitoniuri.tarefasapi.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface Disassembler<E, D> {

    D toDTO(E entity);

    default List<D> toDTOList(List<E> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

}

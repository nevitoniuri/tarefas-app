package com.nevitoniuri.tarefasapi.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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

    default Page<D> toDTOPage(Pageable pageable, Page<E> page) {
        if (page == null) {
            return null;
        }
        return new PageImpl<>(this.toDTOList(page.getContent()), pageable, page.getTotalElements());
    }
}

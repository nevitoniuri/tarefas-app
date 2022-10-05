package com.nevitoniuri.tarefasapi.util;

public interface Assembler<R, E> {
    E toEntity(R request);
}

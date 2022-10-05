package com.nevitoniuri.tarefasapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TarefaDTO {
    private Long id;
    private String descricao;
    private Boolean concluida;
}

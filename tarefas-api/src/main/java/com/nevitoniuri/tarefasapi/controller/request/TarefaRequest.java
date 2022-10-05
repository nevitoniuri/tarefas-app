package com.nevitoniuri.tarefasapi.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TarefaRequest {

    @NotEmpty(message = "A descrição não pode ser vazia")
    @NotNull(message = "A descrição não pode ser nula")
    private String descricao;
}

package com.nevitoniuri.tarefasapi.controller.disassembler;

import com.nevitoniuri.tarefasapi.model.Tarefa;
import com.nevitoniuri.tarefasapi.model.dto.TarefaDTO;
import com.nevitoniuri.tarefasapi.util.Disassembler;
import org.springframework.stereotype.Component;

@Component
public class TarefaDisassembler implements Disassembler<Tarefa, TarefaDTO> {

    @Override
    public TarefaDTO toDTO(Tarefa entidade) {
        return TarefaDTO.builder()
                .id(entidade.getId())
                .descricao(entidade.getDescricao())
                .concluida(entidade.getConcluida())
                .build();
    }
}

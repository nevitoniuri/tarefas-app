package com.nevitoniuri.tarefasapi.controller.assembler;

import com.nevitoniuri.tarefasapi.controller.request.TarefaRequest;
import com.nevitoniuri.tarefasapi.model.Tarefa;
import com.nevitoniuri.tarefasapi.util.Assembler;
import org.springframework.stereotype.Component;

@Component
public class TarefaAssembler implements Assembler<TarefaRequest, Tarefa> {

    @Override
    public Tarefa toEntity(TarefaRequest request) {
        var tarefa = new Tarefa();
        copiarParaEntidade(request, tarefa);
        return tarefa;
    }

    public void copiarParaEntidade(TarefaRequest request, Tarefa entidade) {
        entidade.setDescricao(request.getDescricao());
    }
}

package com.nevitoniuri.tarefasapi.controller;

import com.nevitoniuri.tarefasapi.controller.assembler.TarefaAssembler;
import com.nevitoniuri.tarefasapi.controller.disassembler.TarefaDisassembler;
import com.nevitoniuri.tarefasapi.controller.request.TarefaRequest;
import com.nevitoniuri.tarefasapi.model.dto.TarefaDTO;
import com.nevitoniuri.tarefasapi.service.TarefaService;
import com.nevitoniuri.tarefasapi.util.RecursoCriado;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;
    private final TarefaAssembler tarefaAssembler;
    private final TarefaDisassembler tarefaDisassembler;

    @GetMapping
    public Page<TarefaDTO> listar(@PageableDefault(sort = "descricao") Pageable pageable) {
        return tarefaDisassembler.toDTOPage(pageable, tarefaService.listar(pageable));
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid TarefaRequest request) {
        var tarefaSalva = tarefaService.salvar(tarefaAssembler.toEntity(request));
        return ResponseEntity.created(RecursoCriado.location(tarefaSalva.getId())).build();
    }

    @PutMapping("{id}")
    public void editar(@PathVariable Long id, @RequestBody @Valid TarefaRequest request) {
        var tarefa = tarefaService.buscarPorId(id);
        tarefaAssembler.copiarParaEntidade(request, tarefa);
        tarefaService.salvar(tarefa);
    }

    @PutMapping("{id}/concluir")
    public void concluir(@PathVariable Long id) {
        tarefaService.concluir(id);
    }

    @PutMapping("{id}/retomar")
    public void retomar(@PathVariable Long id) {
        tarefaService.retomar(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tarefaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}

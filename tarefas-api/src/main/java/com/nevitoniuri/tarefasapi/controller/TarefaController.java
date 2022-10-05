package com.nevitoniuri.tarefasapi.controller;

import com.nevitoniuri.tarefasapi.controller.assembler.TarefaAssembler;
import com.nevitoniuri.tarefasapi.controller.disassembler.TarefaDisassembler;
import com.nevitoniuri.tarefasapi.controller.request.TarefaRequest;
import com.nevitoniuri.tarefasapi.model.dto.TarefaDTO;
import com.nevitoniuri.tarefasapi.service.TarefaService;
import com.nevitoniuri.tarefasapi.util.RecursoCriado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TarefaController {

    private final TarefaService tarefaService;
    private final TarefaAssembler tarefaAssembler;
    private final TarefaDisassembler tarefaDisassembler;

    @GetMapping
    public List<TarefaDTO> listar() {
        return tarefaDisassembler.toDTOList(tarefaService.listar());
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
        tarefaService.concluir(tarefaService.buscarPorId(id));
    }

    @PutMapping("{id}/retomar")
    public void retomar(@PathVariable Long id) {
        tarefaService.retomar(tarefaService.buscarPorId(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tarefaService.excluir(tarefaService.buscarPorId(id));
        return ResponseEntity.noContent().build();
    }

}

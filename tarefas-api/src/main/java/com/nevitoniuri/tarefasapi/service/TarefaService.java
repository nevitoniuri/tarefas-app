package com.nevitoniuri.tarefasapi.service;

import com.nevitoniuri.tarefasapi.exception.RecursoDuplicadoException;
import com.nevitoniuri.tarefasapi.exception.RecursoNaoEncontradoException;
import com.nevitoniuri.tarefasapi.model.Tarefa;
import com.nevitoniuri.tarefasapi.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public Page<Tarefa> listar(Pageable pageable) {
        return tarefaRepository.findAll(pageable);
    }

    @Transactional
    public Tarefa salvar(Tarefa tarefa) {
        if (existe(tarefa)) {
            throw new RecursoDuplicadoException("Tarefa já cadastrada");
        }
        return tarefaRepository.save(tarefa);
    }

    public boolean existe(Tarefa tarefa) {
        return tarefaRepository.existsTarefaByDescricaoIgnoreCase(tarefa.getDescricao());
    }

    @Transactional
    public void excluir(Long id) {
        tarefaRepository.delete(buscarPorId(id));
    }

    @Transactional
    public void concluir(Long id) {
        var tarefa = buscarPorId(id);
        tarefa.concluir();
        tarefaRepository.save(tarefa);
    }

    @Transactional
    public void retomar(Long id) {
        var tarefa = buscarPorId(id);
        tarefa.retomar();
        tarefaRepository.save(tarefa);
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tarefa não encontrada"));
    }
}

package com.nevitoniuri.tarefasapi.repository;

import com.nevitoniuri.tarefasapi.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    boolean existsByDescricaoIgnoreCase (String descricao);

    boolean existsTarefaByDescricaoIgnoreCase(String descricao);

    Tarefa findByDescricaoIgnoreCase(String descricao);

}

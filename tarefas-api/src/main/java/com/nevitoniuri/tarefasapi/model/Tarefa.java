package com.nevitoniuri.tarefasapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tarefa", schema = "public")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, name = "descricao")
    private String descricao;

    @Column(nullable = false, name = "concluida")
    private Boolean concluida;

    @PrePersist
    public void preSave() {
        this.concluida = Boolean.FALSE;
    }

    public void concluir() {
        this.concluida = Boolean.TRUE;
    }

    public void retomar() {
        this.concluida = Boolean.FALSE;
    }

}

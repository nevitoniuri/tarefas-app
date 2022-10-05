import { Component, OnInit } from '@angular/core';
import { Tarefa } from '../model/Tarefa';
import { TarefaService } from '../service/tarefa.service';

@Component({
  selector: 'app-tarefa',
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css']
})
export class TarefaComponent implements OnInit {

  tarefas: Tarefa[] = [];

  constructor(private tarefaService: TarefaService) { }

  ngOnInit() {
    this.listarTarefas();
  }

  listarTarefas() {
    this.tarefaService.listarTarefas().subscribe(
      (tarefas: Tarefa[]) => {
        for (let i = 0; i < tarefas.length; i++) {
          let tarefa = tarefas[i] as Tarefa;
          this.tarefas.push(tarefa);
        }
      },
      error => {
        console.log(error);
      }
    )
  }

  criarTarefa(descricao: string): void {
    descricao = descricao.trim();
    if (!descricao) { return; }
    this.tarefaService.criarTarefa({ descricao } as Tarefa).subscribe(tarefa => {
      this.tarefas.push(tarefa);
    });
  }

  toggleConcluida(tarefa: Tarefa): void {
    tarefa.concluida = !tarefa.concluida;
  }
  
  toggleConcluida2(tarefa: Tarefa): void {
    if (tarefa.concluida) {
      this.tarefaService.retomarTarefa(tarefa.id).subscribe(tarefa => {
        tarefa.concluida = false;
      });
    } else {
      this.tarefaService.concluirTarefa(tarefa.id);
    }
  }

}

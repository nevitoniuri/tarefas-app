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

  ngOnInit() {
    this.listarTarefas();
  }
}

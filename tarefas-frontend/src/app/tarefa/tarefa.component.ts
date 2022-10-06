import { Component, OnInit } from '@angular/core';
import { Tarefa } from '../model/Tarefa';
import { TarefaService } from '../service/tarefa.service';

@Component({
  selector: 'app-tarefa',
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css']
})
export class TarefaComponent implements OnInit {

  tarefaEditada: Tarefa = new Tarefa(0, '', false);
  tarefas: Tarefa[] = [];
  mostrarEditarDialog: boolean = false;

  constructor(private tarefaService: TarefaService) { }

  ngOnInit() {
    this.listarTarefas();
  }

  listarTarefas() {
    this.tarefaService.listar().subscribe(
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
    this.tarefaService.criar({ descricao } as Tarefa).subscribe(tarefa => {
      this.tarefas.push(tarefa);
    });
    location.reload();
  }

  mostrarModalEditar(tarefa: Tarefa): void {
    this.mostrarEditarDialog = true;

    this.tarefaEditada.id = tarefa.id;
    this.tarefaEditada.descricao = tarefa.descricao;
    this.tarefaEditada.concluida = tarefa.concluida;
  }

  handleEditar(descricao: string): void {
    const id = this.tarefaEditada.id;
    this.tarefaService.editar2(id, descricao).subscribe(tarefa => {
      console.log(tarefa);
    });
    location.reload();
  }

  handleDeletar(tarefa: Tarefa): void {
    this.tarefas = this.tarefas.filter(h => h !== tarefa);
    this.tarefaService.deletar(tarefa.id).subscribe();
    location.reload();
  }

  toggleConcluida(tarefa: Tarefa): void {
    tarefa.concluida = !tarefa.concluida;
    if (tarefa.concluida) {
      this.tarefaService.retomar(tarefa).subscribe(tarefa => {
        tarefa.concluida = false;
      });
      location.reload();
    } else {
      this.tarefaService.concluir(tarefa).subscribe(tarefa => {
        tarefa.concluida = true;
      });
      location.reload();
    }
  }

}

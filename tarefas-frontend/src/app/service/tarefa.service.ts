import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tarefa } from '../model/Tarefa';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {

  url: string = "http://localhost:8080/tarefas/";

  constructor(private http: HttpClient) { }

  listarTarefas(): Observable<Tarefa[]> {
    return this.http.get<Tarefa[]>(this.url);
  }

  criarTarefa(tarefa: Tarefa): Observable<Tarefa> {
    return this.http.post<Tarefa>(this.url, tarefa);
  }

  atualizarTarefa(tarefa: Tarefa): Observable<Tarefa> {
    return this.http.put<Tarefa>(this.url, tarefa);
  }

  deletarTarefa(id: number): Observable<Tarefa> {
    return this.http.delete<Tarefa>(this.url + id);
  }

  concluirTarefa(id: number): Observable<Tarefa> {
    return this.http.put<Tarefa>(this.url + id + "/concluir", null);
  }

  retomarTarefa(id: number): Observable<Tarefa> {
    return this.http.put<Tarefa>(this.url + id + "/retomar", null);
  }

}

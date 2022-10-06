import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tarefa } from '../model/Tarefa';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {

  url: string = "http://localhost:8080/tarefas/";

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  listar(): Observable<Tarefa[]> {
    return this.http.get<Tarefa[]>(this.url);
  }

  criar(tarefa: Tarefa): Observable<Tarefa> {
    return this.http.post<Tarefa>(this.url, tarefa);
  }

  editar(tarefa: Tarefa): Observable<Tarefa> {
    const body = { descricao: tarefa.descricao };
    return this.http.put<Tarefa>(this.url + tarefa.id, body, this.httpOptions);
  }

  editar2(id: number, descricao: string): Observable<Tarefa> {
    const body = { descricao: descricao };
    return this.http.put<Tarefa>(this.url + id, body, this.httpOptions);
  }

  concluir(tarefa: Tarefa): Observable<Tarefa> {
    return this.http.put<Tarefa>(this.url + tarefa.id + "/concluir", tarefa);
  }

  retomar(tarefa: Tarefa): Observable<Tarefa> {
    return this.http.put<Tarefa>(this.url + tarefa.id + "/retomar", tarefa);
  }

  deletar(id: number): Observable<Tarefa> {
    return this.http.delete<Tarefa>(this.url + id);
  }

}

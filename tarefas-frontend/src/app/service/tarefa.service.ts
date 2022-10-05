import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tarefa } from '../model/Tarefa';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {

  url: string = "http://localhost:8080/tarefas";

  constructor(private http: HttpClient) { }

  listarTarefas(): Observable<Tarefa[]> {
    return this.http.get<Tarefa[]>(this.url);
  }

}

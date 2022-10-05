import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { TarefaComponent } from './tarefa/tarefa.component';
import { HttpClientModule } from '@angular/common/http';
import {ToggleButtonModule} from 'primeng/togglebutton';


@NgModule({
  declarations: [
    AppComponent,
    TarefaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    FormsModule,
    InputTextModule,
    TableModule,
    HttpClientModule,
    ToggleButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

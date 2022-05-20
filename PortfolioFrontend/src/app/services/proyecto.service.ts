import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Proyecto } from '../models/proyecto';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {

  proyectoURL = 'http://localhost:8080/proyecto/'

  constructor(
    private httpClient: HttpClient
  ) { }

  public list():Observable<Proyecto[]> {
    return this.httpClient.get<Proyecto[]>(this.proyectoURL+'list');
  }

  public save(proyecto:any):Observable<any>{
    return this.httpClient.post<any>(this.proyectoURL+'save', proyecto);
  }

  public update(id:number, proyecto:any):Observable<any>{
    return this.httpClient.put<any>(this.proyectoURL+'update/'+id, proyecto);
  }

  public delete(id:number):Observable<any>{
    return this.httpClient.delete<any>(this.proyectoURL+'delete/'+id)
  }
}

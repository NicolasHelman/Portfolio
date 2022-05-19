import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Experiencia } from '../models/experiencia';

@Injectable({
  providedIn: 'root'
})
export class ExperienciaService {

  experienciaURL = 'http://localhost:8080/experiencia/'

  constructor(
    private httpClient: HttpClient
  ) { }

  public list():Observable<Experiencia[]> {
    return this.httpClient.get<Experiencia[]>(this.experienciaURL+'list');
  }

  public save(experiencia:any):Observable<any>{
    return this.httpClient.post<any>(this.experienciaURL+'save', experiencia);
  }

  public update(id:number, experiencia:any):Observable<any>{
    return this.httpClient.put<any>(this.experienciaURL+'update/'+id, experiencia);
  }

  public delete(id:number):Observable<any>{
    return this.httpClient.delete<any>(this.experienciaURL+'delete/'+id)
  }
}

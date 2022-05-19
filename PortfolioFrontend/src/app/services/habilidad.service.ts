import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Habilidad } from '../models/habilidad';

@Injectable({
  providedIn: 'root'
})
export class HabilidadService {

  habilidadURL = 'http://localhost:8080/habilidad/'

  constructor(
    private httpClient: HttpClient
  ) { }

  public list():Observable<Habilidad[]> {
    return this.httpClient.get<Habilidad[]>(this.habilidadURL+'list');
  }

  public save(habilidad:Habilidad):Observable<any>{
    return this.httpClient.post<any>(this.habilidadURL+'save', habilidad);
  }

  public update(id:number, habilidad:Habilidad):Observable<any>{
    return this.httpClient.put<any>(this.habilidadURL+'update/'+id, habilidad);
  }

  public delete(id:number):Observable<any>{
    return this.httpClient.delete<any>(this.habilidadURL+'delete/'+id)
  }
}

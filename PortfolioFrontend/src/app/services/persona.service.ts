import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Persona } from '../models/persona';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  personaURL = 'http://localhost:8080/persona/'

  constructor(
    private httpClient: HttpClient
  ) { }

  public list():Observable<Persona[]> {
    return this.httpClient.get<Persona[]>(this.personaURL+'list');
  }

  public save(persona:any):Observable<any>{
    return this.httpClient.post<any>(this.personaURL+'save', persona);
  }

  public update(id:number, persona:any):Observable<any>{
    return this.httpClient.put<any>(this.personaURL+'update/'+id,persona);
  }

  public delete(id:number):Observable<any>{
    return this.httpClient.delete<any>(this.personaURL+'delete/'+ id)
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Educacion } from '../models/educacion';

@Injectable({
  providedIn: 'root'
})
export class EducacionService {

  educacionURL = 'http://localhost:8080/educacion/'

  constructor(
    private httpClient: HttpClient
  ) { }

  public list():Observable<Educacion[]> {
    return this.httpClient.get<Educacion[]>(this.educacionURL+'list');
  }

  public save(educacion:any):Observable<any>{
    return this.httpClient.post<any>(this.educacionURL+'save', educacion);
  }

  public update(id:number, educacion:any):Observable<any>{
    return this.httpClient.put<any>(this.educacionURL+'update/'+id,educacion);
  }

  public delete(id:number):Observable<any>{
    return this.httpClient.delete<any>(this.educacionURL+'delete/'+id)
  }
}

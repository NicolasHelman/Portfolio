import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AcercaDe } from '../models/acerca-de';

@Injectable({
  providedIn: 'root'
})
export class AcercaDeService {

  acercaDeURL = 'http://localhost:8080/acercaDe/'

  constructor(
    private httpClient: HttpClient
  ) { }

  public list():Observable<AcercaDe[]> {
    return this.httpClient.get<AcercaDe[]>(this.acercaDeURL +'list');
  }

  public save(acercaDe:any):Observable<any>{
    return this.httpClient.post<any>(this.acercaDeURL+'save', acercaDe);
  }

  public update(id:number, acercaDe:any):Observable<any>{
    return this.httpClient.put<any>(this.acercaDeURL+'update/'+id,acercaDe);
  }

  public delete(id:number):Observable<any>{
    return this.httpClient.delete<any>(this.acercaDeURL+'delete/'+id)
  }
}

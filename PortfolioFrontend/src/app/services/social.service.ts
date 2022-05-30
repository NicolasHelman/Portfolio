import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Social } from '../models/social';

@Injectable({
  providedIn: 'root'
})
export class SocialService {

  socialURL = 'http://localhost:8080/social/'

  constructor(
    private httpClient: HttpClient
  ) { }

  public list():Observable<Social[]> {
    return this.httpClient.get<Social[]>(this.socialURL+'list');
  }

  public save(social:any):Observable<any>{
    return this.httpClient.post<any>(this.socialURL+'save', social);
  }

  public update(id:number, social:any):Observable<any>{
    return this.httpClient.put<any>(this.socialURL+'update/'+id, social);
  }

  public delete(id:number):Observable<any>{
    return this.httpClient.delete<any>(this.socialURL+'delete/'+id)
  }
}

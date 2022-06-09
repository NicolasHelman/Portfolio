import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactoService {

  contactoURL = 'http://localhost:8080/contacto/'

  constructor(
    private httpClient: HttpClient
  ) { }

  public save(contacto:any):Observable<any>{
    return this.httpClient.post<any>(this.contactoURL+'save', contacto);
  }

}

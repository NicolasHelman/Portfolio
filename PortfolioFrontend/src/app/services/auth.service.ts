import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtDto } from '../models/jwt-dto';
import { LoginUsuario } from '../models/login-usuario';
import { NuevoUsuario } from '../models/nuevo-usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authURL = 'http://localhost:8080/auth/'

  constructor(private httpClient: HttpClient) { }

  public new(nuevoUsuario: NuevoUsuario): Observable<any> {
    return this.httpClient.post<any>(this.authURL+'nuevo',nuevoUsuario);
  }

  public login(loginUsuario: LoginUsuario): Observable<JwtDto> {
    return this.httpClient.post<JwtDto>(this.authURL+'login',loginUsuario);
  }

}

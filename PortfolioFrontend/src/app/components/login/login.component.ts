import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { LoginUsuario } from 'src/app/models/login-usuario';
import { AuthService } from 'src/app/services/auth.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUsuario!: LoginUsuario;
  formLogin: FormGroup;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) {
    this.formLogin = this.formBuilder.group({
      nombreUsuario: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  ngOnInit() {
  }

  submitLogin(): void {

    if(this.formLogin.valid) {

      let nombreUsuario = this.formLogin.controls["nombreUsuario"].value;
      let password = this.formLogin.controls["password"].value;

      let loginUsuario = new LoginUsuario(nombreUsuario, password);

      this.authService.login(loginUsuario).subscribe(
        data => {
          this.tokenService.setToken(data.token);
          this.closeForm();
          window.location.reload();
        },
        err => {
          this.toastr.error('Usuario o Contraseña incorrectos', 'Error');
        }
      );
    }

  }

  closeForm(): void{
    this.formLogin.reset();
    document.getElementById("closeModalLogin")?.click();
  }

}

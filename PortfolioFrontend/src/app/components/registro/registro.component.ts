import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { NuevoUsuario } from 'src/app/models/nuevo-usuario';
import { AuthService } from 'src/app/services/auth.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  nuevoUsuario!: NuevoUsuario;
  formRegistro: FormGroup;
  isLogged = false;
  errMsj: string;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) {
    this.formRegistro = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      nombreUsuario: ['', [Validators.required]],
      email: ['',[Validators.required,Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }
  }

  submitRegistro(): void {

    if(this.formRegistro.valid) {

      const usuarioNuevo: any = {
        nombre: this.formRegistro.get('nombre')?.value,
        nombreUsuario: this.formRegistro.get('nombreUsuario')?.value,
        email: this.formRegistro.get('email')?.value,
        password: this.formRegistro.get('password')?.value
      }

      this.authService.new(usuarioNuevo).subscribe(
        data => {
          this.toastr.success('Usuario registrado', 'Usuario');     
          this.closeForm();

        }, error => {
          this.errMsj = error.error.mensaje;
          this.toastr.error(this.errMsj, 'Error');
        }
      );

    }
  }

  closeForm(): void{
    this.formRegistro.reset();
    document.getElementById('closeModalRegistro')?.click();
  }

}

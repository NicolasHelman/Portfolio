import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Persona } from 'src/app/models/persona';
import { PersonaService } from 'src/app/services/persona.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.css']
})
export class PersonaComponent implements OnInit {

  persona!: Persona;
  formPersona: FormGroup;
  isLogged = false;
  isAdmin = false;

  constructor(
    private personaService: PersonaService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { 
    this.formPersona = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      imgPerfil: ['', [Validators.required]],
      imgPortada:['', [Validators.required]],
      cargo: ['', [Validators.required]],
      tipoCargo: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.view();
    this.isLogged = this.tokenService.isLogged();
    this.isAdmin = this.tokenService.isAdmin();
  }

  view(): void {
    this.personaService.view().subscribe(
      data => {
        this.persona = data;
      }
    )
  };

  submitPersona() {
    if(this.formPersona.valid){
    
      let nombre = this.formPersona.controls["nombre"].value;
      let imgPerfil = this.formPersona.controls["imgPerfil"].value;
      let imgPortada = this.formPersona.controls["imgPortada"].value;
      let cargo = this.formPersona.controls["cargo"].value;
      let tipoCargo = this.formPersona.controls["tipoCargo"].value;
    
      let personaEditar = new Persona(this.persona.id, nombre, imgPerfil, imgPortada, cargo, tipoCargo);
    
      this.personaService.update(personaEditar).subscribe(data=>{
        this.persona = personaEditar;
        this.toastr.info('Persona actualizada', 'Persona');
        
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error'); 
      })

    } else{
      this.formPersona.markAllAsTouched();
    }
  }

  editarPersona(){
    this.formPersona.controls["nombre"].setValue(this.persona.nombre);
    this.formPersona.controls["imgPerfil"].setValue(this.persona.imgPerfil);
    this.formPersona.controls["imgPortada"].setValue(this.persona.imgPortada);
    this.formPersona.controls["cargo"].setValue(this.persona.cargo);
    this.formPersona.controls["tipoCargo"].setValue(this.persona.tipoCargo);
  }

  logout(): void {
    this.tokenService.logOut();
    window.location.reload();
  }

  closeForm(): void{
    document.getElementById("closeModalPersona")?.click();
  }

}

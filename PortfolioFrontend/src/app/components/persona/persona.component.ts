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

  listPersona!: Persona[];
  formNuevo: FormGroup;
  formEditar: FormGroup;
  id: number | undefined;
  isAdmin = false;
  isLogged = false;

  constructor(
    private personaService: PersonaService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { 
    this.formNuevo = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      imgPerfil: ['', [Validators.required]],
      imgPortada:['', [Validators.required]],
      cargo: ['', [Validators.required]],
      tipoCargo: ['', [Validators.required]],
    });
    this.formEditar = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      imgPerfil: ['', [Validators.required]],
      imgPortada:['', [Validators.required]],
      cargo: ['', [Validators.required]],
      tipoCargo: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.list();
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();
  }

  list(): void {
    this.personaService.list().subscribe(
      data => {
        this.listPersona = data;
      }
    )
  };

  submitPersona() {

    if(this.id == undefined){

      const personaNuevo: any = {
        nombre: this.formNuevo.get('nombre')?.value,
        imgPerfil: this.formNuevo.get('imgPerfil')?.value,
        imgPortada: this.formNuevo.get('imgPortada')?.value,
        cargo: this.formNuevo.get('cargo')?.value,
        tipoCargo: this.formNuevo.get('tipoCargo')?.value
      }

      this.personaService.save(personaNuevo).subscribe(data=>{

        this.toastr.success('Persona registrada', 'Persona');     
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{

      const personaEditar: any = {
        nombre: this.formEditar.get('nombre')?.value,
        imgPerfil: this.formEditar.get('imgPerfil')?.value,
        imgPortada: this.formEditar.get('imgPortada')?.value,
        cargo: this.formEditar.get('cargo')?.value,
        tipoCargo: this.formEditar.get('tipoCargo')?.value
      }

      personaEditar.id = this.id;
    
      this.personaService.update(this.id, personaEditar).subscribe(data=>{

        this.toastr.info('Persona actualizada', 'Persona');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })
    }
  }

  nuevoPersona() {
    this.id = undefined;
  }

  editarPersona(persona: any) {
    this.id = persona.id;
    
    this.formEditar.patchValue({
      nombre: persona.nombre,
      imgPerfil: persona.imgPerfil,
      imgPortada: persona.imgPortada,
      cargo: persona.cargo,
      tipoCargo: persona.tipoCargo
    })

  }

  eliminarPersona(id: number){
    this.personaService.delete(id).subscribe(data => {
      this.toastr.error('Persona eliminada','Persona');
      this.list();
    })
  }

  closeForm(): void{
    this.formNuevo.reset();
    this.formEditar.reset();
    document.getElementById('closeNuevoModalPersona')?.click();
    document.getElementById('closeEditarModalPersona')?.click();
    this.list();
  }

  logout(): void {
    this.tokenService.logOut();
    window.location.reload();
  }

}
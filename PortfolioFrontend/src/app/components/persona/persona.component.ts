import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Persona } from 'src/app/models/persona';
import { PersonaService } from 'src/app/services/persona.service';

@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.css']
})
export class PersonaComponent implements OnInit {

  persona!: Persona;
  form: FormGroup;

  constructor(
    private personaService: PersonaService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) { 
    this.form = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      imgPerfil: ['', [Validators.required]],
      imgPortada:['', [Validators.required]],
      cargo: ['', [Validators.required]],
      tipoCargo: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.view();
  }

  view(): void {
    this.personaService.view().subscribe(
      data => {
        this.persona = data;
      }
    )
  };

  newPersona() {
    if(this.form.valid){
    
      let nombre = this.form.controls["nombre"].value;
      let imgPerfil = this.form.controls["imgPerfil"].value;
      let imgPortada = this.form.controls["imgPortada"].value;
      let cargo = this.form.controls["cargo"].value;
      let tipoCargo = this.form.controls["tipoCargo"].value;
    
      let personaEditar = new Persona(this.persona.id, nombre, imgPerfil, imgPortada, cargo, tipoCargo);
    
      this.personaService.update(personaEditar).subscribe(data=>{
        this.persona = personaEditar;
        this.toastr.info('Persona actualizada', 'Persona');
        
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error'); 
      })

    } else{
      this.form.markAllAsTouched();
    }
  }

  dataForm(){
    this.form.controls["nombre"].setValue(this.persona.nombre);
    this.form.controls["imgPerfil"].setValue(this.persona.imgPerfil);
    this.form.controls["imgPortada"].setValue(this.persona.imgPortada);
    this.form.controls["cargo"].setValue(this.persona.cargo);
    this.form.controls["tipoCargo"].setValue(this.persona.tipoCargo);
  }

  closeForm(): void{
    document.getElementById("closeModalPersona")?.click();
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Experiencia } from 'src/app/models/experiencia';
import { ExperienciaService } from 'src/app/services/experiencia.service';

@Component({
  selector: 'app-experiencia',
  templateUrl: './experiencia.component.html',
  styleUrls: ['./experiencia.component.css']
})
export class ExperienciaComponent implements OnInit {

  listExperiencia!: Experiencia[];
  form: FormGroup;
  id: number | undefined;
  
  constructor(
    private experienciaService: ExperienciaService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) {
    this.form =  this.formBuilder.group({
      empresa: ["",[Validators.required]],
      cargo: ["", [Validators.required]],
      fechaInicio: ["", [Validators.required]],
      fechaFin: ["", [Validators.required]],
      imgEmpresa: ["", [Validators.required]]
    })
  }

  ngOnInit(): void {
    this.list();
  }

  list(): void {
    this.experienciaService.list().subscribe(
      data => {
        this.listExperiencia = data;
      }
    )
  };

  newExperiencia() {

    const experiencia: any = {
      empresa: this.form.get('empresa')?.value,
      cargo: this.form.get('cargo')?.value,
      fechaInicio: this.form.get('fechaInicio')?.value,
      fechaFin: this.form.get('fechaFin')?.value,
      imgEmpresa: this.form.get('imgEmpresa')?.value
    }

    if(this.id == undefined){
   
      this.experienciaService.save(experiencia).subscribe(data => {

        this.toastr.success('Experiencia registrada', 'Experiencia');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{
      experiencia.id = this.id;
      
      this.experienciaService.update(this.id, experiencia).subscribe(data => {
        
        this.toastr.info('Experiencia actualizada', 'Experiencia');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })
    }
  }

  nuevoExperiencia() {
    this.id = undefined;
  }

  editarExperiencia(experiencia: any) {
    this.id = experiencia.id;

    this.form.patchValue({
      empresa: experiencia.empresa,
      cargo: experiencia.cargo,
      fechaInicio: experiencia.fechaInicio,
      fechaFin: experiencia.fechaFin,
      imgEmpresa: experiencia.imgEmpresa
    });
  }

  eliminarExperiencia(id: number) {
    this.experienciaService.delete(id).subscribe(data => {
      this.toastr.error('Experiencia eliminada','Experiencia');
      this.list();
    })
  }


  closeForm(): void{
    this.form.reset();
    this.list();
    document.getElementById("closeModalExperiencia")?.click();
  }


}
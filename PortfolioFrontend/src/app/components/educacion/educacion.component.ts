import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Educacion } from 'src/app/models/educacion';
import { EducacionService } from 'src/app/services/educacion.service';

@Component({
  selector: 'app-educacion',
  templateUrl: './educacion.component.html',
  styleUrls: ['./educacion.component.css']
})
export class EducacionComponent implements OnInit {

  listEducacion!: Educacion[];
  formNuevo: FormGroup;
  formEditar: FormGroup;
  id: number | undefined;

  constructor(
    private educacionService: EducacionService,
    private formBuilder: FormBuilder, 
    private toastr: ToastrService
  ) {
    this.formNuevo = this.formBuilder.group({
      institucion: ['',[Validators.required]],
      titulo: ['',[Validators.required]],
      fechaInicio: ['',[Validators.required]],
      fechaFin: ['',[Validators.required]],
      imgInstitucion: ['',[Validators.required]]
    });
    this.formEditar = this.formBuilder.group({
      institucion: ['',[Validators.required]],
      titulo: ['',[Validators.required]],
      fechaInicio: ['',[Validators.required]],
      fechaFin: ['',[Validators.required]],
      imgInstitucion: ['',[Validators.required]]
    });
  }

  ngOnInit(): void {
    this.list();
  }

  list(): void {
    this.educacionService.list().subscribe(
      data => {
        this.listEducacion = data;
      }
    )
  };

  submitEducacion() {

    if(this.id == undefined){

      const educacionNuevo: any = {
        institucion: this.formNuevo.get('institucion')?.value,
        titulo: this.formNuevo.get('titulo')?.value,
        fechaInicio: this.formNuevo.get('fechaInicio')?.value,
        fechaFin: this.formNuevo.get('fechaFin')?.value,
        imgInstitucion: this.formNuevo.get('imgInstitucion')?.value
      }
   
      this.educacionService.save(educacionNuevo).subscribe(data => {

        this.toastr.success('Educación registrada', 'Educacion');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{

      const educacionEditar: any = {
        institucion: this.formEditar.get('institucion')?.value,
        titulo: this.formEditar.get('titulo')?.value,
        fechaInicio: this.formEditar.get('fechaInicio')?.value,
        fechaFin: this.formEditar.get('fechaFin')?.value,
        imgInstitucion: this.formEditar.get('imgInstitucion')?.value
      }

      educacionEditar.id = this.id;
      
      this.educacionService.update(this.id, educacionEditar).subscribe(data => {

        this.toastr.info('Educación actualizada', 'Educacion');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })
    }
  }

  nuevoEducacion() {
    this.id = undefined;
  }

  editarEducacion(educacion: any) {
    this.id = educacion.id;
    
    this.formEditar.patchValue({
      institucion: educacion.institucion,
      titulo: educacion.titulo,
      fechaInicio: educacion.fechaInicio,
      fechaFin: educacion.fechaFin,
      imgInstitucion: educacion.imgInstitucion
    })
  }

  eliminarEducacion(id: number){
    this.educacionService.delete(id).subscribe(data => {
      this.toastr.error('Educacion eliminada','Educación');
      this.list();
    })
  }

  closeForm(): void{
    this.formNuevo.reset();
    this.formEditar.reset();
    document.getElementById("closeNuevoModalEducacion")?.click();
    document.getElementById("closeEditarModalEducacion")?.click();
    this.list();
  }

}
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
  form: FormGroup;
  id: number | undefined;

  constructor(
    private educacionService: EducacionService,
    private formBuilder: FormBuilder, 
    private toastr: ToastrService
  ) {
    this.form = this.formBuilder.group({
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

  newEducacion() {

    const educacion: any = {
      institucion: this.form.get('institucion')?.value,
      titulo: this.form.get('titulo')?.value,
      fechaInicio: this.form.get('fechaInicio')?.value,
      fechaFin: this.form.get('fechaFin')?.value,
      imgInstitucion: this.form.get('imgInstitucion')?.value
    }

    if(this.id == undefined){
   
      this.educacionService.save(educacion).subscribe(data => {

        this.toastr.success('Educación registrada', 'Educacion');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{
      educacion.id = this.id;
      
      this.educacionService.update(this.id, educacion).subscribe(data => {

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
    
    this.form.patchValue({
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
    this.form.reset();
    this.list();
    document.getElementById("closeModalEducacion")?.click();
  }

}
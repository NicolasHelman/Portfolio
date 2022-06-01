import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Habilidad } from 'src/app/models/habilidad';
import { HabilidadService } from 'src/app/services/habilidad.service';

@Component({
  selector: 'app-habilidad',
  templateUrl: './habilidad.component.html',
  styleUrls: ['./habilidad.component.css']
})
export class HabilidadComponent implements OnInit {

  listHabilidad!: Habilidad[];
  formNuevo: FormGroup;
  formEditar: FormGroup;
  id: number | undefined;
  
  constructor(
    private habilidadService: HabilidadService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) {
    this.formNuevo =  this.formBuilder.group({
      tipo: ["",[Validators.required]],
      porcentaje: ["", [Validators.required,Validators.min(1),Validators.max(100)]]
    });
    this.formEditar =  this.formBuilder.group({
      tipo: ["",[Validators.required]],
      porcentaje: ["", [Validators.required,Validators.min(1),Validators.max(100)]]
    });
  }

  ngOnInit(): void {
    this.list();
  }

  list(): void {
    this.habilidadService.list().subscribe(
      data => {
        this.listHabilidad = data;
      }
    )
  };

  submitHabilidad() {

    if(this.id == undefined){

      const habilidadNuevo: any = {
        tipo: this.formNuevo.get('tipo')?.value,
        porcentaje: this.formNuevo.get('porcentaje')?.value
      }
   
      this.habilidadService.save(habilidadNuevo).subscribe(data => {

        this.toastr.success('Habilidad registrada', 'Habilidad');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{

      const habilidadEditar: any = {
        tipo: this.formEditar.get('tipo')?.value,
        porcentaje: this.formEditar.get('porcentaje')?.value
      }

      habilidadEditar.id = this.id;
      
      this.habilidadService.update(this.id, habilidadEditar).subscribe(data => {
        
        this.toastr.info('Habilidad actualizada', 'Habilidad');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })
    }
  }

  nuevoHabilidad() {
    this.id = undefined;
  }

  editarHabilidad(habilidad: any) {
    this.id = habilidad.id;

    this.formEditar.patchValue({
      tipo: habilidad.tipo,
      porcentaje: habilidad.porcentaje
    });
  }

  eliminarHabilidad(id: number) {
    this.habilidadService.delete(id).subscribe(data => {
      this.toastr.error('Habilidad eliminada','Habilidad');
      this.list();
    })
  }

  closeForm(): void{
    this.formNuevo.reset();
    this.formEditar.reset();
    document.getElementById("closeNuevoModalHabilidad")?.click();
    document.getElementById("closeEditarModalHabilidad")?.click();
    this.list();
  }

}
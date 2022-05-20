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
  form: FormGroup;
  id: number | undefined;
  
  constructor(
    private habilidadService: HabilidadService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) {
    this.form =  this.formBuilder.group({
      tipo: ["",[Validators.required]],
      porcentaje: ["", [Validators.required]]
    })
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

  newHabilidad() {

    const habilidad: any = {
      tipo: this.form.get('tipo')?.value,
      porcentaje: this.form.get('porcentaje')?.value
    }

    if(this.id == undefined){
   
      this.habilidadService.save(habilidad).subscribe(data => {

        this.toastr.success('Habilidad registrada', 'Habilidad');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{
      habilidad.id = this.id;
      
      this.habilidadService.update(this.id, habilidad).subscribe(data => {
        
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

    this.form.patchValue({
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
    this.form.reset();
    this.list();
    document.getElementById("closeModalHabilidad")?.click();
  }


}
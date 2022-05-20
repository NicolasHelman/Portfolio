import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Proyecto } from 'src/app/models/proyecto';
import { ProyectoService } from 'src/app/services/proyecto.service';

@Component({
  selector: 'app-proyecto',
  templateUrl: './proyecto.component.html',
  styleUrls: ['./proyecto.component.css']
})
export class ProyectoComponent implements OnInit {

  listProyecto!: Proyecto[];
  form: FormGroup;
  id: number | undefined;

  constructor(
    private proyectoService: ProyectoService,
    private formBuilder: FormBuilder, 
    private toastr: ToastrService
  ) {
    this.form = this.formBuilder.group({
      nombre: ['',[Validators.required]],
      descripcion: ['',[Validators.required]],
      imgProyecto: ['',[Validators.required]],
      urlProyecto: ['',[Validators.required]]
    });
  }

  ngOnInit(): void {
    this.list();
  }

  list(): void {
    this.proyectoService.list().subscribe(
      data => {
        this.listProyecto = data;
      }
    )
  };

  newProyecto() {

    const proyecto: any = {
      nombre: this.form.get('nombre')?.value,
      descripcion: this.form.get('descripcion')?.value,
      imgProyecto: this.form.get('imgProyecto')?.value,
      urlProyecto: this.form.get('urlProyecto')?.value
    }

    if(this.id == undefined){
   
      this.proyectoService.save(proyecto).subscribe(data => {

        this.toastr.success('Proyecto registrado', 'Proyecto');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{
      proyecto.id = this.id;
      
      this.proyectoService.update(this.id, proyecto).subscribe(data => {

        this.toastr.info('Proyecto actualizado', 'Proyecto');       
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })
    }
  }

  nuevoProyecto() {
    this.id = undefined;
  }

  editarProyecto(proyecto: any) {
    this.id = proyecto.id;
    
    this.form.patchValue({
      nombre: proyecto.nombre,
      descripcion: proyecto.descripcion,
      imgProyecto: proyecto.imgProyecto,
      urlProyecto: proyecto.urlProyecto
    })
  }

  eliminarProyecto(id: number){
    this.proyectoService.delete(id).subscribe(data => {
      this.toastr.error('Proyecto eliminado','Proyecto');
      this.list();
    })
  }

  closeForm(): void{
    this.form.reset();
    this.list();
    document.getElementById("closeModalProyecto")?.click();
  }

}

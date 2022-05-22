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
  formNuevo: FormGroup;
  formEditar: FormGroup;
  id: number | undefined;

  constructor(
    private proyectoService: ProyectoService,
    private formBuilder: FormBuilder, 
    private toastr: ToastrService
  ) {
    this.formNuevo = this.formBuilder.group({
      nombre: ['',[Validators.required]],
      descripcion: ['',[Validators.required]],
      imgProyecto: ['',[Validators.required]],
      urlProyecto: ['',[Validators.required]]
    });
    this.formEditar = this.formBuilder.group({
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

  submitProyecto() {

    if(this.id == undefined){

      const proyectoNuevo: any = {
        nombre: this.formNuevo.get('nombre')?.value,
        descripcion: this.formNuevo.get('descripcion')?.value,
        imgProyecto: this.formNuevo.get('imgProyecto')?.value,
        urlProyecto: this.formNuevo.get('urlProyecto')?.value
      }
   
      this.proyectoService.save(proyectoNuevo).subscribe(data => {

        this.toastr.success('Proyecto registrado', 'Proyecto');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{

      const proyectoEditar: any = {
        nombre: this.formEditar.get('nombre')?.value,
        descripcion: this.formEditar.get('descripcion')?.value,
        imgProyecto: this.formEditar.get('imgProyecto')?.value,
        urlProyecto: this.formEditar.get('urlProyecto')?.value
      }

      proyectoEditar.id = this.id;
      
      this.proyectoService.update(this.id, proyectoEditar).subscribe(data => {

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
    
    this.formEditar.patchValue({
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
    this.formNuevo.reset();
    this.formEditar.reset();
    document.getElementById("closeNuevoModalProyecto")?.click();
    document.getElementById("closeEditarModalProyecto")?.click();
    this.list();
  }

}

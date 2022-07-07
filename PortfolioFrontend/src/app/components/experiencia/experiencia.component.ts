import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Experiencia } from 'src/app/models/experiencia';
import { ExperienciaService } from 'src/app/services/experiencia.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-experiencia',
  templateUrl: './experiencia.component.html',
  styleUrls: ['./experiencia.component.css']
})
export class ExperienciaComponent implements OnInit {

  listExperiencia!: Experiencia[];
  formNuevo: FormGroup;
  formEditar: FormGroup;
  id: number | undefined;
  isAdmin = false;
  
  constructor(
    private experienciaService: ExperienciaService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) {
    this.formNuevo =  this.formBuilder.group({
      empresa: ["",[Validators.required]],
      cargo: ["", [Validators.required]],
      fechaInicio: ["", [Validators.required]],
      fechaFin: ["", [Validators.required]],
      imgEmpresa: ["", [Validators.required]]
    });
    this.formEditar =  this.formBuilder.group({
      empresa: ["",[Validators.required]],
      cargo: ["", [Validators.required]],
      fechaInicio: ["", [Validators.required]],
      fechaFin: ["", [Validators.required]],
      imgEmpresa: ["", [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.list();
    this.isAdmin = this.tokenService.isAdmin();
  }

  list(): void {
    this.experienciaService.list().subscribe(
      data => {
        this.listExperiencia = data;
      }
    )
  };

  submitExperiencia() {

    if(this.id == undefined){

      const experienciaNuevo: any = {
        empresa: this.formNuevo.get('empresa')?.value,
        cargo: this.formNuevo.get('cargo')?.value,
        fechaInicio: this.formNuevo.get('fechaInicio')?.value,
        fechaFin: this.formNuevo.get('fechaFin')?.value,
        imgEmpresa: this.formNuevo.get('imgEmpresa')?.value
      }
   
      this.experienciaService.save(experienciaNuevo).subscribe(data => {

        this.toastr.success('Experiencia registrada', 'Experiencia');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{

      const experienciaEditar: any = {
        empresa: this.formEditar.get('empresa')?.value,
        cargo: this.formEditar.get('cargo')?.value,
        fechaInicio: this.formEditar.get('fechaInicio')?.value,
        fechaFin: this.formEditar.get('fechaFin')?.value,
        imgEmpresa: this.formEditar.get('imgEmpresa')?.value
      }

      experienciaEditar.id = this.id;
      
      this.experienciaService.update(this.id, experienciaEditar).subscribe(data => {
        
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

    this.formEditar.patchValue({
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
    this.formNuevo.reset();
    this.formEditar.reset();
    document.getElementById("closeNuevoModalExperiencia")?.click();
    document.getElementById("closeEditarModalExperiencia")?.click();
    this.list();
  }


}
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AcercaDe } from 'src/app/models/acerca-de';
import { AcercaDeService } from 'src/app/services/acerca-de.service';


@Component({
  selector: 'app-acerca-de',
  templateUrl: './acerca-de.component.html',
  styleUrls: ['./acerca-de.component.css']
})
export class AcercaDeComponent implements OnInit {

  listAcercaDe!: AcercaDe[];
  formNuevo: FormGroup;
  formEditar: FormGroup;
  id: number | undefined;

  constructor(
    private acercaDeService: AcercaDeService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) { 
    this.formNuevo = this.formBuilder.group({
      descripcion: ['',[Validators.required]],
      fechaNacimiento: ['',[Validators.required]],
      ciudad: ['',[Validators.required]],
      telefono: ['',[Validators.required,Validators.min(1000000000)]],
      mail: ['',[Validators.required,Validators.email]]
    });
    this.formEditar = this.formBuilder.group({
      descripcion: ['',[Validators.required]],
      fechaNacimiento: ['',[Validators.required]],
      ciudad: ['',[Validators.required]],
      telefono: ['',[Validators.required,Validators.min(1000000000)]],
      mail: ['',[Validators.required,Validators.email]]
    });
  }

  ngOnInit(): void {
    this.list();
  }

  list(): void {
    this.acercaDeService.list().subscribe(
      data => {
        this.listAcercaDe = data;
      }
    )
  };

  submitAcercaDe() {

    if(this.id == undefined){

      const acercaDeNuevo: any = {
        descripcion: this.formNuevo.get('descripcion')?.value,
        fechaNacimiento: this.formNuevo.get('fechaNacimiento')?.value,
        ciudad: this.formNuevo.get('ciudad')?.value,
        telefono: this.formNuevo.get('telefono')?.value,
        mail: this.formNuevo.get('mail')?.value
      }
    
      this.acercaDeService.save(acercaDeNuevo).subscribe(data=>{

        this.toastr.success('Acerca De registrado', 'Acerca De');     
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{

      const acercaDeEditar: any = {
        descripcion: this.formEditar.get('descripcion')?.value,
        fechaNacimiento: this.formEditar.get('fechaNacimiento')?.value,
        ciudad: this.formEditar.get('ciudad')?.value,
        telefono: this.formEditar.get('telefono')?.value,
        mail: this.formEditar.get('mail')?.value
      }

      acercaDeEditar.id = this.id;
    
      this.acercaDeService.update(this.id, acercaDeEditar).subscribe(data=>{

        this.toastr.info('Acerca De actualizado', 'Acerca De');     
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })
    }
  }

  nuevoAcercaDe() {
    this.id = undefined;
  }

  editarAcercaDe(acercaDe: any) {
    this.id = acercaDe.id;
    
    this.formEditar.patchValue({
      descripcion: acercaDe.descripcion,
      fechaNacimiento: acercaDe.fechaNacimiento,
      ciudad: acercaDe.ciudad,
      telefono: acercaDe.telefono,
      mail: acercaDe.mail
    })

  }

  eliminarAcercaDe(id: number){
    this.acercaDeService.delete(id).subscribe(data => {
      this.toastr.error('Acerca De eliminado','Acerca De');
      this.list();
    })
  }

  closeForm(): void{
    this.formNuevo.reset();
    this.formEditar.reset();
    document.getElementById('closeNuevoModalAcercaDe')?.click();
    document.getElementById('closeEditarModalAcercaDe')?.click();
    this.list();
  }
}
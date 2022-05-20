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

  acercaDe!: AcercaDe;
  form: FormGroup;

  constructor(
    private acercaDeService: AcercaDeService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) { 
    this.form = this.formBuilder.group({
      descripcion: ['',[Validators.required]],
      fechaNacimiento: ['',[Validators.required]],
      ciudad: ['',[Validators.required]],
      telefono: ['',[Validators.required,Validators.minLength(10)]],
      mail: ['',[Validators.required]],
      github: ['',[Validators.required]],
      linkedIn: ['',[Validators.required]]
    });
  }

  ngOnInit(): void {
    this.view();
  }

  view(): void {
    this.acercaDeService.view().subscribe(
      data => {
        this.acercaDe = data;
      }
    )
  };

  newAcercaDe() {
    if(this.form.valid){
   
      let descripcion = this.form.controls["descripcion"].value;
      let fechaNacimiento = this.form.controls["fechaNacimiento"].value;
      let ciudad = this.form.controls["ciudad"].value;
      let telefono = this.form.controls["telefono"].value;
      let mail = this.form.controls["mail"].value;
      let github = this.form.controls["github"].value;
      let linkedIn = this.form.controls["linkedIn"].value;
    
      let acercaDeEditar = new AcercaDe(this.acercaDe.id, descripcion, fechaNacimiento, ciudad, telefono, mail, github, linkedIn);
    
      this.acercaDeService.update(acercaDeEditar).subscribe(data=>{
        this.acercaDe = acercaDeEditar;
        this.toastr.info('Acerca De actualizado', 'Acerca De');
        
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{
      this.form.markAllAsTouched();
    }
  }

  dataForm(){
    this.form.controls["descripcion"].setValue(this.acercaDe.descripcion);
    this.form.controls["fechaNacimiento"].setValue(this.acercaDe.fechaNacimiento);
    this.form.controls["ciudad"].setValue(this.acercaDe.ciudad);
    this.form.controls["telefono"].setValue(this.acercaDe.telefono);
    this.form.controls["mail"].setValue(this.acercaDe.mail);
    this.form.controls["github"].setValue(this.acercaDe.github);
    this.form.controls["linkedIn"].setValue(this.acercaDe.linkedIn);
  }

  closeForm(): void{
    this.form.reset();
    document.getElementById("closeModalAcercaDe")?.click();
  }

}

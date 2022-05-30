import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Social } from 'src/app/models/social';
import { SocialService } from 'src/app/services/social.service';

@Component({
  selector: 'app-social',
  templateUrl: './social.component.html',
  styleUrls: ['./social.component.css']
})
export class SocialComponent implements OnInit {

  listSocial!: Social[];
  formNuevo: FormGroup;
  formEditar: FormGroup;
  id: number | undefined;
  
  constructor(
    private socialService: SocialService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) {
    this.formNuevo =  this.formBuilder.group({
      nombre: ['',[Validators.required]],
      urlSocial: ['', [Validators.required]],
      colorSocial: ['', [Validators.required]]
    });
    this.formEditar =  this.formBuilder.group({
      nombre: ['',[Validators.required]],
      urlSocial: ['', [Validators.required]],
      colorSocial: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.list();
  }

  list(): void {
    this.socialService.list().subscribe(
      data => {
        this.listSocial = data;
      }
    )
  };

  submitSocial() {

    if(this.id == undefined){

      const socialNuevo: any = {
        nombre: this.formNuevo.get('nombre')?.value,
        urlSocial: this.formNuevo.get('urlSocial')?.value,
        colorSocial: this.formNuevo.get('colorSocial')?.value
      }
   
      this.socialService.save(socialNuevo).subscribe(data => {

        this.toastr.success('Red Social registrada', 'Red Social');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })

    } else{

      const socialEditar: any = {
        nombre: this.formEditar.get('nombre')?.value,
        urlSocial: this.formEditar.get('urlSocial')?.value,
        colorSocial: this.formEditar.get('colorSocial')?.value
      }

      socialEditar.id = this.id;
      
      this.socialService.update(this.id, socialEditar).subscribe(data => {
        
        this.toastr.info('Red Social actualizada', 'Red Social');
        this.closeForm();

      }, error => {
        this.toastr.error('Ocurrió un error','Error');
      })
    }
  }

  nuevoSocial() {
    this.id = undefined;
  }

  editarSocial(social: any) {
    this.id = social.id;

    this.formEditar.patchValue({
      nombre: social.nombre,
      urlSocial: social.urlSocial,
      colorSocial: social.colorSocial
    });
  }

  eliminarSocial(id: number) {
    this.socialService.delete(id).subscribe(data => {
      this.toastr.error('Red Social eliminada','Red Social');
      this.list();
    })
  }

  closeForm(): void{
    this.formNuevo.reset();
    this.formEditar.reset();
    document.getElementById("closeNuevoModalSocial")?.click();
    document.getElementById("closeEditarModalSocial")?.click();
    this.list();
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ContactoService } from 'src/app/services/contacto.service';

@Component({
  selector: 'app-contacto',
  templateUrl: './contacto.component.html',
  styleUrls: ['./contacto.component.css']
})
export class ContactoComponent implements OnInit {

  formContacto: FormGroup;

  constructor(
    private contactoService: ContactoService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) { 
    this.formContacto =  this.formBuilder.group({
      nombre: ['',[Validators.required]],
      mail: ['', [Validators.required,Validators.email]],
      asunto: ['', [Validators.required]],
      mensaje: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
  }

  submitContacto() {

    const contactoNuevo: any = {
      nombre: this.formContacto.get('nombre')?.value,
      mail: this.formContacto.get('mail')?.value,
      asunto: this.formContacto.get('asunto')?.value,
      mensaje: this.formContacto.get('mensaje')?.value
    }

    this.contactoService.save(contactoNuevo).subscribe(data => {
      
      this.toastr.success('Mensaje enviado', 'Contacto');
      this.formContacto.reset();

    }, error => {
      this.toastr.error('Debe estar logeado para enviar el mensaje','Error');
    })

  }

}

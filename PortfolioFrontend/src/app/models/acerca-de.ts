export class AcercaDe {

    id: number;
    descripcion: string;
    fechaNacimiento: string;
    ciudad: string;
    telefono: string;
    mail: string;

    constructor(id: number, descripcion: string, fechaNacimiento: string, ciudad: string, telefono: string, mail: string) {
            this.id = id;
            this.descripcion = descripcion;
            this.fechaNacimiento = fechaNacimiento;
            this.ciudad = ciudad;
            this.telefono = telefono;
            this.mail = mail;
    }

}

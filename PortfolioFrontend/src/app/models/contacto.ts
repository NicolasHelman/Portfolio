export class Contacto {

    id: number;
    nombre: string;
    mail: string;
    asunto: string;
    mensaje: string;

    constructor(id: number, nombre: string, mail: string, asunto: string, mensaje: string) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }
    
}

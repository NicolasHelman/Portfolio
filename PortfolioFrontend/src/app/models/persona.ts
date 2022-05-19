export class Persona {

    id: number;
    nombre: string;
    imgPerfil: string;
    imgPortada: string;
    cargo: string;
    tipoCargo: string;

    constructor(id: number, nombre: string, imgPerfil: string, imgPortada: string, cargo: string, tipoCargo: string) {
        this.id = id;
        this.nombre = nombre;
        this.imgPerfil = imgPerfil;
        this.imgPortada = imgPortada;
        this.cargo = cargo;
        this.tipoCargo = tipoCargo;
    }
}

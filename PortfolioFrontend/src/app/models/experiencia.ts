export class Experiencia {

    id: number;
    empresa: string;
    cargo: string;
    fechaInicio: string;
    fechaFin: string;
    imgEmpresa: string;

    constructor(id: number, empresa: string, cargo: string, fechaInicio: string, fechaFin: string, imgEmpresa: string) {
        this.id = id;
        this.empresa = empresa;
        this.cargo = cargo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.imgEmpresa = imgEmpresa;
    }
}

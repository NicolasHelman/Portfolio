export class Educacion {

    id: number;
    institucion: string;
    titulo: string;
    fechaInicio: string;
    fechaFin: string;
    imgInstitucion: string;

    constructor(id: number, institucion: string, titulo: string, fechaInicio: string, fechaFin: string, imgInstitucion: string) {
        this.id = id;
        this.institucion = institucion;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.imgInstitucion = imgInstitucion;
    }
}

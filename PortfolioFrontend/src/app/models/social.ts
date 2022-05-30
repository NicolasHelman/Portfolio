export class Social {
  
    id: number;
    nombre: string;
    urlSocial: string;
    colorSocial: string;

    constructor(id: number, nombre: string, urlSocial: string, colorSocial: string) {
        this.id = id;
        this.nombre = nombre;
        this.urlSocial = urlSocial;
        this.colorSocial = colorSocial;
    }
}

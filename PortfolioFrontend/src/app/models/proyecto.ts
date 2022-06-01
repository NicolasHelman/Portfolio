export class Proyecto {

    id: number;
    nombre: string;
    descripcion: string;
    imgProyecto: string;
    urlProyecto: string;
    githubProyecto: string;

    constructor(id: number, nombre: string, descripcion: string, imgProyecto: string, urlProyecto: string, githubProyecto: string) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imgProyecto = imgProyecto;
        this.urlProyecto = urlProyecto;
        this.githubProyecto = githubProyecto;
    }
}

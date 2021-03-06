import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

// components
import { AppComponent } from './app.component';
import { PersonaComponent } from './components/persona/persona.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { AppRoutingModule } from './app-routing.module';
import { AcercaDeComponent } from './components/acerca-de/acerca-de.component';
import { EducacionComponent } from './components/educacion/educacion.component';
import { ExperienciaComponent } from './components/experiencia/experiencia.component';
import { HabilidadComponent } from './components/habilidad/habilidad.component';
import { ProyectoComponent } from './components/proyecto/proyecto.component';
import { SocialComponent } from './components/social/social.component';
import { ContactoComponent } from './components/contacto/contacto.component';
import { LoginComponent } from './components/login/login.component';

// external
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

// para consumir la api
import { HttpClientModule } from '@angular/common/http';
// para la creacion del formulario y utilizar el doble binding
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// interceptor provider
import { interceptorProvider } from './services/interceptor.service';
import { RegistroComponent } from './components/registro/registro.component';


@NgModule({
  declarations: [
    AppComponent,
    PersonaComponent,
    PortfolioComponent,
    AcercaDeComponent,
    EducacionComponent,
    ExperienciaComponent,
    HabilidadComponent,
    ProyectoComponent,
    SocialComponent,
    ContactoComponent,
    LoginComponent,
    RegistroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }

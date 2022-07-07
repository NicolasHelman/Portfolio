import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../services/token.service';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  isLogged = false;
  nombreUsuario = '';

  constructor(
    private tokenService: TokenService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.nombreUsuario = this.tokenService.getUsername();
      this.toastr.success('Bienvenido ' + this.nombreUsuario, 'Portfolio');
    } else {
      this.isLogged = false;
      this.nombreUsuario = '';
    }
  }

}

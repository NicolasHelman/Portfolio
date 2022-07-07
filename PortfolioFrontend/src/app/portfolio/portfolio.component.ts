import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../services/token.service';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  nombreUsuario: string;

  constructor(
    private tokenService: TokenService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.nombreUsuario = this.tokenService.getUsername();
    if (this.nombreUsuario) {
      this.toastr.success('Bienvenido ' + this.nombreUsuario, 'Portfolio');
    } else {
      this.nombreUsuario = '';
    }
  }

}

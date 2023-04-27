import { Component } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent {

  constructor(
    private readonly authService: AuthService,
  ){}

  login(){
    this.authService.register("jackiexofficial@gmail.com", "anyad123");
  }

}

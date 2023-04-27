import { Component } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { FirebaseService } from 'src/app/_services/firebase.service';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent {

  constructor(
    private readonly authService: AuthService,
    private readonly firebaseService: FirebaseService
  ){}

  register(){
    this.authService.signUpUser("jackiexofficial@gmail.com", "jelszo123", "Földvári Alex");
  }

  login(){
    this.authService.signInUser("jackiexofficial@gmail.com", "jelszo123");
  }

}

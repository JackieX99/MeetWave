import { Component } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { FirebaseService } from 'src/app/_services/firebase.service';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent {

  constructor(
    private readonly authService: AuthService,
    private readonly firebaseService: FirebaseService,
    private fb: FormBuilder
  ){}

  product = this.fb.group({
    name: ['', Validators.required],
    price: ['', Validators.required],
    images: [{}]
  })

  register(){
    this.authService.signUpUser("jackiexofficial@gmail.com", "jelszo123", "Földvári Alex");
  }

  login(){
    this.authService.signInUser("jackiexofficial@gmail.com", "jelszo123");
  }

  tesztUpload(){
    console.log(this.product)
  }

}

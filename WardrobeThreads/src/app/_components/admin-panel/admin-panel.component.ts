import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { FirebaseService } from 'src/app/_services/firebase.service';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { getAuth, onAuthStateChanged } from '@angular/fire/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent implements OnInit {

  constructor(
    private readonly authService: AuthService,
    private readonly firebaseService: FirebaseService,
    private fb: FormBuilder,
    private router: Router
  ){}

  user: any;
  loggedin: boolean = false;

  products: any[] = [];
  productsKey: any;

  ngOnInit(): void {
    let auth = getAuth();

    this.firebaseService.getAllProduct().then(res => {
      this.products = res; 
      this.productsKey = Object.keys(res);
    })

    onAuthStateChanged(auth, (user) => {
      if (user) {
        this.user = user;
      } else {
        // this.router.navigate(['/login']);
      }
    });
  }

  product: FormGroup = this.fb.group({
    name: ['', Validators.required],
    price: ['', Validators.required],
    images: this.fb.array([])
  })

  pictureUrls: string[] = [];

  register(){
    this.authService.signUpUser("jackiexofficial@gmail.com", "jelszo123", "Földvári Alex");
  }

  login(){
    this.authService.signInUser("jackiexofficial@gmail.com", "jelszo123");
  }

  signout(){
    this.authService.logout();
  }

  get pictures(): FormArray {
    return this.product.get('images') as FormArray;
  }

  onFileSelected(event: any): void {
    if (event.target.files && event.target.files.length) {
      for (let i = 0; i < event.target.files.length; i++) {
        const file = event.target.files[i];
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
          this.pictureUrls.push(reader.result as string);
          this.pictures.push(this.fb.control(reader.result));
        };
      }
    }
  }

  onSubmit() {
    console.log(this.product.value);
    this.firebaseService.uploadProduct(this.product.value)
  }

}

import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { FirebaseService } from 'src/app/_services/firebase.service';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { getAuth, onAuthStateChanged } from '@angular/fire/auth';
import { Router } from '@angular/router';
import { Cases } from 'src/app/_models/phone-cases';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent implements OnInit {

  constructor(
    private readonly firebaseService: FirebaseService,
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
  ){}

  user: any;
  loggedin: boolean = false;

  products: any[] = [];
  productsKey: any;

  ngOnInit(): void { // Oldal betöltésekor hívódik meg
    let auth = getAuth();

    this.firebaseService.getAllProduct().then(res => { // Összes product lekérése adatbázisból
      this.products = res; 
      this.productsKey = Object.keys(res);
    })

    onAuthStateChanged(auth, (user) => { // Felhasználó ellenőrzése, be van-e lépve
      if (user) { // Ha be van, tároljuk
        this.user = user;
      } else { // Ha nincs, kidobjuk a login panelhoz
        // this.router.navigate(['/login']);
      }
    });
  }

  product: FormGroup = this.fb.group({ // Form létrehozása, validálás
    name: ['', Validators.required],
    price: ['', Validators.required],
    images: this.fb.array([], [Validators.required, Validators.maxLength(5)]),
    indeximage: this.fb.array([], Validators.required),
    phoneCategory: ['', Validators.required]
  })

  cases = Cases;

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

  setAdmin(bool: boolean){
    this.firebaseService.setAdmin("DZ2jUc5aFuMDc1WaWN8An2yPnkM2", bool);
  }

  get pictures(): FormArray {
    return this.product.get('images') as FormArray;
  }

  get indeximage(): FormArray {
    return this.product.get('indeximage') as FormArray;
  }

  onFileSelected(event: any): void { // Ha a file inputba fájlok kerülnek, meghívódik a függvény
    if (event.target.files && event.target.files.length) { // Ha van kiválasztott fájl
      for (let i = 0; i < event.target.files.length; i++) { // Átfutjuk a fájlokat
        const file = event.target.files[i]; // Egyesével readelve vannak a fájlok
        const reader = new FileReader();
        reader.readAsDataURL(file); // Konvertálás blobba
        reader.onload = () => { // Tárolás
          this.pictureUrls.push(reader.result as string);
          this.pictures.push(this.fb.control(reader.result));
        };
      }
    }
  }

  onFileSelected2(event: any): void { // Ha a file inputba fájlok kerülnek, meghívódik a függvény
    if (event.target.files && event.target.files.length) { // Ha van kiválasztott fájl
      for (let i = 0; i < event.target.files.length; i++) { // Átfutjuk a fájlokat
        const file = event.target.files[i]; // Egyesével readelve vannak a fájlok
        const reader = new FileReader();
        reader.readAsDataURL(file); // Konvertálás blobba
        reader.onload = () => { // Tárolás
          this.pictureUrls.push(reader.result as string);
          this.indeximage.push(this.fb.control(reader.result));
        };
      }
    }
  }

  onSubmit() { // Form elküldésekor meghívjuk a feltöltés függvényt
    this.firebaseService.uploadProduct(this.product.value)
  }

  deleteImages(type: any){
    if(type == "multiple"){
      (this.product.controls['images'] as FormArray).clear();
    } else if(type == "index"){
      (this.product.controls['indeximage'] as FormArray).clear();
    }
  }

}

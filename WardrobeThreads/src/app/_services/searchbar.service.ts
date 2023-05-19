import { Injectable } from '@angular/core';
import { FirebaseService } from './firebase.service';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SearchbarService {
  products: any[] = [];
  productsKey: any;

  foundProducts: any[] = [];

  constructor(private fbService: FirebaseService, private router: Router) {}

  async searchFor(keyword: any) {
    this.foundProducts = [];

    // Összes product lekérése
    await this.fbService.getAllProduct().then((res) => {
      this.products = res;
      this.productsKey = Object.keys(res);
    });

    // Adott szó ellenőrzése a termékek neveiben

    let foundProds: any[] = [];

    for (const key of Object.keys(this.products)) {
      const value = this.products[key as keyof typeof this.products];
      const idgnev = value.name.toLowerCase();
      if(idgnev.includes(keyword.toLowerCase())){
        foundProds.push(value);
      }
    }

    this.updateArray(foundProds);

    this.router.navigate(["/products"]);
  }

  getFoundProducts(): Promise<any[]> {
    return new Promise<any[]>((resolve, reject) => {
      resolve(this.foundProducts);
    });
  }

  // Kereső találatok visszaadása

  private myArraySubject: BehaviorSubject<any[]> = new BehaviorSubject<any[]>(this.foundProducts);

  // Metódus, amely módosítja a tömböt
  updateArray(newValue: any[]) {
    this.foundProducts = newValue;
    this.myArraySubject.next(this.foundProducts); // Értesítés a feliratkozóknak
  }

  // Metódus a feliratkozásra
  getArrayObservable(): Observable<any[]> {
    return this.myArraySubject.asObservable();
  }
  
}

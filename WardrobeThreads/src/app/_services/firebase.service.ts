import { DatePipe } from '@angular/common';
import { ConditionalExpr } from '@angular/compiler';
import { Component, inject, Injectable } from '@angular/core';
import {
  Database,
  set,
  ref,
  update,
  onValue,
  get,
  push,
  remove
} from '@angular/fire/database';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class FirebaseService {
  
  constructor(public database: Database, private router: Router) { }

  setAdmin(userId: any, admin: boolean) {
    update(ref(this.database, 'users/' + userId), {
      isAdmin: admin,
    });
  }

  async checkIfAdmin(userId: any) {
    var user: any;

    const snapshot = await get(ref(this.database, '/users/' + userId));
    user = snapshot.val();
    return user.isAdmin;
  }

  // Product feltöltése admin felületről

  uploadProduct(product: any){
    // this.generateCode().then(res => { // Kód generálás függvény meghívása, visszakapott értékkel product feltöltése adatbázisba
      push(ref(this.database, 'products/'), {
        name: product.name,
        price: product.price,
        images: product.images,
        category: product.phoneCategory,
        indeximage: product.indeximage
      });    
    // })
  }

  // ID generálása a feltöltött productnak

  async generateCode(){
    let code: any;
    while(true){
      code = Math.random().toString(36).substr(2, 5); // Random ID generálása

      let upload: any;
      const snapshot = await get(
        ref(this.database, '/products/' + code) // Lekérjük adatbázisból a random generált ID-t
      );
      upload = snapshot.val();
      if (upload == undefined) { // Nem létező ID, mehet a feltöltés
        break;
      } else{ // Létező ID, fusson tovább, generáljon új ID-t
        continue;
      }
    }
    return code; // ID visszaadása a feltöltés futásához
  }

  // Összes product lekérése

  async getAllProduct() {
    var products: any[] = [];

    const snapshot = await get(ref(this.database, '/products/'));
    products = snapshot.val();
    return products;
  }

  // 404-es oldal hibás url mentése

  missSpelledUrl(url: any, locale: string){
    let rn = new Date();
    let pipe = new DatePipe(locale);
    let ChangedFormat = pipe.transform(rn, 'YYYY/MM/dd HH:mm');

    push(ref(this.database, 'urls/'), {
      link: url,
      date: ChangedFormat
    }); 
  }
  
}

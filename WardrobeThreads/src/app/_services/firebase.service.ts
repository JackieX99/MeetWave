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
    this.generateCode().then(res => {
      update(ref(this.database, 'products/' + res), {
        name: product.name,
        price: product.price,
        images: product.images
      });    
    })
    
  }

  // ID generálása a feltöltött productnak

  async generateCode(){
    let code: any;
    while(true){
      code = Math.random().toString(36).substr(2, 5);

      let upload: any;
      const snapshot = await get(
        ref(this.database, '/products/' + code)
      );
      upload = snapshot.val();
      if (upload == undefined) {
        // Nincs még ilyen share code, mehet a feltöltés
        break;
      } else{
        continue;
      }
    }
    return code;
  }

  // Összes product lekérése

  async getAllProduct() {
    var products: any[] = [];

    const snapshot = await get(ref(this.database, '/products/'));
    products = snapshot.val();
    return products;
  }
  
}

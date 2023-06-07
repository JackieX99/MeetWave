import { Injectable } from '@angular/core';
import { FirebaseService } from './firebase.service';
import { Case, Cases } from '../_models/phone-cases';

@Injectable({
  providedIn: 'root'
})
export class CasesService {

  products: any[] = [];
  productsKey: any;
  cases: Case[] = Cases;

  constructor(private fbService: FirebaseService) { }

  async calculateCasePieces(){
    await this.fbService.getAllProduct().then((res) => {
      this.products = res;
      this.productsKey = Object.keys(res);
    });

    for(let i = 0; i < this.cases.length; i++){
      let num = 0;
      // console.log(this.cases[i].phone)
      for (const key of Object.keys(this.products)) {
        const value = this.products[key as keyof typeof this.products];
        const idgnev = value.name.toLowerCase();
        // if(idgnev.includes(this.cases[i].phone.toLowerCase())){
        //   num++;
        // }
        if(this.strictIncludes(idgnev, this.cases[i].phone.toLowerCase())){
          num++;
        }
      }
      this.cases[i].piece = num;
    }

  }

  getCases(){
    this.calculateCasePieces();

    return this.cases;
  }

  strictIncludes(str: string, searchValue: string): boolean {
    const regex = new RegExp(`\\b${searchValue}\\b(?!\\s|$)`);
  
    return regex.test(str);
  }


}

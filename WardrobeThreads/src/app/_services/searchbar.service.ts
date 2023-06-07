import { EventEmitter, Injectable } from '@angular/core';
import { FirebaseService } from './firebase.service';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SearchbarService {
  products: any[] = [];
  productsKey: any;

  searching: boolean = false;
  searchForKeyword: any = "";

  foundProducts: any[] = [];

  public searchingFor: EventEmitter<any> = new EventEmitter<any>();

  public search(keyword: any, foundlist: any) {
    this.searchingFor.emit({'keyword': keyword, 'list': foundlist});
  }

  constructor(private fbService: FirebaseService, private router: Router) {}

  async searchFor(keyword: any) {

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

    this.search(keyword, foundProds);

    this.router.navigate(["/products"]);    
  }

  getIfSearching(): boolean{
    return this.searching;
  }

  getSearchKeyword(): String{
    return this.searchForKeyword;
  }

  deleteSearching(){
    this.searching = false;
    this.searchForKeyword = "";
  }

  getFoundProducts(): Promise<any[]> {
    return new Promise<any[]>((resolve, reject) => {
      resolve(this.foundProducts);
    });
  }
  
}

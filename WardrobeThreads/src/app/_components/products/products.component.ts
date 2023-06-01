import { Component, OnDestroy, OnInit } from '@angular/core';
import { SearchbarService } from 'src/app/_services/searchbar.service';
import { Observable, Subscription, from } from 'rxjs';
import { FirebaseService } from 'src/app/_services/firebase.service';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit, OnDestroy {

  // Keresés
  foundProducts: any[] = [];
  foundProductsKey: any[] = [];
  searching: boolean = false;
  keyword: String = "";
  // Összes product
  products: any[] = [];
  productsKey: any[] = [];

  private subscription: Subscription;

  constructor(private sbService: SearchbarService, private fbService: FirebaseService) { }

  ngOnInit(): void {
    this.foundProducts = [];
    this.foundProductsKey = [];

    this.fbService.getAllProduct().then(res => {
      this.products = res;
      this.productsKey = Object.keys(res);
    })
    this.subscription = this.sbService.getArrayObservable().subscribe((updatedArray) => {
      this.searchFor(updatedArray);
    });
  }

  searchFor(array: any) {
    this.searching = this.sbService.getIfSearching();
    this.keyword = this.sbService.getSearchKeyword();
    this.foundProducts = array;
    this.foundProductsKey = Object.keys(array);
  }

  getIfSearching() {
    this.searching = this.sbService.getIfSearching();
  }

  resetSearch() {
    this.searching = false;
    this.sbService.deleteSearching();
    this.foundProducts = [];
    this.foundProductsKey = [];
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }



}

import { Component, OnDestroy, OnInit } from '@angular/core';
import { SearchbarService } from 'src/app/_services/searchbar.service';
import { Observable, Subscription, from } from 'rxjs';
import { FirebaseService } from 'src/app/_services/firebase.service';
import { Case, Cases } from 'src/app/_models/phone-cases';
import { CasesService } from 'src/app/_services/cases.service';


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

  // cases = Cases;
  cases: Case[] = [];

  private subscription: Subscription;

  constructor(private sbService: SearchbarService, private fbService: FirebaseService, private caseService: CasesService) { }

  ngOnInit(): void {
    this.foundProducts = [];
    this.foundProductsKey = [];

    this.fbService.getAllProduct().then(res => {
      this.products = res;
      this.productsKey = Object.keys(res);
    })

    this.sbService.searchingFor.subscribe((data) => { 
      this.searching = true;
      this.keyword = data.keyword;
      this.foundProducts = data.list;
      this.foundProductsKey = Object.keys(data.list);
    });

    this.cases = this.caseService.getCases();
  }

  selectCategory(keyword: any){
    this.sbService.searchFor(keyword);
  }

  deleteSearch() {
    this.keyword = "";
    this.searching = false;
    this.foundProducts = [];
    this.foundProductsKey = [];
  }

  search(event: any) {
    this.sbService.searchFor(event.target.value);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }



}

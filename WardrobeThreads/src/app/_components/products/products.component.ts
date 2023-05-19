import { Component, OnDestroy, OnInit } from '@angular/core';
import { SearchbarService } from 'src/app/_services/searchbar.service';
import { Observable, Subscription, from } from 'rxjs';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit, OnDestroy {

  foundProducts: any[] = [];
  foundProductsKey: any[] = [];

  private subscription: Subscription;
  
  constructor(private sbService: SearchbarService) {}

  ngOnInit(): void {
    this.foundProducts = [];
    this.foundProductsKey = [];

    // this.sbService.getData().then(newValue => {
    //   this.foundProducts = newValue;
    //   this.foundProductsKey = Object.keys(newValue);
    //   console.log("new value: ", newValue)
    // });
    this.subscription = this.sbService.getArrayObservable().subscribe((updatedArray) => {
      this.foundProducts = updatedArray; // Frissítsd a komponensben létrehozott tömböt
      this.foundProductsKey = Object.keys(updatedArray);
    });
    
  }

  ngOnDestroy() {
    this.subscription.unsubscribe(); // Ne felejtsd el leiratkozni a feliratkozásról a komponens megszűnésekor
  }

  

}

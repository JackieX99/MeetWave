import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './_components/navbar/navbar.component';
import { FooterComponent } from './_components/footer/footer.component';
import { RegisterComponent } from './_components/register/register.component';
import { LoginComponent } from './_components/login/login.component';
import { ShopCartComponent } from './_components/shop-cart/shop-cart.component';
import { ProfileInformationsComponent } from './_components/profile-informations/profile-informations.component';
import { PurchaseCompletingComponent } from './_components/purchase-completing/purchase-completing.component';
import { ProductsComponent } from './_components/products/products.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent,
    ShopCartComponent,
    ProfileInformationsComponent,
    PurchaseCompletingComponent,
    ProductsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

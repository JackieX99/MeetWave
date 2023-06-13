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
import { initializeApp,provideFirebaseApp } from '@angular/fire/app';
import { environment } from '../environments/environment';
import { provideAnalytics,getAnalytics,ScreenTrackingService,UserTrackingService } from '@angular/fire/analytics';
import { provideAuth,getAuth } from '@angular/fire/auth';
import { provideDatabase,getDatabase } from '@angular/fire/database';
import { AdminPanelComponent } from './_components/admin-panel/admin-panel.component';
import { MainPanelComponent } from './_components/main-panel/main-panel.component';
import { ProductComponent } from './_components/product/product.component';
import { PageNotFoundComponent } from './_components/page-not-found/page-not-found.component';
import { getStorage, provideStorage } from '@angular/fire/storage';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { DatePipe } from '@angular/common';
import { AnimateOnScrollModule } from 'ng2-animate-on-scroll';


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
    ProductsComponent,
    AdminPanelComponent,
    MainPanelComponent,
    ProductComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    provideFirebaseApp(() => initializeApp(environment.firebase)),
    provideAnalytics(() => getAnalytics()),
    provideAuth(() => getAuth()),
    provideDatabase(() => getDatabase()),
    provideStorage(() => getStorage()),
    BrowserAnimationsModule,
    MaterialModule,
    AnimateOnScrollModule.forRoot()
  ],
  providers: [
    ScreenTrackingService,
    UserTrackingService, 
    { provide: MAT_DATE_LOCALE, useValue: 'hu-HU' },
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

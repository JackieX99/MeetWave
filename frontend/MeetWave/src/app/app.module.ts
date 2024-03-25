import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './_components/login/login.component';
import { RegisterComponent } from './_components/register/register.component';
import { NotfoundComponent } from './_components/notfound/notfound.component';
import { DashboardComponent } from './_components/dashboard/dashboard.component';
import { AdminpageComponent } from './_components/adminpage/adminpage.component';
import { EventsComponent } from './_components/events/events.component';
import { EventComponent } from './_components/event/event.component';
import { FooterComponent } from './_components/footer/footer.component';
import { MainComponent } from './_components/main/main.component';
import { HttpClientModule } from  '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatExpansionModule} from '@angular/material/expansion';
import { LocalStorageService } from './_services/local-storage.service';
import {Component} from '@angular/core';
import {BreakpointObserver} from '@angular/cdk/layout';
import {StepperOrientation, MatStepperModule} from '@angular/material/stepper';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {AsyncPipe} from '@angular/common';
import { SnackbarComponent } from './_components/_shared/snackbar/snackbar.component';
import { NavbarComponent } from './_components/navbar/navbar.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NotfoundComponent,
    DashboardComponent,
    AdminpageComponent,
    EventsComponent,
    EventComponent,
    FooterComponent,
    MainComponent,
    SnackbarComponent,
    NavbarComponent  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatExpansionModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    AsyncPipe,
  ],
  providers: [MainComponent, LocalStorageService],
  bootstrap: [AppComponent]
})
export class AppModule { }

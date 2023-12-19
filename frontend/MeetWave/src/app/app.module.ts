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
import { FormsModule } from '@angular/forms';

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
    MainComponent  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

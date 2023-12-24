import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotfoundComponent } from './_components/notfound/notfound.component';
import { DashboardComponent } from './_components/dashboard/dashboard.component';
import { EventComponent } from './_components/event/event.component';
import { EventsComponent } from './_components/events/events.component';
import { LoginComponent } from './_components/login/login.component';
import { RegisterComponent } from './_components/register/register.component';
import { MainComponent } from './_components/main/main.component';

const routes: Routes = [
  { path: 'welcome', component: MainComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'event', component: EventComponent },
  { path: 'events', component: EventsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '',   redirectTo: '/welcome', pathMatch: 'full' },
  { path: '**', component: NotfoundComponent }
];

@NgModule({
  // imports: [RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled'})],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

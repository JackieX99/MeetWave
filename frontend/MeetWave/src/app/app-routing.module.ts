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
  { path: 'welcome', component: MainComponent, title: 'MeetWave ~ Üdvözlünk' },
  { path: 'dashboard', component: DashboardComponent, title: 'MeetWave ~ Saját profil' },
  { path: 'event', component: EventComponent, title: 'MeetWave ~ Esemény' },
  { path: 'events', component: EventsComponent, title: 'MeetWave ~ Események' },
  { path: 'login', component: LoginComponent, title: 'MeetWave ~ Bejelentkezés' },
  { path: 'register', component: RegisterComponent, title: 'MeetWave ~ Regisztráció' },
  { path: '',   redirectTo: '/welcome', pathMatch: 'full' },
  { path: '**', component: NotfoundComponent, title: 'MeetWave ~ Eltévedtél?' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled'})],
  // imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

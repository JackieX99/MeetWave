import { HttpClient } from '@angular/common/http';
import { NavigationEnd, NavigationStart, Router } from '@angular/router';
import { NavbarVisibilityService } from './_services/navbar-visibility-service.service';
import { Component, OnInit } from '@angular/core';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  private routes: string[] = ['welcome', 'dashboard', 'event', 'events', 'login', 'register'];


  constructor(private router: Router, public navbarVisibilityService: NavbarVisibilityService) {}

  ngOnInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      console.log(this.router.url)
      this.navbarVisibilityService.visible = this.isRouteVisible(this.router.url);
    });
  }

  private isRouteVisible(url: string): boolean {
    return this.routes.some(route => url.includes(route));
  }

}

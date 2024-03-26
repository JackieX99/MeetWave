import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { AuthService } from 'src/app/_services/auth.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, AfterViewInit {
  activeRoute: string | undefined;
  showDropdown: boolean = false;
  user: any;

  constructor(private router: Router, private route: ActivatedRoute, private us: UserService, private as: AuthService) { }

  ngOnInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.setActiveRoute();
    });

    this.us.user$.subscribe(user => {
      this.user = user;
      console.log(this.user);
    });
  }

  ngAfterViewInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.setUser();
    });
  }

  setActiveRoute(): void {
    this.activeRoute = this.router.url;
  }

  setUser(): void {
    this.user = this.us.getUser();
  }

  logout(): void{
    this.as.logout();
    this.showDropdown = false;
  }
}

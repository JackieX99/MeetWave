import { Component, Inject, LOCALE_ID } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FirebaseService } from 'src/app/_services/firebase.service';

@Component({
  selector: 'app-page-not-found',
  templateUrl: './page-not-found.component.html',
  styleUrls: ['./page-not-found.component.scss']
})
export class PageNotFoundComponent {

  constructor(private router: Router,  private activeRoute: ActivatedRoute, firebaseService: FirebaseService, @Inject(LOCALE_ID) public locale: string) { 
    this.activeRoute.queryParams.subscribe((qp) => {
      firebaseService.missSpelledUrl(this.router.url, locale);
    });
  }

}

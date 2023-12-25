import { Component, Inject, forwardRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LocalStorageService } from 'src/app/_services/local-storage.service';
import { MainComponent } from '../main/main.component';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {

  activeRouteName: string = '';

  constructor(
    private router: Router,
    private localStorage: LocalStorageService,
    private route: ActivatedRoute,
    @Inject(forwardRef(() => MainComponent)) public mainComponent: MainComponent
  ) { }

  navigateToTarget(target: String, page: String) {
    this.route.url.subscribe(url => {
      const routeSegments = url.map(segment => segment.path);
      this.activeRouteName = routeSegments.join('/');
    });
    
    // A felhasználó már az adott oldalon tartózkodik, scroll eventet kell hívni
    if(this.activeRouteName == page){
      if(this.activeRouteName == "welcome"){
        switch(target){
          case "gyik":
            this.mainComponent.navigateToTarget("gyik");
            break;
            case "services":
              this.mainComponent.navigateToTarget("services");
              break;
            default:
              // do nothing              
        }
      } 
    } else{
      this.localStorage.setItem("routeTo", target);
      this.router.navigate(['/' + page]);
    }
  }

  

}

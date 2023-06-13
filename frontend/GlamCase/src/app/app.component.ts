import {
  Component,
  OnInit,
  HostListener,
  Renderer2,
  AfterViewInit,
} from '@angular/core';
import { AuthService } from './_services/auth.service';
import { ViewportScroller } from '@angular/common';
import { getAuth, onAuthStateChanged } from "firebase/auth";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit, AfterViewInit {
  activeRouter: any = '';
  user: any;

  // userDeviceHeight: number = 0;
  // userDeviceWidth: number = 0;
  // mainframeHeight: number = 0;

  constructor(
    private auth: AuthService,
    private viewportScroller: ViewportScroller,
    private renderer: Renderer2
  ) {
    // this.getScreenSize();
  }

  // @HostListener('window:resize', ['$event'])
  // getScreenSize(event?: any) {
  //   this.userDeviceHeight = window.innerHeight;
  //   this.userDeviceWidth = window.innerWidth;
  // }

  // prevScrollpos: any;
  // @HostListener('window:scroll', [])
  // onScroll() {
  //   const scrollPosition = this.viewportScroller.getScrollPosition();

  //   var currentScrollPos = scrollPosition[1];
  //   if (this.prevScrollpos > currentScrollPos) {
  //     this.renderer.setStyle(this.element, 'top', '0px');
  //     if(currentScrollPos > this.mainframeHeight){
  //       this.renderer.setStyle(this.element, 'background-color', 'var(--white)');
  //     } else{
  //       this.renderer.setStyle(this.element, 'background-color', 'transparent');
  //     }
  //   } else {
  //     this.renderer.setStyle(this.element, 'top', '-150px');
  //   }
  //   this.prevScrollpos = currentScrollPos;
  // }

  ngOnInit(): void {
    const auth = getAuth();
    onAuthStateChanged(auth, (user) => {
      if (user) {
        // User is signed in.
        this.user = user;
        this.auth.setUser(user);
        const eventData = {};
        this.auth.userFound();
      } else {
        // User is not signed in.
        // ...
      }
    });

    // const scrollPosition = this.viewportScroller.getScrollPosition();
    // this.prevScrollpos = scrollPosition[1];

    // Viewheight to px konvert user device méret alapján
    // this.mainframeHeight = 75 * (this.userDeviceHeight / 100); // 75 vh = x px    
  }

  element: any;

  ngAfterViewInit() {
    // this.element = this.renderer.selectRootElement('.navbarka', true);
  }


  public onRouterOutletActivate(event: any) {
    this.activeRouter = event.constructor.name; // "PageNotFoundComponent"
  }
}

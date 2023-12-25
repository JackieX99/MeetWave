import { AfterViewChecked, AfterViewInit, Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import { LocalStorageService } from 'src/app/_services/local-storage.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements AfterViewChecked {

  @ViewChild('targetGyik') targetGyikElement!: ElementRef;
  @ViewChild('services') targetServicesElement!: ElementRef;
  
  panelOpenState = false;

  constructor(private localStorage: LocalStorageService) {}

  ngAfterViewChecked(): void {
    let routeto: String = this.localStorage.getItem("routeTo");
    this.localStorage.removeItem("routeTo");
    setTimeout(() => {
      this.navigateToTarget(routeto)
    }, 0);
  }

  public navigateToTarget(target: String){
    switch(target){
      case "gyik":
        this.targetGyikElement.nativeElement.scrollIntoView({ behavior: 'smooth' });
        break;
      case "services":
        this.targetServicesElement.nativeElement.scrollIntoView({ behavior: 'smooth' });
        break;
      default:
        // do nothing
    }
  }
}

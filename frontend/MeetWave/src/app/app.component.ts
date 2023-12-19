import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
  userId: string = '12';
  selectedFile: File | null = null;

  constructor(private http: HttpClient) {}

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  uploadFile(): void {
    if (!this.selectedFile || !this.userId) {
      console.error('Válassz fájlt és add meg a UserID-t!');
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile, this.selectedFile.name);

    const userIdNumber = parseInt(this.userId, 10);
    formData.append('userid', userIdNumber.toString());

    this.http.post<any>('http://88.151.100.4:10046/muteUser', {"userID": 9}).subscribe(
      (response) => {
        console.log('Sikeres fájlfeltöltés:', response);
      },
      (error) => {
        console.error('Hiba történt a fájlfeltöltés során:', error);
      }
    );
  }

}

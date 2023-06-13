import { Injectable, inject } from '@angular/core';
import { Storage, ref, uploadBytesResumable } from '@angular/fire/storage';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  private readonly storage: Storage = inject(Storage);

  constructor() { }

  uploadFile(input: HTMLInputElement) {
    if (!input.files) return

    const files: FileList = input.files;

    for (let i = 0; i < files.length; i++) {
      const file = files.item(i);
      if (file) {
        const storageRef = ref(this.storage, file.name);
        uploadBytesResumable(storageRef, file);
      }
    }
  }
}

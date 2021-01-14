import { Component } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient } from "@angular/common/http"
import {finalize} from "rxjs/operators";
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  baseURL = environment.baseURL;
  title = 'Demo';
  constructor(private app: AppService, private http: HttpClient, private router: Router) {}

  logout() {
    this.http.post(`${this.baseURL}/logout`, {}).pipe(
      finalize(() => {
      this.app.authenticated = false;
      this.router.navigateByUrl('/login');
    })).subscribe();
  }
}

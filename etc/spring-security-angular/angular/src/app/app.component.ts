import { Component } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient } from "@angular/common/http"
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  baseURL = new URL(environment.baseURL);
  title = 'Demo';
  
  constructor(private app: AppService, private http: HttpClient, private router: Router) {}
  
  // withCredentials: true will make the POST request to send the JSESSIONID cookie.
  // logout require 2 things: JSESSIONID cookie and the X-XSRF-TOKEN header.
  logout() {
    this.http.post(`//localhost:8080/logout`, null, {withCredentials: true}).subscribe(() => this.app.setAuthenticated(false));
  }
}

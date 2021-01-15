import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

interface Greeting { id: string, content: string }

@Component({
  templateUrl: './home.component.html'
})
export class HomeComponent {
  baseURL = environment.baseURL;

  title = 'Demo';
  greeting: Greeting = { id: "", content: ""};

  constructor(private app: AppService, private http: HttpClient) {
    if (this.app.authenticated) {
      this.http.get(`${this.baseURL}/resource`, {withCredentials: true}).subscribe(
        (data: Greeting) => this.greeting = data,
        error => {
          console.log(error);
          this.app.setAuthenticated(false);
        }
      );
    }
  }

  authenticated() { return this.app.authenticated; }

}
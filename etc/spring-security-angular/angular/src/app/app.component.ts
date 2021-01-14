import { Component } from '@angular/core';
import { HttpClient } from "@angular/common/http"

interface Greeting {
  id:string, content: string
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  baseURL = "http://localhost:8080";
  title = 'Demo';
  greeting: Greeting = { id: "", content: ""};
  constructor(private http: HttpClient) {
    this.http.get(`${this.baseURL}/resource`).subscribe((data: Greeting) => this.greeting = data);
  }
}

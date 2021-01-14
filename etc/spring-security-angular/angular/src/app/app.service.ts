import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable()
export class AppService {

  baseURL = environment.baseURL;
  authenticated = sessionStorage.getItem("authenticated") === "true";

  constructor(private http: HttpClient) {
  }

  authenticate(credentials, callback) {

    const headers = new HttpHeaders(credentials ? {
        authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get(`${this.baseURL}/user`, {headers: headers, withCredentials: true}).subscribe(response => {
        if (response['name']) {
            this.setAuthenticated(true);
        } else {
            this.setAuthenticated(false);
        }
        return callback && callback();
    });

  }

  setAuthenticated(state: boolean) {
      sessionStorage.setItem("authenticated", state ? "true" : "false");
      this.authenticated = state;
  }

}
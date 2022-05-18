import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {Observable, BehaviorSubject} from 'rxjs';
import {tap, distinctUntilChanged, map} from 'rxjs/operators';
import { of } from 'rxjs';

import {User, UserDTO} from './user.model';
import {TokenStorage} from './token-storage';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {HttpHeaders} from '@angular/common/http';
import {Credentials} from './credentials.model';
import { UsernameStorage } from './user-storage';

const apiUrl = environment.baseApiUrl + '/auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private tokenStore: TokenStorage,
    private usernameStore: UsernameStorage,
    private router: Router) {
  }

  login(credentials: Credentials): Observable<any> {
    return this.http.post<any>(`${apiUrl}/login`, credentials)
      .pipe(
        tap(data => {
          this.tokenStore.set(data.token)
          this.usernameStore.set(data.username)
        })
      );
  }

  register(user: UserDTO): Observable<any> {
    return this.http.post(`${apiUrl}/register`, user, {responseType: 'text'});
  }

  signout() {
    this.tokenStore.destroy();
    this.usernameStore.destroy();
  }

  currentUser(): User {
    return this.usernameStore.get();
  }

  isAuthenticated(): Observable<boolean> {
    const res = Boolean(this.tokenStore.get())
    return of(res)
  }

  getAuthHeaders(): HttpHeaders {
    return new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${this.tokenStore.get()}`);
  }
}


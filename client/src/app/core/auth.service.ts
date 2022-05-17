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
import { UserStorage } from './user-storage';

const apiUrl = environment.baseApiUrl + '/auth';
const registerUrl = environment.baseApiUrl + '/register';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private jwt: TokenStorage,
    private store: UserStorage,
    private router: Router) {
  }

  attempAuth(credentials: Credentials): Observable<any> {
    debugger
    const headers = new HttpHeaders()
      .set('Authorization', 'Basic ' + btoa(credentials.username + ':' + credentials.password));
    return this.http.get<User>(`${apiUrl}/user`, {headers})
      .pipe(
        tap(data => {
          this.store.set(data)
        })
      );
  }

  attempRegister(user: UserDTO): Observable<any> {
    return this.http.post<User>(`${registerUrl}`, user)
      .pipe(
        tap(data => {
          this.store.set(data)
        })
      );
  }

  signout() {
    this.http.get<any>(`${apiUrl}/logout`).subscribe(
      (data) => {
        // reset the initial values
        this.jwt.destroy();
        this.store.destroy();
      }
    );
  }

  currentUser(): User {
    return this.store.get();
  }

  isAuthenticated(): Observable<boolean> {
    const res = Boolean(this.store.get())
    return of(res)
  }
}


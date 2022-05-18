import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from '../core/auth.service';

@Injectable()
export class WonderService {
  private apiUrl = environment.baseApiUrl + '/wonder';

  constructor(private http: HttpClient, private authService: AuthService) { }

  getWonder(): Observable<any> {
    const headers = this.authService.getAuthHeaders();
    return this.http.get(`${this.apiUrl}`, { headers: headers });
  }
}

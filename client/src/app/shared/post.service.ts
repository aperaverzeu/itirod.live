import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from '../core/auth.service';
import { Post } from './post.model';

@Injectable()
export class PostService {

  private courseUrl = environment.baseApiUrl + '/courses';
  private postUrl = environment.baseApiUrl + '/posts';
  authHeaders: HttpHeaders = <HttpHeaders>{};

  constructor(private http: HttpClient, authService: AuthService) {
    this.authHeaders = authService.getAuthHeaders();
  }

  getCourses(): Observable<any> {
    return this.http.get(`${this.courseUrl}`, { headers: this.authHeaders });
  }

  getPostsByTitle(title: string): Observable<any> {
    return this.http.get(`${this.postUrl}/course/${title}`, { headers: this.authHeaders });
  }

  getPosts(): Observable<any> {
    return this.http.get(`${this.postUrl}`, { headers: this.authHeaders });
  }
  
  getPost(id: string): Observable<any> {
    return this.http.get(`${this.postUrl}/${id}`, { headers: this.authHeaders });
  }

  getPostByTitle(title: string): Observable<any> {
    return this.http.get(`${this.postUrl}/post/${title}`, { headers: this.authHeaders });
  }

  savePost(data: Post): Observable<any> {
    return this.http.post(`${this.postUrl}`, data, { headers: this.authHeaders });
  }

  updatePost(id: string, data: Post): Observable<any> {
    return this.http.put(`${this.postUrl}/${id}`, data, { headers: this.authHeaders });
  }

  deletePost(id: string): Observable<any> {
    return this.http.delete(`${this.postUrl}/${id}`, { headers: this.authHeaders });
  }

}

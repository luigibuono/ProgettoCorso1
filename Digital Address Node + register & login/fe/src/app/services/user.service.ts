//user.service
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http'
import { Observable, catchError, throwError } from 'rxjs';
import { User } from '../models/user';
import { environment as env} from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
headers!:HttpHeaders;
  constructor(private http:HttpClient) {
    this.headers= new HttpHeaders({'content-type':'application/json'});
   }

GetUsers():Observable<User[]>{
  return this.http.get<User[]>(env.apiAddress +'/user');
}
GetUserById(id:any):Observable<User>{
  return this.http.get<User>(env.apiAddress +'/user/'+id);
}
AddUser(user: User): Observable<HttpResponse<any>> {
  return this.http
    .post<HttpResponse<any>>(env.apiAddress + '/user/', JSON.stringify(user), {
      headers: this.headers,
      observe: 'response',
    })
    .pipe(catchError((error: any) => {
      console.error('Errore durante la richiesta POST:', error);
      return throwError(error);
    })
    );
}
UpdateUser(user: User): Observable<HttpResponse<any>> {
  return this.http
    .put(env.apiAddress + '/user/' + user._id, JSON.stringify(user), {
      headers: this.headers,
      observe: 'response',
    }).pipe(
    catchError((error: any) => {
      console.error('Errore durante la richiesta PUT:', error);
      return throwError(error);
    })
    );
}
DeleteUser(id:any):Observable<any>{
  return this.http.delete<HttpResponse<any>>(env.apiAddress +'/user/' +id,
  {observe:'response' });
}

searchUsersByName(name: string): Observable<User[]> {
  const params = new HttpParams().set('name', name);
  return this.http.get<User[]>(`${env.apiAddress}/user/search`, { params })
    .pipe(
      catchError((error: any) => {
        console.error('Errore durante la ricerca:', error);
        return throwError(error);
      })
    );
}


   // Funzione per registrare un nuovo utente
   register(user: any): Observable<any> {
    return this.http.post(`${env.apiAddress}/user/register`, user, { headers: this.headers })
      .pipe(
        catchError((error: any) => {
          console.error('Errore durante la registrazione:', error);
          return throwError(error);
        })
      );
  }

  // Funzione per effettuare il login
  login(credentials: any): Observable<any> {
    return this.http.post(`${env.apiAddress}/user/login`, credentials, { headers: this.headers })
      .pipe(
        catchError((error: any) => {
          console.error('Errore durante il login:', error);
          return throwError(error);
        })
      );
  }

logout(): void {
  localStorage.removeItem('token');
  localStorage.removeItem('user');
}

setToken(token: string): void {
  localStorage.setItem('token', token);
}

setUser(user: any): void {
  localStorage.setItem('user', JSON.stringify(user));
}

getToken(): string | null {
  return localStorage.getItem('token');
}

getUser(): any {
  return JSON.parse(localStorage.getItem('user')!);
}

isLoggedIn(): boolean {
  return this.getToken() !== null;
}
}

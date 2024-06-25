import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: User[] = [];
  searchQuery = '';
  searchResults: User[] = [];

  constructor(private userService: UserService, private http: HttpClient) {}

  ngOnInit(): void {
    this.userService.GetUsers().subscribe(
      (res: User[]) => {
        this.users = res;
      },
      error => {
        console.error('Errore durante il recupero degli utenti:', error);
        // Gestione dell'errore lato frontend
      }
    );
  }

  searchUsers(): void {
    if (this.searchQuery.trim() !== '') {
      this.userService.searchUsersByName(this.searchQuery)
        .subscribe(
          (users: User[]) => {
            this.searchResults = users;
          },
          error => {
            console.error('Errore durante la ricerca per nome:', error);
            // Gestione dell'errore lato frontend
          }
        );
    } else {
      this.searchResults = []; // Pulisci i risultati di ricerca se la query è vuota
    }
  }

  deleteUser(id: any): void {
    if (confirm('Sei sicuro di voler eliminare?')) {
      this.userService.DeleteUser(id).subscribe(
        res => {
          if (res.status === 200) {
            this.users = this.users.filter(user => user._id !== id);
            // Aggiorna anche searchResults se necessario
            this.searchResults = this.searchResults.filter(user => user._id !== id);
          }
        },
        error => {
          console.error('Errore durante la cancellazione dell\'utente:', error);
          // Gestione dell'errore lato frontend
        }
      );
    }
  }
}

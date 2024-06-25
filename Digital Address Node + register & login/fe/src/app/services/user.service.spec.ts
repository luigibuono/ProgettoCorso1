import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule,HttpTestingController} from '@angular/common/http/testing';

import { UserService } from './user.service';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';


describe
('UserService', ()=>{
  let userService:UserService;
  let httpTestingController:HttpTestingController;

  beforeEach(()=>{
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    providers: [UserService],
    });

    userService = TestBed.inject(UserService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });
  afterEach(() => {
    httpTestingController.verify(); // Verifica che non ci siano richieste HTTP pendenti.
  });

  it('should be created', () => {
    expect(userService).toBeTruthy();
  });

  it('should retrieve users from the API via GET', () => {
    const mockUsers: User[] = [
      { _id: '1', name: 'User 1',address: 'Italy', contact:'123'  },
      { _id: '2', name: 'User 2',address: 'France', contact: '456' },
    ];

    userService.GetUsers().subscribe((users) => {
      expect(users).toEqual(mockUsers);
    });

    const req = httpTestingController.expectOne(`${environment.apiAddress}/user`);
    expect(req.request.method).toEqual('GET');
    req.flush(mockUsers);
  });


})


import { NgModule,  } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersComponent } from './components/users/users.component';
import { CreateComponent } from './components/create/create.component';

const routes: Routes = [
  {path:'',component:UsersComponent},
  {path:'users',component:UsersComponent},
  {path:'create',component:CreateComponent},
  {path:'edit/:id',component:CreateComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

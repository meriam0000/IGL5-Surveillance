import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {ListeEnseignantsComponent} from "./pages/liste-enseignants/liste-enseignants.component";
import { CrudUsersComponent } from './pages/crud-users/crud-users.component';
import { LoginComponent } from './auth/login/login.component';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'enseignants', component: ListeEnseignantsComponent },
  { path: 'users', component: CrudUsersComponent },
  { path: 'login', component: LoginComponent },

  // Add other routes as necessary
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

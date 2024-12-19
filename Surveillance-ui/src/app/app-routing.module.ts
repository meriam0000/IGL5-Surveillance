import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {ListeEnseignantsComponent} from "./pages/liste-enseignants/liste-enseignants.component";
import { UserManagementComponent } from './pages/user-management/user-management.component';
import { CrudUsersComponent } from './crud-users/crud-users.component';
import { LoginComponent } from './auth/login/login.component';
import { ListeSallesComponent } from './pages/liste-salles/liste-salles.component';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'enseignants', component: ListeEnseignantsComponent },
  { path: 'users', component: CrudUsersComponent },
  { path: 'login', component: LoginComponent },
  { path: 'salles',component: ListeSallesComponent}

  // Add other routes as necessary
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

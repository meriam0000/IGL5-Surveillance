import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ListeEnseignantsComponent } from './pages/liste-enseignants/liste-enseignants.component';
import { LoginComponent } from './auth/login/login.component';
import { AuthGuard } from './auth.guard';
import {CrudUsersComponent} from "./pages/crud-users/crud-users.component";
import {ListeSallesComponent} from "./pages/liste-salles/liste-salles.component"; // Import the AuthGuard

const routes: Routes = [
  { path: '', component: DashboardComponent, canActivate: [AuthGuard] }, // Protect Dashboard
  { path: 'enseignants', component: ListeEnseignantsComponent, canActivate: [AuthGuard] }, // Protect Teachers List
  { path: 'users', component: CrudUsersComponent, canActivate: [AuthGuard] }, // Protect User Management
  { path: 'login', component: LoginComponent }, // Public login route
  { path: 'salles',component: ListeSallesComponent}, // Protect Rooms List

  // Redirect unknown routes to login
  { path: '**', redirectTo: '/login' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

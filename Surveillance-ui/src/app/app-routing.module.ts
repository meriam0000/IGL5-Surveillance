import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ListeEnseignantsComponent } from './pages/liste-enseignants/liste-enseignants.component';
import { UserManagementComponent } from './pages/user-management/user-management.component';
import { CrudUsersComponent } from './crud-users/crud-users.component';
import { LoginComponent } from './auth/login/login.component';
import { AuthGuard } from './auth.guard'; // Import the AuthGuard

const routes: Routes = [
  { path: '', component: DashboardComponent, canActivate: [AuthGuard] }, // Protect Dashboard
  { path: 'enseignants', component: ListeEnseignantsComponent, canActivate: [AuthGuard] }, // Protect Teachers List
  { path: 'users', component: CrudUsersComponent, canActivate: [AuthGuard] }, // Protect User Management
  { path: 'login', component: LoginComponent }, // Public login route

  // Redirect unknown routes to login
  { path: '**', redirectTo: '/login' }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

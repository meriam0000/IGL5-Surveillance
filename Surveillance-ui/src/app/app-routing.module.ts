import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {ListeEnseignantsComponent} from "./pages/liste-enseignants/liste-enseignants.component";

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'enseignants', component: ListeEnseignantsComponent },

  // Add other routes as necessary
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

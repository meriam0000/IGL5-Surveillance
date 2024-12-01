import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  sidebarItems = [
    {
      title: 'Gestion des salles',
      items: [
        { title: 'Liste de salles' },
        { title: 'Disponibilité des Salles' },
      ]
    },
    {
      title: 'Gestion de classe',
      items: [
        { title: 'Liste de classes' },
      ]
    },
    {
      title: 'Gestion de session',
      items: [
        { title: 'Gérer les session' },
        { title: 'Gérer les Surveillances' },
      ]
    },
    {
      title: 'Gestion d’enseignant',
      items: [
        { title: 'Liste des enseignants' },
        { title: 'Profile Enseignant' },
      ]
    },
    {
      title: 'Gestion d’etudiants',
      items: [
        { title: 'Liste des étudiants' },
        { title: 'Profile Etudiant' },
      ]
    },
    {
      title: 'Gestion des Utilisateurs',
      items: [
        { title: 'Gérer les utilisateurs' },
      ]
    },
  ];

}
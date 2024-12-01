import { Component, AfterViewInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements AfterViewInit {

  ngAfterViewInit(): void {
    // Register all chart.js components
    Chart.register(...registerables);

    // Pie Chart (Répartition des Étudiants)
    new Chart('studentDistributionChart', {
      type: 'pie',
      data: {
        labels: ['Science', 'Arts', 'Commerce', 'Others'],
        datasets: [{
          data: [25, 30, 35, 10],
          backgroundColor: ['#42A5F5', '#66BB6A', '#FFCA28', '#FF7043'],
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'top',
          },
        },
      }
    });

    // Bar Chart (Total Hours Surveilled)
    new Chart('surveillanceHoursChart', {
      type: 'bar',
      data: {
        labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4'],
        datasets: [{
          label: 'Total Hours',
          data: [10, 15, 20, 25],
          backgroundColor: '#42A5F5',
        }]
      },
      options: {
        responsive: true,
        scales: {
          x: {
            beginAtZero: true
          },
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }
}

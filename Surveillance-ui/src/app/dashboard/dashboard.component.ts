import { Component, AfterViewInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements AfterViewInit {
  constructor() {
    Chart.register(...registerables);
  }

  ngAfterViewInit(): void {
    this.createSurveillanceHoursChart();
    this.createStudentDistributionChart();
    this.createTeacherDistributionChart();
  }

  createSurveillanceHoursChart(): void {
    new Chart('surveillanceHoursChart', {
      type: 'line',
      data: {
        labels: Array.from({ length: 14 }, (_, i) => (i + 1).toString()),
        datasets: [{
          data: [10, 20, 15, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75],
          borderColor: '#3b82f6',
          tension: 0.4,
          fill: false
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: false
          }
        },
        scales: {
          x: {
            display: false
          },
          y: {
            display: false
          }
        },
        elements: {
          point: {
            radius: 0
          }
        }
      }
    });
  }

  createStudentDistributionChart(): void {
    new Chart('studentDistributionChart', {
      type: 'doughnut',
      data: {
        labels: ['IGL5', 'IDS5', 'ICE', 'IGL4', 'IDS4'],
        datasets: [{
          data: [42, 21, 15, 12, 10],
          backgroundColor: ['#ef4444', '#3b82f6', '#22c55e', '#eab308', '#a855f7']
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'right',
            labels: {
              usePointStyle: true,
              pointStyle: 'circle'
            }
          }
        },
        cutout: '70%'
      }
    });
  }

  createTeacherDistributionChart(): void {
    new Chart('teacherDistributionChart', {
      type: 'doughnut',
      data: {
        labels: ['Informatique', 'Physique', 'Mathématique', 'Chimie', 'Biologie'],
        datasets: [{
          data: [42, 21, 15, 12, 10],
          backgroundColor: ['#ef4444', '#3b82f6', '#22c55e', '#eab308', '#a855f7']
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'right',
            labels: {
              usePointStyle: true,
              pointStyle: 'circle'
            }
          }
        },
        cutout: '70%'
      }
    });
  }
}
import { Component, AfterViewInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements AfterViewInit {
  chart: any;

  ngAfterViewInit() {
    // Register all chart types and components
    Chart.register(...registerables);

    // Create a chart instance
    this.chart = new Chart('myChart', {
      type: 'line', // Change this to 'bar', 'pie', etc., depending on the chart type
      data: {
        labels: ['January', 'February', 'March', 'April', 'May'],
        datasets: [{
          label: 'Sample Data',
          data: [65, 59, 80, 81, 56],
          borderColor: '#42A5F5',
          fill: false
        }]
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }
}

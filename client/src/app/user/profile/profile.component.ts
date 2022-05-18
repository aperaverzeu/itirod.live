import { Component, OnInit, ViewChild } from '@angular/core';
import { UsernameStorage } from 'src/app/core/user-storage';
import { User, UserDTO } from 'src/app/core/user.model';
import { ChartComponent } from "ng-apexcharts";

import {
  ApexNonAxisChartSeries,
  ApexResponsive,
  ApexChart
} from "ng-apexcharts";
import { AuthService } from 'src/app/core/auth.service';
import { Router } from '@angular/router';

export type ChartOptions = {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  responsive: ApexResponsive[];
  labels: any;
  colors: any;
};

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  username: string = '';
  @ViewChild("chart") chart: any;
  public chartOptions: any;

  constructor(private store: UsernameStorage, private authService: AuthService, private router: Router) { 
    this.chartOptions = {
      series: [1, 1, 1],
      chart: {
        width: 600,
        type: "pie"
      },
      labels: ["History", "Culture", "Language"],
      colors: ['#fb8500', '#ffb703', '#219ebc'],
      responsive: [
        {
          breakpoint: 480,
          options: {
            chart: {
              width: 200
            },
            legend: {
              position: "bottom"
            }
          }
        }
      ]
    };
  }

  ngOnInit() {
    this.username = this.store.get();
  }

  logout() {
    this.authService.signout();
    this.router.navigate(['/auth/signin']);
  }

}

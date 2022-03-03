import { Component, OnInit } from '@angular/core';

import { IGrafikon } from '../grafikon.model';
import { GrafikonService } from '../service/grafikon.service';
import { Chart } from 'chart.js';

@Component({
  selector: 'jhi-grafikon',
  templateUrl: './grafikon.component.html',
})
export class GrafikonComponent implements OnInit {
  grafikons?: IGrafikon[];
  isLoading = false;
  chart: any = [];
  // public pieChartLabels = ['Sales Q1', 'Sales Q2', 'Sales Q3', 'Sales Q4'];
  // public pieChartData = [120, 150, 180, 90];
  // public pieChartType = 'pie';

  constructor(protected grafikonService: GrafikonService) {}
  ngOnInit(): void {
    this.podaci();
  }
  loadAll(): void {
    this.isLoading = true;

    // this.grafikonService.query().subscribe(
    //   (res: HttpResponse<IGrafikon[]>) => {
    //     this.isLoading = false;
    //     this.grafikons = res.body ?? [];
    //     // eslint-disable-next-line no-console
    //     console.log("To je ",this.grafikons)
    //
    //   },
    //   () => {
    //     this.isLoading = false;
    //   }
    // );
  }
  private podaci(): any {
    this.grafikonService.dailyForecast().subscribe((res: any[]) => {
      const region = res.map((res: { region: string }) => res.region);
      const prodaja = res.map((res: { prodaja: number }) => res.prodaja);

      this.chart = new Chart('canvas', {
        // type: 'bar',
        type: 'pie',
        data: {
          labels: region,
          datasets: [
            {
              data: prodaja,
              backgroundColor: ['red', 'yellow', 'blue'],
              borderColor: '#3cba9f',
              // fill: true
            },
          ],
        },
      });
    });
  }
}

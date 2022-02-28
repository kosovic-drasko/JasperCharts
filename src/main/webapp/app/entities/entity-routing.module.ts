import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'report',
        data: { pageTitle: 'Reports' },
        loadChildren: () => import('./report/report.module').then(m => m.ReportModule),
      },

      {
        path: 'grafikon',
        data: { pageTitle: 'Grafikons' },
        loadChildren: () => import('./grafikon/grafikon.module').then(m => m.GrafikonModule),
      },

      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}

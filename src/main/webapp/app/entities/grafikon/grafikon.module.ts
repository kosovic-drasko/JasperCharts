import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { GrafikonComponent } from './list/grafikon.component';
import { GrafikonDetailComponent } from './detail/grafikon-detail.component';
import { GrafikonRoutingModule } from './route/grafikon-routing.module';
import { NgChartsModule } from 'ng2-charts';

@NgModule({
  imports: [SharedModule, GrafikonRoutingModule, NgChartsModule],
  declarations: [GrafikonComponent, GrafikonDetailComponent],
})
export class GrafikonModule {}

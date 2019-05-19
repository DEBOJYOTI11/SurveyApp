import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ChartsModule } from 'ng2-charts';

import { AppComponent } from './app.component';
import { ChartComponentComponent } from './chart-component/chart-component.component';
import { PollComponentComponent } from './poll-component/poll-component.component';
import { UiModule } from './ui/ui.module';
import { HttpClientModule } from '@angular/common/http';


import { PollService} from '../app/service/poll.service';
import { ChartServiceService} from '../app/service/chart-service.service';

@NgModule({
  declarations: [
    AppComponent,
    ChartComponentComponent,
    PollComponentComponent
  ],
  imports: [
    BrowserModule,
    ChartsModule,
    UiModule,
    HttpClientModule
  ],
  providers: [PollService, ChartServiceService],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule {

  constructor () { }
 }

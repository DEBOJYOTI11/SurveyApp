import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {ChartServiceService} from '../service/chart-service.service';
import { ChoiceVoteCount } from '../model/choiceVoteCount';
import { BaseChartDirective } from 'ng2-charts/ng2-charts';

import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-chart-component',
  templateUrl: './chart-component.component.html',
  styleUrls: ['./chart-component.component.css']
})
export class ChartComponentComponent  implements OnInit {
  @ViewChild(BaseChartDirective) chart: BaseChartDirective;
  // socket
  private serverUrl = 'http://localhost:8080/socket';
  private stompClient;

  public doughnutChartLabels: any[] =  ['Download Sales', 'In-Store Sales', 'Mail-Order Sales'];
  public doughnutChartData: number[] =  [350, 450, 100];

  public doughnutChartType: any = 'doughnut';

  choiceVoteCount: ChoiceVoteCount;

  constructor(private chartService: ChartServiceService ) {

    this.initializeWebSocketConnection();

    this.chartService.get_vote_results().subscribe(res => {
                  this.choiceVoteCount = res;
                  this.mapVoteStoChart();
                  this.refresh_chart();
    });
  }
  ngOnInit() {

  }
  loadChartData = () => {

  }
  mapVoteStoChart = () => {
    this.doughnutChartLabels.length = 0;
    this.doughnutChartData.length = 0;

    this.choiceVoteCount.voteCount.forEach(ele => {
      console.log(ele);
      this.doughnutChartLabels.push(ele.name);
      this.doughnutChartData.push(ele.value);
      });
      console.log('one' + this.doughnutChartLabels);
      console.log('two' + this.doughnutChartData);
  }

  initializeWebSocketConnection = () => {
    const ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;

    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe('/chat', (message) => {
        if ( message) {
          console.log('Choice vote count data recieved ' + message.body.voteCount);
          this.choiceVoteCount  =  message.body.voteCount;
 
          this.mapVoteStoChart();
          this.refresh_chart();
        }
      });
    });
  }

  sendMessage = (message) => {
    this.stompClient.send('/app/send/message' , {}, message);
  }
  refresh_chart() {
    setTimeout(() => {    
        console.log('d' +  this.doughnutChartLabels);
        console.log( this.doughnutChartData);
        if (this.chart && this.chart.chart && this.chart.chart.config) {
          console.log('red');
            this.chart.chart.config.data.labels =   this.doughnutChartLabels;
            this.chart.chart.config.data.data =  this.doughnutChartData;
            this.chart.chart.update();
        }
    });
}


}

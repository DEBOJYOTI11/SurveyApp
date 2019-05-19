import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import { HttpClient , HttpErrorResponse } from '@angular/common/http';
import { Poll } from '../model/poll';
import { Choice } from '../model/choice';

@Injectable()
export class ChartServiceService {

  baseUrl  = 'http://localhost:8080/poll';

  constructor(private httpClient: HttpClient) { }

  get_vote_results  = (): Observable<any>  => {
     return this.httpClient.get(this.baseUrl + '/getPollResults/1');
  }

}

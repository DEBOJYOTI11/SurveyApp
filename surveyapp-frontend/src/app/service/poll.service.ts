import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import { HttpClient , HttpErrorResponse } from '@angular/common/http';
import { Poll } from '../model/poll';
import { Choice } from '../model/choice';
@Injectable()
export class PollService {

  baseUrl  = 'http://localhost:8080/poll';

  constructor(private httpClient: HttpClient) { }

  get_latest_poll  = (): Observable<any>  => {
     return this.httpClient.get(this.baseUrl + '/getAll');
  }

  savePoll = (ch: Choice, poll: Poll): void => {
    this.httpClient.post(this.baseUrl + '/saveVote', {
      'pollId': poll.id,
      'choiceId': ch.id
    })
    .subscribe(
      res => console.log(res),
      err => console.log(err)
    );
  }
}



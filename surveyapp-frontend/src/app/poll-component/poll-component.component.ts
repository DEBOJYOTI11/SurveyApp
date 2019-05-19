import { Component, OnInit } from '@angular/core';
import {Poll} from '../model/poll';

import {PollService} from '../service/poll.service';
import { Choice } from '../model/choice';

@Component({
  selector: 'app-poll-component',
  templateUrl: './poll-component.component.html',
  styleUrls: ['./poll-component.component.css']
})
export class PollComponentComponent implements OnInit {

  poll: Poll;

  constructor(private pollservice: PollService) {
    this.pollservice.get_latest_poll().subscribe(res => {
      this.poll = res[0];
    });
  }

  ngOnInit() {
  }

  savePoll = (ch: Choice) => {
    this.pollservice.savePoll(ch, this.poll);
  }
//   clearPoll() {
//     this.ngRedux.dispatch({type: REMOVE_POLL});
//   }
//  addPoll(poll: Poll) {
//     console.log('add poll called');
//     this.ngRedux.dispatch({ type: ADD_POLL, model:  this.model });
//   }

//   addChoice(choice: Choice) {
//     this.ngRedux.dispatch({type: ADD_CHOICE, choice: choice });
//   }
}














































































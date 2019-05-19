import { Vote} from '../model/vote';

export interface ChoiceVoteCount {
  voteCount: Vote[];
  totalNumberOfVotes: number;
}

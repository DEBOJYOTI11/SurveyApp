import { Choice} from '../model/choice';

export interface Poll {
  id: number;
  question: String;
  choices: Choice[];
}

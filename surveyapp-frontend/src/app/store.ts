// import { ADD_CHOICE, ADD_POLL , REMOVE_CHOICE, REMOVE_POLL, REMOVE_ALL_CHOICE } from './actions';
// import {Poll} from '../app/model/poll';
// import { Choice } from './model/choice';
// export interface AppStore {
//   campaigns: Campaign[];
//   // other properties...
// }
// export const campaigns = (state: any = [], {type, payload}) => {
//   switch (type) {
//       case 'ADD_CAMPAIGNS':
//           return payload;
//       case 'CREATE_CAMPAIGN':
//           return [...state, payload];
//       case 'UPDATE_CAMPAIGN':
//           return state.map(campaign => {
//               return campaign.token === payload.token ? Object.assign({}, campaign, payload): campaign;
//           });
//       case 'DELETE_CAMPAIGN':
//           return state.filter(campaign => {
//               return campaign.token !== payload.token;
//           });
//       default:
//           return state;
//   }
// }

import {StompConfig} from '@stomp/ng2-stompjs';
import * as SockJS from 'sockjs-client';

export const stompConfig: StompConfig = {
  url: socketProvider,
  heartbeat_in: 0,
  heartbeat_out: 3000,
  reconnect_delay: 5000,
  headers: {},
  debug: false,
};

export function socketProvider() {
 // return new SockJS('/legacy-web-ws');
}

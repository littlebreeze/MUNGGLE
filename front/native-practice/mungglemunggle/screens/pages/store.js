// store.js
import { createStore } from 'redux';
import rootReducer from './reducers'; // 리듀서 함수가 있는 파일

const store = createStore(rootReducer);

export default store;

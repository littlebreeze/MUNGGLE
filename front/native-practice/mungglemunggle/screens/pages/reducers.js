// reducers.js
const initialState = {
  isLoggedIn: false,
  // 다른 초기 상태 필드들도 추가 가능
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'LOGIN_SUCCESS':
      return {
        ...state,
        isLoggedIn: true,
      };
    case 'LOGOUT':
      console.log('로그아웃');
      return {
        ...state,
        isLoggedIn: false,
      };
    default:
      return state;
  }
};

export default rootReducer;

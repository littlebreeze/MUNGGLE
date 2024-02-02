import AsyncStorage from '@react-native-community/async-storage';
import axios from 'axios';
import { loginSuccess } from './actions';

//로그인(/login) 후 마이페이지(/mypage)까지 바로 해결 예정

export const login = async (username, password, dispatch) => {
  try {
    const response = await axios.post(
      'http://i10a410.p.ssafy.io:8080/login',
      `username=${username}&password=${password}`,
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
      }
    );

    // 서버 응답에서 토큰 추출
    const token = response.headers.authorization;

    // AsyncStorage에 토큰 저장
    await AsyncStorage.setItem('token', token);

    dispatch(loginSuccess()); //로그인 성공 시 액션 디스패치
    console.log('Login successful:', response.data);
    console.log('token: ',token);
    
    return response.data; // 서버에서 반환된 데이터를 반환합니다.

  } catch (error) {
    console.error('Error posting data:', error);
    throw error; // 에러가 발생하면 상위 컴포넌트로 에러를 전파합니다.
  }
};

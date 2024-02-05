import AsyncStorage from '@react-native-community/async-storage';
import axios from 'axios';

//username, password 필요
//로그인(/login) 후 마이페이지(/mypage)까지 진행

export const login = async (username, password) => {
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
    // 추후 refresh 토큰도 저장 예정 (아마)
    await AsyncStorage.setItem('token', token);

    console.log('Login successful:', response.data);
    console.log('token: ',token);

    const responseMyData = await axios.get('http://i10a410.p.ssafy.io:8080/users/mypage', {
      headers: {
        'Authorization': `${token}`,
      },
    });

    console.log(`내 id: ${responseMyData.data.id}`);

    const id = responseMyData.data.id;

    //내 userID 저장
    await AsyncStorage.setItem('myID', id.toString());

  } catch (error) {
    window.alert('아이디 혹은 비밀번호가 틀립니다.')
    console.error('Error posting data:', error);
    throw error;
  }
};

import React, {useEffect, useState} from 'react';
import {View, Text, StyleSheet} from 'react-native';
import axios from 'axios';

//로그인 성공
const App = () => {
  const [postData, setPostData] = useState(null);

  useEffect(() => {
    // POST 요청 보내는 함수
    const postDataExample = async () => {
      try {
        const response = await axios.post(
          'http://i10a410.p.ssafy.io:8080/login',
          `username=az3024@ssafy.com&password=11111111`,
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
            },
          }
        );

        console.log('Post successful:', response.data);
        setPostData(response.data);

      } catch (error) {
        console.error('Error posting data:', error);
      }
    };

    postDataExample(); // 컴포넌트가 마운트될 때 POST 요청 보내기
  }, []); // 빈 배열을 전달하여 한 번만 호출되도록 설정

  return (
    <View style={styles.container}>
      {postData ? (
        <Text>{JSON.stringify(postData)}</Text>
      ) : (
        <Text>Loading...</Text>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default App;

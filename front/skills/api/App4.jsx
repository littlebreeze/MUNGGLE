import React, {useEffect, useState} from 'react';
import {View, Text, StyleSheet, Button} from 'react-native';
import axios from 'axios';

// 로그인 후 PUT
const App = () => {
  const [token, setToken] = useState(null);

  const login = async () => {
    try {
      const response = await axios.post(
        'http://i10a410.p.ssafy.io:8080/login',
        'username=az3024@ssafy.com&password=11111111',
        {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
          },
        },
      );

      console.log('Login successful:', response.data);
      setToken(response.request.responseHeaders['Set-Cookie'].split(';')[0]);
      console.log(token);
    } catch (error) {
      console.error('Error logging in:', error);
    }
  };

  const editDog = async () => {
    const edit = axios
      .put(
        'http://i10a410.p.ssafy.io:8080/dogs/2',
        {
          dto: {
            kindId: 57,
            birthDate: '2022-02-02T20:20:20',
            size: null,
            weight: null,
            gender: null,
            isNeutering: true,
            name: '삐삐222',
            description: '삐삐',
          },
        },
        {
          headers: {
            Cookie: token,
          },
        },
      )
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  useEffect(() => {
    // Login when the component mounts
    login();
  }, []);

  return (
    <View style={styles.container}>
      <Text>Login Data:</Text>

      <Button title="Register Dog" onPress={editDog} />

      <Text>Dog Registration Data:</Text>
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

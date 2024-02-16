import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Button } from 'react-native';
import axios from 'axios';
//로그인 후 delete(성공)
const App = () => {
  const [loginData, setLoginData] = useState(null);
  const [dogRegistrationData, setDogRegistrationData] = useState(null);

  // Function to handle login
  const login = async () => {
    try {
      const response = await axios.post(
        'http://i10a410.p.ssafy.io:8080/login',
        'username=az3024@ssafy.com&password=11111111',
        {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
          },
        }
      );

      console.log('Login successful:', response.data);
      setLoginData(response.data);

    } catch (error) {
      console.error('Error logging in:', error);
    }
  };

  const deleteDog = async () => {
    try {
      const response = await axios.delete('http://i10a410.p.ssafy.io:8080/dogs/4');
      setDogRegistrationData(response.data);
      console.log(dogRegistrationData);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    // Login when the component mounts
    login();
  }, []);

  return (
    <View style={styles.container}>
      <Text>Login Data:</Text>
      {loginData ? (
        <Text>{JSON.stringify(loginData)}</Text>
      ) : (
        <Text>Loading...</Text>
      )}

      <Button title="Register Dog" onPress={deleteDog} />

      <Text>Dog Registration Data:</Text>
      {dogRegistrationData ? (
        <Text>{JSON.stringify(dogRegistrationData)}</Text>
      ) : (
        <Text>Register a dog to see the data.</Text>
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

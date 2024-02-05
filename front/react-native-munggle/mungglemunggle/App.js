import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, Dimensions } from 'react-native';
import * as Font from "expo-font";

import AsyncStorage from '@react-native-async-storage/async-storage';

import Body from './screens/layout/body';
import Nav from './screens/layout/nav';

import LoginScreen from './screens/pages/login';

// nav : 10 %
// body : 82 %
// footer : 8 %

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

const getFonts = () => Font.loadAsync({
  "pretendard": require("./assets/fonts/Pretendard-Regular.ttf"),
});

export default function App() {
  const [isLogin, setIsLogin] = useState(true);

  useEffect(() => {
    getFonts;
    AsyncStorage.setItem("API_URL", "http://i10a410.p.ssafy.io:8080");
  }, []);

  if (isLogin) {
    return (
      <View style={styles.container}>
        <View style={{ width: SCREEN_WIDTH, flex: 1}}>
          <Nav style={{ width: SCREEN_WIDTH, flex: 1}}/>
        </View>
        
        <View style={{ width: SCREEN_WIDTH, flex: 9}}>
          <Body style={{ width: SCREEN_WIDTH, flex: 1}}/>
        </View>
      </View>
    );
  } else {
    return (
      <LoginScreen setIsLogin={setIsLogin} />
    );
  }

}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#fff',
    alignItems: 'center',
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT,
    flex: 1,
  },
});
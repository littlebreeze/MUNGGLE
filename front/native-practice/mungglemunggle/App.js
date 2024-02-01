import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, Dimensions } from 'react-native';
import * as Font from "expo-font";
    
import HomeScreen from './screens/pages/post';
import MatchScreen from './screens/pages/match';
import WalkScreen from './screens/pages/walk';

import Body from './screens/layout/body';
import Nav from './screens/layout/nav';

// nav : 10 %
// body : 82 %
// footer : 8 %

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

const getFonts = () => Font.loadAsync({
  "pretendard": require("./assets/fonts/Pretendard-Regular.ttf"),
});

export default function App() {
  useEffect(() => {
    getFonts
  }, []);

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
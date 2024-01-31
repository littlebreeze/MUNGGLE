import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, Dimensions } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import * as Font from "expo-font";
    
import HomeScreen from './screens/pages/post';
import MatchScreen from './screens/pages/match';
import WalkScreen from './screens/pages/walk';

import Body from './screens/layout/body';
import Nav from './screens/layout/nav';

const Stack = createNativeStackNavigator();

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

const getFonts = () => Font.loadAsync({
  "pretendard": require("./assets/fonts/Pretendard-Regular.ttf"),
});

export default function App() {
  useEffect(() => {
    getFonts
  }, []);

  return (
    <NavigationContainer style={styles.container}>
      <View style={{ width: SCREEN_WIDTH, height: SCREEN_HEIGHT * 0.10}}>
        <Nav />
      </View>
      
      <Stack.Navigator
        style={{ width: SCREEN_WIDTH, height: SCREEN_HEIGHT * 0.90}}
        screenOptions={{
          headerShown: false,
        }}
      >
        <Stack.Screen 
          name="Body"
          component={Body}
        />
      </Stack.Navigator>
    </NavigationContainer>
  
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
    padding: 20,
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT,
  },
});
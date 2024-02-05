import React, { useEffect, useState } from 'react';
import { Text, View, StyleSheet, Dimensions, TouchableOpacity } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import axios from 'axios';
import WebView from 'react-native-webview';
import * as Font from "expo-font";
import { Provider } from 'react-redux'; // react-redux용으로 추가
import store from './screens/pages/store';
    
import HomeScreen from './screens/pages/post';
import MatchScreen from './screens/pages/match';
import WalkScreen from './screens/pages/walk';

import Body from './screens/layout/body';
import Nav from './screens/layout/nav';

const Stack = createNativeStackNavigator();

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")



export default function App() {

  const [data, setData] = useState("");

    async function postData() {
      try {
        //응답 성공 
        const response = await axios.post('http://i10a410.p.ssafy.io:8080/oauth2/authorization/naver',{
            //보내고자 하는 데이터 

        },
        // {
        //   headers: {
        //     "Content-Type": "multipart/form-data",    //400에러
        //     "Content-Type": "application/x-www-form-urlencoded",    //500에러
        //     "Content-Type": "application/json"//500에러
        //   },
        // }
        );
        console.log(response.data);
        setData(response.data);
      } catch (error) {
        //응답 실패
        console.error(error);
      }
    };



  const handleButtonPress = () => {
    console.log('Button Pressed!');
    postData();
  };

  return (
    <View>
      <TouchableOpacity style={{marginTop:'20%'}} onPress={handleButtonPress}>
        <Text style={styles.buttonText}>Press Me</Text>
      </TouchableOpacity>
      <View style={{height: 300 }}>
      <WebView
        source={{ html: data }}
      />
      </View>
      <Text>123</Text>
      </View>
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
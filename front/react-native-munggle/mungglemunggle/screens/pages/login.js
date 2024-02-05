import React, {useEffect, useState} from "react";
import { View, Text, TextInput, 
  Image, TouchableOpacity, Dimensions,
  StyleSheet, 
} from "react-native";
import logo from "../../assets/icons/logo.png";
import AsyncStorage from "@react-native-async-storage/async-storage";
import * as AuthSession from "expo-auth-session";
import axios from "axios";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function LoginScreen (props) {
  const [apiUrl, setApiUrl] = useState(AsyncStorage.getItem("API_URL"));

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {
    const payLoad = { username, password };
    // console.log(payLoad);
    // console.log(apiUrl._j);
    // console.log(`${apiUrl._j}/login/`);
    axios.post(
      `${apiUrl._j}/login`,
      payLoad,
      {headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      }})
      .then((res) => {
        AsyncStorage.setItem("accessToken", res.headers.get("access-token"));
        AsyncStorage.setItem("accessToken", res.headers.get("refresh-token"));
        props.setIsLogin(true);
      })
      .catch((err) => console.log(err))
  };



  return (
    <View style={styles.loginContainer}>
      <Image 
        style={styles.loginTopImage} 
        source={logo}
      />
      
      <TextInput 
        placeholder="아이디"
        value={username}
        onChangeText={(text) => setUsername(text)}
        style={styles.usernameInput} 
      />
      
      <TextInput
        placeholder="비밀번호"
        value={password}
        onChangeText={(text) => setPassword(text)}
        secureTextEntry={true} 
        style={styles.passwordInput} 
      />
      
      <TouchableOpacity 
        style={styles.loginButton} 
        onPress={handleLogin}
      >
        <Text style={styles.loginButtonText}>로그인</Text>
      </TouchableOpacity>
      
      <View style={styles.signInButtonView}>
        <TouchableOpacity>
          <Text>회원가입</Text>
        </TouchableOpacity>
        <TouchableOpacity>
          <Text>아이디/비밀번호 찾기</Text>
        </TouchableOpacity>
      </View>
      
      <View style={{ flexDirection: "row", alignItems: "center", marginVertical: 20 }}>
        <View style={{ flex: 1, height: 1, backgroundColor: "black" }} />
        <Text style={{ marginHorizontal: 10 }}>또는</Text>
        <View style={{ flex: 1, height: 1, backgroundColor: "black" }} />
      </View>
      
      <TouchableOpacity 
        style={styles.googleLoginButton}
      >
        <Text>구글로 시작하기</Text>
      </TouchableOpacity>
      
      <TouchableOpacity 
        style={styles.kakaoLoginButton}
      >
        <Text>카카오로 시작하기</Text>
      </TouchableOpacity>
      
      <TouchableOpacity 
        style={styles.naverLoginButton}
      >
        <Text>네이버로 시작하기</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  loginContainer: {
    flex: 1, 
    justifyContent: "center", 
    alignItems: "center",
  },

  loginTopImage: {
    width: "45%", 
    height: "10%", 
    marginBottom: "20%"
  },

  usernameInput: {
    height: 40, 
    borderColor: "gray", 
    borderWidth: 1, 
    marginBottom: 10, 
    padding: 10, 
    width: 200,
  },
  passwordInput: {
    height: 40, 
    borderColor: "gray", 
    borderWidth: 1, 
    marginBottom: 20, 
    padding: 10, 
    width: 200
  },

  loginButton: {
    backgroundColor: "blue", 
    padding: 10, 
    borderRadius: 5, 
    marginBottom: 20,
  },
  loginButtonText: {
    color: "white", 
    textAlign: "center", 
  },
  
  signInButtonView: {
    flexDirection: "row", 
    justifyContent: "space-between", 
    width: "100%"
  },

  googleLoginButton: {
    backgroundColor: "white", 
    padding: 10, 
    borderRadius: 5, 
    marginBottom: 10,
  },
  kakaoLoginButton: {
    backgroundColor: "yellow", 
    padding: 10, 
    borderRadius: 5, 
    marginBottom: 10,
  },
  naverLoginButton: {
    backgroundColor: "green", 
    padding: 10, 
    borderRadius: 5, 
    marginBottom: 10,
  },
});
import React, { useEffect, useState } from "react";
import { View, Image, StyleSheet,
  ScrollView, TouchableOpacity, Button,
  Dimensions,
  TextInput,
  KeyboardAvoidingView,
  Platform   
} from "react-native";

import * as ImagePicker from 'expo-image-picker';

import iconClose from "../../assets/icons/close1.png";
import iconCamera from "../../assets/icons/camera.png";
import iconSend from "../../assets/icons/send.png";

import axios, { Axios } from "axios";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function PostCreate (props) {
  const [images, setImages] = useState([]);
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [tags, setTags] = useState([]);

  const pickImage = async () => {
    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.All,
      allowsEditing: true,
      aspect: [4, 3], //비율 변경 가능
      quality: 1,
    });

    console.log(result);

    if (!result.canceled) {
      setImages([...images, result.assets[0].uri]);
    }
  };

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === "ios" ? "padding" : "height"}
      keyboardVerticalOffset={100}
      style={styles.createModalBackGround}
    >

    {/*사진이 오는 곳*/}
    <View style={styles.createModalBackGround}>
      <View style={styles.createModalContainer}>
        <ScrollView style={styles.createModalScrollView}>
          <TouchableOpacity
            style={[styles.closeView, { zIndex: 2 },]}
            onPress={props.closeCreateModal}
            >
            <Image 
              style={styles.closeImage}
              source={iconClose}
            />
          </TouchableOpacity>

          <View style={{height:260}}>
            <ScrollView
              horizontal={true}
              showHorizontalScrollIndicator={false}
              style={{paddingVertical: 20, backgroundColor: 'gray'}}>
                {images.map((uri, index) => (
                <Image key={index} source={{ uri }} style={{ width: 200, height: 200, marginRight: 10, marginTop: 20 }} />
                      
                    ))}
            </ScrollView>
          </View>

          {/* <Button title="사진 추가" onPress={pickImage} /> */}
    
          <View style={styles.buttonContainer}>
          {/* 왼쪽 카메라 버튼 */}
          <TouchableOpacity
            style={styles.cameraButton}
            onPress={pickImage}
          >
            <Image
              source={iconCamera}
              style={{width:40, height:40}}
            />
          </TouchableOpacity>

          {/* 오른쪽 전송 버튼 */}
          <TouchableOpacity
            style={styles.sendButton}
            onPress={() => console.log('Send button pressed')}
          >
            <Image 
              source={iconSend}
              style={{width:33, height:33}}
            />
          </TouchableOpacity>
          </View>
  

          {/* 제목 (Title) */}
          <View style={styles.postCreateMiddleView}>
            <TextInput
              placeholder="제목을 입력하세요"
              value={title}
              onChangeText={setTitle}
              style={styles.textInput}
            />
          </View>

          {/* 태그 (Tags) */}
          <View style={styles.postCreateMiddleView}>
            <TextInput
              placeholder="태그를 입력하세요 (#강아지 #산책)"
              value={tags}
              onChangeText={setTags}
              style={styles.textInput}
            />
          </View>


          {/* 내용 (Content) */}
          <View style={styles.postCreateBottomView}>
            <TextInput
              placeholder="내용을 입력하세요"
              value={content}
              onChangeText={setContent}
              style={styles.textInput}
              multiline
            />
          </View>

          <Button title="제출"/>
        </ScrollView>
      </View>
    </View>
    </KeyboardAvoidingView>
  );
}

const styles = StyleSheet.create({
  createModalBackGround: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT,
    justifyContent: "center",
    alignItems: "center",
  },
  createModalContainer: {
    justifyContent: "center",
    alignItems: "center",
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.78,
    backgroundColor: "white",
    marginBottom: SCREEN_HEIGHT * 0.03,
    padding: SCREEN_WIDTH * 0.0,
    position: "relative",
  },
  createModalScrollView: {
  },

  closeView: {
    width: SCREEN_WIDTH * 0.06,
    height: SCREEN_WIDTH * 0.06,
    position: "absolute",
    top: 0,
    right: 10,
    marginTop: 10
  },
  closeImage: {
    width: SCREEN_WIDTH * 0.06,
    height: SCREEN_WIDTH * 0.06,
  },

  postCreateTopView: {
    marginTop: SCREEN_HEIGHT * 0.03,
    width: SCREEN_WIDTH * 0.8,
    height: SCREEN_HEIGHT * 0.05,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "gray",
    marginLeft: SCREEN_WIDTH * 0.05,
  },

  postCreateMiddleView: {
    marginTop: SCREEN_HEIGHT * 0.02,
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.05,
    justifyContent: "center",
    marginLeft: SCREEN_WIDTH * 0.02,
    padding: 10,
    borderRadius: 10, // 모서리 둥근 정도
    borderColor: 'gray', // 테두리 색상
    borderWidth: 1, // 테두리 두께
  },


  postCreateBottomView: {
    marginTop: SCREEN_HEIGHT * 0.03,
    marginBottom: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.175,
    marginLeft: SCREEN_WIDTH * 0.02,
    padding: 10,
    borderRadius: 10, // 모서리 둥근 정도
    borderColor: 'gray', // 테두리 색상
    borderWidth: 1, // 테두리 두께
  },

  cameraButton: {
    position: 'relative',
    left: 10,
    backgroundColor: 'transparent',
    padding: 10,
    zIndex: 2,
  },

  sendButton: {
    position: 'relative',
    backgroundColor: 'transparent',
    padding: 10,
    zIndex: 2,
    marginLeft: SCREEN_WIDTH*0.62
  },

  buttonContainer: {
    flexDirection: 'row',  // 버튼들을 가로로 나열
    position: 'relative',
  },
});
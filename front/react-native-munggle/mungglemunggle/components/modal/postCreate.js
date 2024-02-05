import React, { startTransition, useEffect, useState } from "react";
import { View, Image, StyleSheet,
  ScrollView, TouchableOpacity, Button,
  Dimensions, Text,
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

  useEffect(() => {
    console.log(images);
  }, [images]);
  
  const pickImage = () => {
    ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.All,
      allowsEditing: true,
      aspect: [4, 3], //비율 변경 가능
      quality: 1,
      }).then((res) => {
        setImages([...images, res.assets[0]]);
      }).catch((err) => {
        console.log(err)
      })
    };


  const preViewImages = () => {
    return (
      <ScrollView
        horizontal={true}
        showHorizontalScrollIndicator={false}
      >
        {images && images.map((image, index) => {
          const uri = image.uri;

          return (
            <View key={index} style={styles.previewImageView}>
              <Image 
                source={{uri}} 
                style={styles.previewImage} 
              />
            </View>
          );
        })}
      </ScrollView>
    );
  }

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === "ios" ? "padding" : "height"}
      keyboardVerticalOffset={100}
      style={styles.createModalBackGround}
    >

    <View style={styles.createModalBackGround}>
      <View style={styles.createModalContainer}>
        <ScrollView style={styles.createModalScrollView}>
          <TouchableOpacity
            style={styles.closeView}
            onPress={props.closeCreateModal}
            >
            <Image 
              style={styles.closeImage}
              source={iconClose}
            />
          </TouchableOpacity>

          <View style={styles.postCreateTopView}>
            {preViewImages()}
          </View>

          <View style={styles.postCreateMiddleView}>
            <View style={styles.buttonContainer}>
              <TouchableOpacity
                style={styles.cameraButtonView}
                onPress={() => pickImage()}
              >
                <Image
                  source={iconCamera}
                  style={styles.cameraButton}
                />
              </TouchableOpacity>
            </View>
          </View>

          <View style={styles.postCreateBottomView}>
            <View style={styles.postCreateTextInputView}>
              <TextInput
                placeholder="제목을 입력하세요"
                value={title}
                onChangeText={setTitle}
                style={styles.postCreateTextInput}
              />
            </View>

            <View style={styles.postCreateTextInputView}>
              <TextInput
                placeholder="태그를 입력하세요 (#강아지 #산책)"
                value={tags}
                onChangeText={setTags}
                style={styles.postCreateTextInput}
              />
            </View>

            <View style={styles.postCreateTextAreaView}>
              <TextInput
                placeholder="내용을 입력하세요"
                textAlignVertical="top"
                value={content}
                onChangeText={setContent(content)}
                style={styles.postCreateTextArea}
                multiline
              />
            </View>
          </View>

          <View style={styles.postCreateSubmitView}>
            <TouchableOpacity
             style={styles.postCreateSubmitTouchView}
            >
              <Text style={styles.postCreateSubmitText}>제출</Text>
            </TouchableOpacity>
          </View>

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
    borderRadius: 30,
    backgroundColor: "white",
    marginBottom: SCREEN_HEIGHT * 0.03,
    position: "relative",
  },
  createModalScrollView: {
  },

  closeView: {
    width: SCREEN_WIDTH * 0.05,
    height: SCREEN_WIDTH * 0.05,
    position: "absolute",
    top: 5,
    right: 5,
  },
  closeImage: {
    width: SCREEN_WIDTH * 0.05,
    height: SCREEN_WIDTH * 0.05,
  },

  postCreateTopView: {
    marginTop: SCREEN_HEIGHT * 0.03,
    width: SCREEN_WIDTH * 0.8,
    height: SCREEN_HEIGHT * 0.25,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "gray",
    marginLeft: SCREEN_WIDTH * 0.05,
  },
  previewImageView: {
    width: SCREEN_WIDTH * 0.7, 
    height: SCREEN_HEIGHT * 0.25, 
    marginRight: SCREEN_WIDTH * 0.01, 
  },
  previewImage: {
    width: SCREEN_WIDTH * 0.7, 
    height: SCREEN_HEIGHT * 0.25, 
  },
  
  postCreateMiddleView: {
    marginVertical: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_WIDTH * 0.13,
    alignItems: "center",
  },
  buttonContainer: {
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_WIDTH * 0.13,
    flexDirection: "row", 
    justifyContent: "center", 
  },
  cameraButtonView: {
    width: SCREEN_WIDTH * 0.3,
    height: SCREEN_WIDTH * 0.12,
    borderWidth: 1,
    borderRadius: 30,
    justifyContent: "center",
    alignItems: "center",
  },
  cameraButton: {
    width: SCREEN_WIDTH * 0.1,
    height: SCREEN_WIDTH * 0.1,
  },
  
  postCreateBottomView: {
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.35,
    alignItems: "center",
  },
  postCreateTextInputView: {
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.07,
  },
  postCreateTextInput: {
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.06,
    padding: 10,
    borderRadius: 10, 
    borderColor: 'gray',
    borderWidth: 1, 
  },
  postCreateTextAreaView: {
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.2,
  },
  postCreateTextArea: {
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.2,
    padding: 10,
    borderRadius: 10,
    borderColor: 'gray', 
    borderWidth: 1, 
  },
  
  postCreateSubmitView: {
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.05,
    alignItems: "center",
  },
  postCreateSubmitTouchView: {
    width: SCREEN_WIDTH * 0.6,
    height: SCREEN_HEIGHT * 0.05,
    backgroundColor: "rgb(13, 110, 253)",
    borderWidth: 1,
    borderRadius: 50,
    borderColor: "rgb(13, 110, 253)",
    justifyContent: "center",
    alignItems: "center",
  },
  postCreateSubmitText: {
    color: "white",
    fontSize: 20,
  },
});
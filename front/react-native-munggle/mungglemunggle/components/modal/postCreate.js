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

import { AntDesign } from '@expo/vector-icons';
import AsyncStorage from "@react-native-async-storage/async-storage";

import axios, { Axios } from "axios";
import { Header } from "react-native/Libraries/NewAppScreen";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function PostCreate (props) {
  const apiUrl = "http://i10a410.p.ssafy.io:8080";

  const [images, setImages] = useState([]);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [tags, setTags] = useState([]);
  const [tag, setTag] = useState("");

  const [isPrivate, setIsPrivate] = useState(false);

  const createPost = () => {
    const dto = {
      postTitle: title, 
      postContent: content, 
      isPrivate: isPrivate,
      hashtags: tags,
    };

    const formData = new FormData();

    formData.append("file", images);
    formData.append("dto", new Blob([JSON.stringify(dto)], {type:"application/json"}));

    console.log(images);
    console.log(dto);

    AsyncStorage.getItem("accessToken")
    .then((token) => { 
      axios.post(
        `${apiUrl}/posts`,
        formData,
        {headers: {
          "Authorization": token,
          "Content-Type": "multipart/form-data",
        }}
      ).then((res) => {
        console.log(res);
      }).catch((err) => {
        console.log(err)
      })
    })
  }

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

  const createTag = () => {
    setTags([...tags, tag]);
    setTag("");
  }

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
  
  const tagList = () => {
    return (
      <ScrollView
        horizontal={true}
        showHorizontalScrollIndicator={false}
        style={styles.tagListScroll}
      >
        {tags && tags.map((tag, index) => {
          return (
            <View key={index} style={styles.tagListView}>
              <Text style={styles.tagListText}>{tag}</Text>
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
                onChangeText={(e) => setTitle(e)}
                style={styles.postCreateTextInput}
              />
            </View>

            <View style={styles.postCreateTagInputView}>
              <View style={styles.postCreateTagListView}>
                {tagList()}
              </View>
              <View style={styles.postCreateTagCreateView}>
                <TextInput
                  placeholder="ex) # 강아지"
                  value={tag}
                  onChangeText={(e) => setTag(e)}
                  style={styles.postCreateTagInput}
                />
                <TouchableOpacity 
                  style={styles.postCreateTagSubmitView}
                  onPress={() => createTag()}
                >
                  <AntDesign name="pluscircleo" size={SCREEN_WIDTH * 0.08} color="rgb(13, 110, 253)" />
                </TouchableOpacity>
              </View>
            </View>

            <View style={styles.postCreateTextAreaView}>
              <TextInput
                placeholder="내용을 입력하세요"
                textAlignVertical="top"
                value={content}
                onChangeText={(e) => setContent(e)}
                style={styles.postCreateTextArea}
                multiline
              />
            </View>
          </View>

          <View style={styles.postCreateSubmitView}>
            <TouchableOpacity
             style={styles.postCreateSubmitTouchView}
             onPress={createPost}
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
    height: SCREEN_HEIGHT * 0.8,
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
    borderRadius: 10, 
    borderColor: 'gray',
    borderWidth: 1, 
    paddingLeft: SCREEN_WIDTH * 0.02,
    fontSize: 16,
  },
  
  postCreateTagInputView: {
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.07,
    flexDirection: "row",
    position: "relative",
  },
  postCreateTagListView: {
    width: SCREEN_WIDTH * 0.43,
    height: SCREEN_HEIGHT * 0.06,
    borderWidth: 1,
    borderColor: "gray",
    borderRadius: 10,
  },
  tagListScroll: {
    height: SCREEN_HEIGHT * 0.06,
  },
  tagListView: {
    backgroundColor: "rgb(180, 180, 180)",
    borderRadius: 20,
    justifyContent: "center",
    alignItems: "center",
    height: SCREEN_HEIGHT * 0.036,
    paddingHorizontal: SCREEN_WIDTH * 0.025,
    paddingVertical: SCREEN_HEIGHT * 0.0015,
    marginTop: SCREEN_HEIGHT * 0.012,
    marginLeft: SCREEN_WIDTH * 0.01,
  },
  tagListText: {
    color: "white",
    fontSize: 17,
  },
  postCreateTagCreateView: {
    width: SCREEN_WIDTH * 0.40,
    height: SCREEN_HEIGHT * 0.06,
    position: "absolute",
    right: 0,
    flexDirection: "row"
  },
  postCreateTagInput: {
    width: SCREEN_WIDTH * 0.3,
    height: SCREEN_HEIGHT * 0.06,
    paddingLeft: SCREEN_WIDTH * 0.02,
    fontSize: 16,
    borderRadius: 10, 
    borderColor: 'gray',
    borderWidth: 1, 
  },
  postCreateTagSubmitView: {
    width: SCREEN_WIDTH * 0.10,
    height: SCREEN_HEIGHT * 0.06,
    justifyContent: "center",
    alignItems: "center",
  },
  postCreateTextAreaView: {
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.2,
  },
  postCreateTextArea: {
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.2,
    paddingTop: SCREEN_HEIGHT * 0.01,
    paddingLeft: SCREEN_WIDTH * 0.02,
    fontSize: 16,    
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
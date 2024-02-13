import React, { useEffect, useRef, useState } from "react";
import { View, Text, Button, ScrollView, StyleSheet, Dimensions, TouchableOpacity, Alert, Platform, KeyboardAvoidingView, TextInput } from "react-native";
import { FontAwesome } from '@expo/vector-icons';

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function WalkCreate (props) {
  const { duration } = props.duration;
  const { locations } = props.locations;
  const { distance } = props.distance;
  const apiUrl = "http://i10a410.p.ssafy.io:8080";

  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const [isPrivate, setIsPrivate] = useState(false);

  const [rating, setRating] = useState(0);

  const handleRatingChange = (selectedRating) => {
    setRating(selectedRating);
  };

  const StarRating = ({ rating, onRatingChange }) => {
    const [selectedRating, setSelectedRating] = useState(rating);
  
    const handleRating = (selectedStar) => {
      setSelectedRating(selectedStar);
      onRatingChange(selectedStar);
    };
  
    return (
      <View style={styles.container}>
        {[1, 2, 3, 4, 5].map((star) => (
          <TouchableOpacity
            key={star}
            onPress={() => handleRating(star)}
          >
            <FontAwesome
              name={star <= rating ? 'star' : 'star-o'}
              size={32}
              color={star <= rating ? 'gold' : 'gray'}
            />
          </TouchableOpacity>
        ))}
      </View>
    );
  };


  
  const createWalkData = async () => {
    const payLoad = {
      walkName: title, 
      duration: duration, 
      distance: distance,
      rating: rating,
      description: description,
      location: locations.map(location => ({
        lat: location.Ma,
        lng: location.La,
      }))
    };

    const formData = new FormData();

    await axios.post(
      `${apiUrl}/walks`,
      formData,
    ).then((res) => {
      console.log(res.status);
    }).catch((err) => {
      console.log(err)
    })
  };

  const createWalk = async () => {
    await createWalkData();
  };
  
  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === "ios" ? "padding" : "height"}
      keyboardVerticalOffset={100}
      style={styles.createModalBackGround}
    >

    <View style={styles.container}>
      <Text style={styles.text}>별점을 선택하세요: {rating}</Text>
      {StarRating({ rating, onRatingChange: handleRatingChange })}
    </View>

    <View style={styles.createModalBackGround}>
      <View style={styles.createModalContainer}>
        <ScrollView style={styles.createModalScrollView}>
          <TouchableOpacity
            style={styles.closeView}
            onPress={props.closeCreateModal}
            >
          </TouchableOpacity>

          <View style={styles.postCreateTopView}>
          </View>

          <View style={styles.postCreateMiddleView}>
            <View style={styles.buttonContainer}>
              <TouchableOpacity
                style={styles.cameraButtonView}
              >
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

            <View style={styles.postCreateTextAreaView}>
              <TextInput
                placeholder="리뷰를 입력하세요"
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
             onPress={createWalk}
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

  postCreateTopView: {
    marginTop: SCREEN_HEIGHT * 0.03,
    width: SCREEN_WIDTH * 0.8,
    height: SCREEN_HEIGHT * 0.25,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "gray",
    marginLeft: SCREEN_WIDTH * 0.05,
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

  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  text: {
    fontSize: 20,
    marginBottom: 20,
  },
});
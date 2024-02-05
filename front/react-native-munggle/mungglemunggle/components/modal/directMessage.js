import React, { useEffect, useState } from "react";
import { View, Text, Image, StyleSheet,
  ScrollView, TouchableOpacity, Button,
  Dimensions,
  TextInput, 
} from "react-native";

import iconClose from "../../assets/icons/close1.png";

import axios, { Axios } from "axios";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function DirectMessage (props) {

  return (
    <View style={styles.directMessageModalBackGround}>
      <View style={styles.directMessageModalContainer}>
        <ScrollView style={styles.directMessageModalScrollView}>
          <TouchableOpacity
            style={styles.closeView}
            onPress={props.closeDirectMessageModal}
            >
            <Image 
              style={styles.closeImage}
              source={iconClose}
            />
          </TouchableOpacity>

          <TouchableOpacity style={styles.directMessageTopView}>
            <Text style={styles.directMessageTopText}>DM</Text>
          </TouchableOpacity>

          <View style={styles.directMessageMiddleView}>
            <Text>middle</Text>
          </View>

          <View style={styles.directMessageBottomView}>
            <Text>bottom</Text>
          </View>
        </ScrollView>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  directMessageModalBackGround: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT,
    justifyContent: "center",
    alignItems: "center",
  },
  directMessageModalContainer: {
    justifyContent: "center",
    alignItems: "center",
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.78,
    backgroundColor: "white",
    marginBottom: SCREEN_HEIGHT * 0.03,
    padding: SCREEN_WIDTH * 0.0,
    position: "relative",
  },
  directMessageModalScrollView: {
  },

  closeView: {
    width: SCREEN_WIDTH * 0.06,
    height: SCREEN_WIDTH * 0.06,
    position: "absolute",
    top: 0,
    right: 0,
  },
  closeImage: {
    width: SCREEN_WIDTH * 0.06,
    height: SCREEN_WIDTH * 0.06,
  },

  directMessageTopView: {
    marginTop: SCREEN_HEIGHT * 0.03,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.31,
    justifyContent: "center",
    alignItems: "center",
  },

  directMessageMiddleView: {
    marginTop: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.1,
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "row",
  },

  directMessageBottomView: {
    marginBottom: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.215,
    alignItems: "center",
  },
});
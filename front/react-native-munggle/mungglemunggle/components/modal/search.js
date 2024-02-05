import React, { useEffect, useState } from "react";
import { View, Text, Image, StyleSheet,
  ScrollView, TouchableOpacity, Button,
  Dimensions,
  TextInput, 
} from "react-native";

import iconClose from "../../assets/icons/close1.png";

import axios, { Axios } from "axios";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function Search (props) {

  return (
    <View style={styles.searchModalBackGround}>
      <View style={styles.searchModalContainer}>
        <ScrollView style={styles.searchModalScrollView}>
          <TouchableOpacity
            style={styles.closeView}
            onPress={props.closeSearchModal}
            >
            <Image 
              style={styles.closeImage}
              source={iconClose}
            />
          </TouchableOpacity>

          <TouchableOpacity style={styles.searchTopView}>
            <Text style={styles.searchTopText}>검색</Text>
          </TouchableOpacity>

          <View style={styles.searchMiddleView}>
            <Text>middle</Text>
          </View>

          <View style={styles.searchBottomView}>
            <Text>bottom</Text>
          </View>
        </ScrollView>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  searchModalBackGround: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT,
    justifyContent: "center",
    alignItems: "center",
  },
  searchModalContainer: {
    justifyContent: "center",
    alignItems: "center",
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.78,
    backgroundColor: "white",
    marginBottom: SCREEN_HEIGHT * 0.03,
    padding: SCREEN_WIDTH * 0.0,
    position: "relative",
  },
  searchModalScrollView: {
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

  searchTopView: {
    marginTop: SCREEN_HEIGHT * 0.03,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.31,
    justifyContent: "center",
    alignItems: "center",
  },

  searchMiddleView: {
    marginTop: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.1,
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "row",
  },

  searchBottomView: {
    marginBottom: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.215,
    alignItems: "center",
  },
});
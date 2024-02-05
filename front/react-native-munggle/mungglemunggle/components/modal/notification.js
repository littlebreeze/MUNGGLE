import React, { useEffect, useState } from "react";
import { View, Text, Image, StyleSheet,
  ScrollView, TouchableOpacity, Button,
  Dimensions,
  TextInput, 
} from "react-native";

import iconClose from "../../assets/icons/close1.png";

import axios, { Axios } from "axios";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function Notification (props) {

  return (
    <View style={styles.notificationModalBackGround}>
      <View style={styles.notificationModalContainer}>
        <ScrollView style={styles.notificationModalScrollView}>
          <TouchableOpacity
            style={styles.closeView}
            onPress={props.closeNotificationModal}
            >
            <Image 
              style={styles.closeImage}
              source={iconClose}
            />
          </TouchableOpacity>

          <TouchableOpacity onPress={openPicker} style={styles.notificationTopView}>
            <Text style={styles.notificationTopText}>알림</Text>
          </TouchableOpacity>

          <View style={styles.notificationMiddleView}>
            <Text>middle</Text>
          </View>

          <View style={styles.notificationBottomView}>
            <Text>bottom</Text>
          </View>
        </ScrollView>
      </View>
    </View>
  );
}

const styles = StyleSheet.notification({
  notificationModalBackGround: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT,
    justifyContent: "center",
    alignItems: "center",
  },
  notificationModalContainer: {
    justifyContent: "center",
    alignItems: "center",
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.78,
    backgroundColor: "white",
    marginBottom: SCREEN_HEIGHT * 0.03,
    padding: SCREEN_WIDTH * 0.0,
    position: "relative",
  },
  notificationModalScrollView: {
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

  notificationTopView: {
    marginTop: SCREEN_HEIGHT * 0.03,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.31,
    justifyContent: "center",
    alignItems: "center",
  },

  notificationMiddleView: {
    marginTop: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.1,
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "row",
  },

  notificationBottomView: {
    marginBottom: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.215,
    alignItems: "center",
  },
});
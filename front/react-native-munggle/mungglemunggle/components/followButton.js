import React from "react";
import { TouchableOpacity, Text, StyleSheet, Dimensions } from "react-native";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function FollowButton () {
  return (
    <TouchableOpacity style={styles.followButtonView}>
      <Text style={styles.followButtonText}>팔로우</Text>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  followButtonView: {
    width: SCREEN_HEIGHT * 0.07,
    height: SCREEN_HEIGHT * 0.04,
    backgroundColor: "rgb(253, 245, 169)",
    justifyContent: "center",
    alignItems: "center",
    borderRadius: 8,
  },
  followButtonText: {
    fontSize: 15,
    fontWeight: "500",
  },
});

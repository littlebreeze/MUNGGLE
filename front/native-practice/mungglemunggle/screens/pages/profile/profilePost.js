import React from "react";
import { View, Text, StyleSheet, Dimensions } from "react-native";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function ProfilePost (props) {
  const postList = props.postList;

  // const posts = postList.map((post, index) => {
  //   return (
  //     <View style={profilePostView}>

  //     </View>
  //   );
  // })

  return (
    <View style={styles.profilePostContainer}>
      <Text>profile Dog</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  profilePostContainer: {
    width: SCREEN_WIDTH,
    flexDirection: "row",
    flexWrap: "wrap",
  },
  profilePostView: {

  },
});
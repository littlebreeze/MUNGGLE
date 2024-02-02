import React from "react";
import { View, Text, Image, StyleSheet, Dimensions } from "react-native";
import { Shadow } from "react-native-shadow-2";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function ProfilePost (props) {
  const postList = props.postList;

  const posts = () => {
    return (
      <View style={styles.profilePostListView}>
        {postList && postList.map((post, index) => {
          return(
            <Shadow
              key={index}
              offset={[13, 13]}
              startColor="rgba(0, 0, 0, 0.2)"
            >
              <View style={styles.profilePostView}>
                <Image
                  style={styles.profilePostDetailImage}
                  source={post.imgPost}
                />
                <Text style={styles.profilePostDetailTitle}>{post.title}</Text>
              </View>
            </Shadow>
          );
        })}
      </View>
    );
  };

  return (
    <View style={styles.profilePostContainer}>
      {posts()}
    </View>
  );
};

const styles = StyleSheet.create({
  profilePostContainer: {
    width: SCREEN_WIDTH,
    alignItems: "center",
  },
  profilePostListView: {
    width: SCREEN_WIDTH,
    flexDirection: "row",
    flexWrap: "wrap",
  },
  profilePostView: {
    width: SCREEN_WIDTH * 0.284,
    height: SCREEN_HEIGHT * 0.15,
    margin: 10,
    backgroundColor: "white",
    justifyContent: "center",
    alignItems: "center",
    padding: 10,
  },
  profilePostDetailImage: {
    width: SCREEN_WIDTH * 0.24,
    height: SCREEN_HEIGHT * 0.115,
    marginBottom: 4,
  },
  profilePostDetailTitle: {
    fontSize: 10,
    fontWeight: "500",
  }
});
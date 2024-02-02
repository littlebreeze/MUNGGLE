import React from "react";
import { View, Text, Image, StyleSheet,
  ScrollView, TouchableOpacity, Button,
  Dimensions, 
} from "react-native";

import iconClose from "../../assets/icons/close1.png";
import iconBornWhite from "../../assets/icons/bornWhite.png";
import iconBornBlack from "../../assets/icons/bornBlack.png";
import iconScrap from "../../assets/icons/scrap.png";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function PostDetail (props) {
  const post = props.post;

  return (
    <View style={styles.detailModalBackGround}>
      <View style={styles.detailModalContainer}>
        <ScrollView style={styles.detailModalScrollView}>
          <TouchableOpacity
            style={styles.closeView}
            onPress={props.closeModal}
            >
            <Image 
              style={styles.closeImage}
              source={iconClose}
            />
          </TouchableOpacity>

          <View style={styles.postDetailTopView}>
            <Image 
              style={styles.postDetailImage}
              source={post.imgPost}
            />
          </View>

          <View style={styles.postDetailMiddleView}>
            <View style={styles.postDetailMiddleLeftView}>
              <Text style={styles.postDetailTitle}>{post.title}</Text>
              <Text style={styles.postDetailDate}>{post.createdAt}</Text>
            </View>

            <View style={styles.postDetailMiddleRightView}>
              <Text style={styles.postDetailLikeCount}>1234</Text>
              <Image 
                style={styles.postDetailLikeIcon}
                source={iconBornWhite}
                />
              <Image 
                style={styles.postDetailScrapIcon}
                source={iconScrap}
              />
            </View>
          </View>

          <View style={styles.postDetailContentView}>
            <View style={styles.postDetailContentInView}>
              <Text style={styles.postDetailContent}>{post.content}</Text>
            </View>
          </View>

          <View style={styles.postDetailTagListView}>
            {post.tagList && post.tagList.map((tag, index) => {
              return (
                <View style={styles.postDetailTagView} key={index}>
                  <Text style={styles.postDetailTagText}># {tag}</Text>
                </View>
              );
            })}
          </View>

          
          <View style={styles.postDetailCommentView}>
            <Text>댓글 영역</Text>
          </View>
        </ScrollView>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  detailModalBackGround: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT,
    justifyContent: "center",
    alignItems: "center",
  },
  detailModalContainer: {
    justifyContent: "center",
    alignItems: "center",
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.78,
    backgroundColor: "white",
    marginBottom: SCREEN_HEIGHT * 0.03,
    padding: SCREEN_WIDTH * 0.0,
    position: "relative",
  },
  detailModalScrollView: {
  },

  closeView: {
    width: SCREEN_WIDTH * 0.08,
    height: SCREEN_WIDTH * 0.08,
    position: "absolute",
    top: 0,
    right: SCREEN_WIDTH * 0.36,
  },
  closeImage: {
    width: SCREEN_WIDTH * 0.08,
    height: SCREEN_WIDTH * 0.08,
  },

  postDetailTopView: {
    marginTop: SCREEN_HEIGHT * 0.03,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.31,
    justifyContent: "center",
    alignItems: "center",
  },
  postDetailImage: {
    width: SCREEN_WIDTH * 0.8,
    height: SCREEN_HEIGHT * 0.31,
  },
  
  postDetailMiddleView: {
    marginTop: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.06,
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "row",
  },
  postDetailMiddleLeftView: {
    width: SCREEN_WIDTH * 0.5,
    height: SCREEN_HEIGHT * 0.06,
    justifyContent: "space-around",
  },
  postDetailTitle: {
    fontSize: 18,
    fontWeight: "600",
  },
  postDetailDate: {
    fontSize: 14,
    color: "gray",
  },
  postDetailMiddleRightView: {
    width: SCREEN_WIDTH * 0.3,
    height: SCREEN_HEIGHT * 0.06,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: 'space-around',
  },
  postDetailLikeCount: {
    fontSize: 20,
    fontWeight: "500",
    color: "rgb(146, 146, 0)",
  },
  postDetailLikeIcon: {
    width: SCREEN_WIDTH * 0.07,
    height: SCREEN_WIDTH * 0.07,
  },
  postDetailScrapIcon: {
    width: SCREEN_WIDTH * 0.07,
    height: SCREEN_WIDTH * 0.07,
  },
  
  postDetailContentView: {
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.2,
    alignItems: "center",
  },
  postDetailContentInView: {
    width: SCREEN_WIDTH * 0.8,
    height: SCREEN_HEIGHT * 0.2,
  },
  postDetailContent: {

  },

  postDetailTagListView: {

  },
  postDetailTagView: {

  },
  postDetailTagText: {

  },

  postDetailCommentView: {

  },
});
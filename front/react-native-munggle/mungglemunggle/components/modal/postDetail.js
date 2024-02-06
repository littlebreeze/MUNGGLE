import React from "react";
import { View, Text, Image, StyleSheet,
  ScrollView, TouchableOpacity, Button,
  Dimensions,
  TextInput, 
} from "react-native";

import iconClose from "../../assets/icons/close1.png";
import iconBornWhite from "../../assets/icons/bornWhite.png";
import iconBornBlack from "../../assets/icons/bornBlack.png";
import iconScrap from "../../assets/icons/scrap.png";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function PostDetail (props) {
  const post = props.post;

  const comments = [
    {
      username: "형우",
      content: "사진이 너무 귀여워요!",
    },
    {
      username: "윤지",
      content: "강아지 납치합니다",
    },
    {
      username: "태현",
      content: "멍멍! 왈왈!",
    },
    {
      username: "정식",
      content: "행복아 산책가자",
    },
    {
      username: "지원",
      content: "참 쉽조?",
    },
    {
      username: "평섭",
      content: "멍글멍글~",
    },
  ];

  const commentList = () => {
    return (
      <View style={styles.postDetailCommentList}>
        {comments && comments.map((comment, index) => {
          return (
          <View key={index} style={styles.postDetailComment}>
            <View style={styles.postDetailCommentUsernameView}>
              <Text style={styles.postDetailCommentUsername}>{comment.username}</Text>
            </View>
            <View style={styles.postDetailCommentContentView}>
              <Text style={styles.postDetailCommentContent}>{comment.content}</Text>
            </View>
          </View>
          );
        })}
      </View>
    );
  }

  return (
    <View style={styles.detailModalBackGround}>
      <View style={styles.detailModalContainer}>
        <ScrollView style={styles.detailModalScrollView}>
          <TouchableOpacity
            style={styles.closeView}
            onPress={props.closeDetailModal}
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
              <TouchableOpacity>
                <Image 
                  style={styles.postDetailLikeIcon}
                  source={iconBornWhite}
                />
              </TouchableOpacity>
              <TouchableOpacity>
                <Image 
                  style={styles.postDetailScrapIcon}
                  source={iconScrap}
                />
              </TouchableOpacity>
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
            <View style={styles.postDetailCommentTopView}>
              <Text style={styles.postDetailCommentTitle}>댓글</Text>
              <Text style={styles.postDetailCommentCount}>5 개</Text>
            </View>

            <View style={styles.postDetailCommentMiddleView}>
              {commentList()}
            </View>

            <View style={styles.postDetailCommentBottomView}>
              {/* 키보드 디테일 동작 설정 필요 */}
              <TextInput
                style={styles.postDetailCommentTextInput}
                placeholder="내용을 입력해주세요."
                placeholderTextColor="gray"
                keyboardAppearance="dark"
                keyboardType="web-search"
              />
            </View>
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
    height: SCREEN_HEIGHT * 0.8,
    backgroundColor: "white",
    marginBottom: SCREEN_HEIGHT * 0.03,
    borderRadius: 30,
    position: "relative",
  },
  detailModalScrollView: {
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
    height: SCREEN_HEIGHT * 0.1,
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "row",
  },
  postDetailMiddleLeftView: {
    width: SCREEN_WIDTH * 0.5,
    height: SCREEN_HEIGHT * 0.1,
    justifyContent: "space-around",
  },
  postDetailTitle: {
    fontSize: 23,
    fontWeight: "600",
  },
  postDetailDate: {
    fontSize: 17,
    color: "gray",
  },
  postDetailMiddleRightView: {
    width: SCREEN_WIDTH * 0.3,
    height: SCREEN_HEIGHT * 0.1,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: 'space-around',
  },
  postDetailLikeCount: {
    fontSize: 20,
    fontWeight: "500",
    color: "rgb(146, 146, 0)",
    marginBottom: SCREEN_HEIGHT * 0.02,
  },
  postDetailLikeIcon: {
    width: SCREEN_WIDTH * 0.07,
    height: SCREEN_WIDTH * 0.07,
    marginBottom: SCREEN_HEIGHT * 0.02,
  },
  postDetailScrapIcon: {
    width: SCREEN_WIDTH * 0.07,
    height: SCREEN_WIDTH * 0.07,
    marginBottom: SCREEN_HEIGHT * 0.02,
  },
  
  postDetailContentView: {
    marginBottom: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.215,
    alignItems: "center",
  },
  postDetailContentInView: {
    width: SCREEN_WIDTH * 0.8,
    height: SCREEN_HEIGHT * 0.215,
  },
  postDetailContent: {
    fontSize: 19,
  },

  postDetailTagListView: {
    flexDirection: "row",
    marginLeft: SCREEN_WIDTH * 0.05,
  },
  postDetailTagView: {
    backgroundColor: "rgb(180, 180, 180)",
    borderRadius: 100,
    justifyContent: "center",
    alignItems: "center",
    marginRight: SCREEN_WIDTH * 0.005,
    paddingHorizontal: SCREEN_WIDTH * 0.03,
    paddingVertical: SCREEN_HEIGHT * 0.003,
  },
  postDetailTagText: {
    color: "white",
    fontSize: 17,
  },

  postDetailCommentView: {
    width: SCREEN_WIDTH * 0.9,
    alignItems: "center",
    marginTop: SCREEN_HEIGHT * 0.01,
  },
  postDetailCommentTopView: {
    width: SCREEN_WIDTH * 0.8,
    height: SCREEN_HEIGHT * 0.05,
    flexDirection: "row",
    justifyContent: "space-between",
  },
  postDetailCommentTitle: {
    fontSize: 20,
    fontWeight: "600",
  },
  postDetailCommentCount: {
    fontSize: 20,
    fontWeight: "600",
  },
  postDetailCommentMiddleView: {
    width: SCREEN_WIDTH * 0.8,
  },
  postDetailCommentList: {
    width: SCREEN_WIDTH * 0.8,
    alignItems: "center",
  },
  postDetailComment: {
    width: SCREEN_WIDTH * 0.75,
    flexDirection: "row",
  },
  postDetailCommentUsernameView: {
    width: SCREEN_WIDTH * 0.20,
    height: SCREEN_HEIGHT * 0.03,
    justifyContent: "flex-end",
  },
  postDetailCommentUsername: {
    fontSize: 17,
    fontWeight: "600",
  },
  postDetailCommentContentView: {
    width: SCREEN_WIDTH * 0.55,
    height: SCREEN_HEIGHT * 0.03,
    justifyContent: "flex-end",
  },
  postDetailCommentContent: {
    fontSize: 15,
  },
  postDetailCommentBottomView: {
    width: SCREEN_WIDTH * 0.9,
    alignItems: "center",
    marginVertical: SCREEN_HEIGHT * 0.02
  },
  postDetailCommentTextInput: {
    width: SCREEN_WIDTH * 0.8,
    height: SCREEN_HEIGHT * 0.05,
    borderWidth: 1,
    borderColor: "gray",
    paddingLeft: SCREEN_WIDTH * 0.02,
    fontSize: 15,
  },
});
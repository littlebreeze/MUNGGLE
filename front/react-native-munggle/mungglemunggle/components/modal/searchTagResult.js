import React, {useEffect, useState} from "react";
import {
  View, TouchableOpacity, Image,
  ScrollView, Text, StyleSheet,
  Dimensions, Modal,
} from "react-native";

import iconClose from "../../assets/icons/close1.png";
import iconBornWhite from "../../assets/icons/bornWhite.png";

import PostDetail from "./postDetail";
import ProfileCircle from "../profileCircle";
import FollowButton from "../followButton";
import DirectMessageButton from "../directMessageButton";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

//테스트 post 데이터(추후 삭제)
const postData = [
  {
    "postId": 1,
    "postTitle": "멋진 내 강아지",
    "imageURLs": [
      "https://live.staticflickr.com/7669/17268903144_b5e9d79c4e_z.jpg",
    ],
    "userId": 1,
    "profileImage": "https://images.mypetlife.co.kr/content/uploads/2023/11/17133418/61fbb115-3845-4427-b72d-76c5e650cd3c.jpeg",
    "nickname": "멍멍123",
    "likeCnt": 123,
    "isLiked": true,
    "createdAt": "2024-01-29T15:18:38.236335"
  },
  {
    "postId": 2,
    "postTitle": "강아지와 산책",
    "imageURLs": [
        "https://cdn.imweb.me/thumbnail/20221027/f76c4c81b7bde.jpg",
    ],
    "userId": 1,
    "profileImage": "http://www.evermodel.com/uploaded/model/414/d3d415e8cad046393ac6aa22f0bfd5980_slide.jpg",
    "nickname": "멍12",
    "likeCnt": 23,
    "isLiked": false,
    "createdAt": "2024-01-25T15:18:38.236335"
  },
  {
    "postId": 3,
    "postTitle": "강아지 생일",
    "imageURLs": [
        "https://blancs.co.kr/web/product/big/201906/1e94a6b1b4be68347b0cd84a79b482ba.jpg",
    ],
    "userId": 1,
    "profileImage": "http://www.evermodel.com/uploaded/model/414/d3d415e8cad046393ac6aa22f0bfd5983_slide.jpg",
    "nickname": "멍글34",
    "likeCnt": 3,
    "isLiked": true,
    "createdAt": "2024-01-22T15:18:38.236335"
  },
  {
    "postId": 4,
    "postTitle": "멋진 강아지와 나",
    "imageURLs": [
        "https://live.staticflickr.com/7669/17268903144_b5e9d79c4e_z.jpg",
    ],
    "userId": 1,
    "profileImage": "https://images.mypetlife.co.kr/content/uploads/2023/11/17133418/61fbb115-3845-4427-b72d-76c5e650cd3c.jpeg",
    "nickname": "멍12멍",
    "likeCnt": 13,
    "isLiked": false,
    "createdAt": "2024-01-28T15:18:38.236335"
  },
];

export default function SearchTagResult (props) {
  const searchTag = props.searchTag;

  //게시물 결과에서의 이벤트
  const handleUserPress = () => {
    //유저 상세 정보 모달(이용자 결과에서도 사용)
    console.log("handleUserPress");
  };

  const handlePostPress = () => {
    //게시물 상세 정보 모달
    console.log("handlePostPress");
  };

  const handleLikePress = () => {
    //좋아요, 좋아요 취소 전송
    console.log("handleLikePress");
  };

  const postContent = () => {
    return (
      <View style={styles.searchTagResultPostBottomView}>
        {postData && postData.map((post, index) => {
          const [isDetailModalOpen, setIsDetailModalOpen] = useState(false);
          
          const openDetailModal = () => { setIsDetailModalOpen(true) };
  
          const closeDetailModal = () => { setIsDetailModalOpen(false) };
  
          return(
            <View key={index} style={styles.searchTagResultPostListView}>
              <View style={styles.searchTagResultPostListViewLeftView}>
                <ProfileCircle 
                  imageProfile={post.profileImage}
                  nameProfile={post.nickname}
                />
                <View style={styles.searchTagResultPostListProfileButtonView}>
                  <FollowButton />
                  <DirectMessageButton />
                </View>
              </View>
              
              <View style={styles.searchTagResultPostListViewRightView}>
                <TouchableOpacity 
                  style={styles.searchTagResultPostListImageView}
                  onPress={() => openDetailModal()}
                >
                  <Image 
                    style={styles.searchTagResultPostListImage}
                    src={post.imageURLs[0]} 
                  />
                </TouchableOpacity>
  
                <View style={styles.searchTagResultPostListBottomView}>
                  <View style={styles.searchTagResultPostListTextView}>
                    <Text style={styles.searchTagResultPostListTitle}>{post.postTitle}</Text>
                    <Text style={styles.searchTagResultPostListDate}>{post.createdAt}</Text>
                  </View>
                  <View style={styles.searchTagResultPostListIconView}>
                    <View style={styles.searchTagResultPostLikeCountView}>
                      <Text style={styles.searchTagResultPostLikeCountText}>12</Text>
                    </View>
                    <TouchableOpacity style={styles.searchTagResultPostLikeIcon}>
                      <Image 
                        style={styles.searchTagResultPostLikeIcon}
                        source={iconBornWhite}
                      />
                    </TouchableOpacity>
                  </View>
                </View>
              </View>
              <Modal
                animationType="fade"
                transparent={true}
                visible={isDetailModalOpen}
                onRequestClose={() => closeDetailModal()}>
                <PostDetail closeDetailModal={closeDetailModal} post={post} />
              </Modal>
            </View>
          );
        })}
      </View>
    );
   };

    return (
      <View style={styles.searchTagResultModalBackGround}>
        <View style={styles.searchTagResultModalContainer}>
          <TouchableOpacity
            style={styles.tagResultCloseView}
            onPress={props.closeTagSearchModal}>
            <Image
              style={styles.tagResultCloseImage}
              source={iconClose}
            />
          </TouchableOpacity>

          <View style={styles.searchTagResultTopView}>
            <Text style={styles.searchTagResultTopText}># {searchTag}를 포함하는 게시물</Text>
          </View>

          <ScrollView>
            <View
              style={styles.tagResultContents}
            >
              {postContent()}
            </View>
          </ScrollView>
        </View>
      </View>
    );
  };

const styles = StyleSheet.create({
  searchTagResultModalBackGround: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT,
    justifyContent: "center",
    alignItems: "center",
  },
  searchTagResultModalContainer: {
    alignItems: "center",
    width: SCREEN_WIDTH * 0.88,
    height: SCREEN_HEIGHT * 0.80,
    marginBottom: SCREEN_HEIGHT * 0.02,
    position: "relative",
    borderRadius: 30,
    borderWidth: 1,
    borderColor: "lightgrey",
    backgroundColor: "rgb(255, 255, 200)",
  },
  tagResultCloseView: {
    width: SCREEN_WIDTH * 0.06,
    height: SCREEN_WIDTH * 0.06,
    position: "absolute",
    top: 10,
    right: 10,
  },
  tagResultCloseImage: {
    width: SCREEN_WIDTH * 0.06,
    height: SCREEN_WIDTH * 0.06,
  },

  tagResultContents: {
    width: SCREEN_WIDTH * 0.9,
    alignItems: 'center',
  },

  searchTagResultTopView: {
    width: SCREEN_WIDTH * 0.88,
    height: SCREEN_HEIGHT * 0.05,
    marginVertical: SCREEN_HEIGHT * 0.04,
    borderBottomWidth: 1,
    borderBottomColor: "gray",
    justifyContent: "center",
    alignItems: "flex-start",
  },
  searchTagResultTopText: {
    marginLeft: SCREEN_WIDTH * 0.1,
    fontSize: 16,
    fontWeight: "500",
  },

// post content
  searchTagResultPostBottomView: {
    width: SCREEN_WIDTH * 0.88,
    alignItems: "center",
  },
  searchTagResultPostListView: {
    marginVertical: 10,
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.32,
    flexDirection: "row",
    alignItems: "flex-start",
    justifyContent: "space-around",
    backgroundColor: "rgb(249, 250, 208)",
    borderRadius: 20,
    paddingVertical: SCREEN_HEIGHT * 0.01,
  },
  searchTagResultPostListViewLeftView: {
    width: SCREEN_WIDTH * 0.21,
    height: SCREEN_HEIGHT * 0.2,
    backgroundColor: "rgb(249, 250, 208)",
    borderRadius: 7,
    alignItems: "center",
    justifyContent: "space-around",
  },
  searchTagResultPostListProfileButtonView: {
    flexDirection: "row",
    width: SCREEN_WIDTH * 0.225,
    justifyContent: "space-between",
  },
  searchTagResultPostListViewRightView: {
    width: SCREEN_WIDTH * 0.57,
    height: SCREEN_HEIGHT * 0.3,
    alignItems: "center",
    backgroundColor: "rgb(249, 250, 208)",
    borderRadius: 9,
    justifyContent: "space-around",
  },
  searchTagResultPostListImageView: {
    width: SCREEN_WIDTH * 0.57,
    height: SCREEN_HEIGHT * 0.23,
    backgroundColor: "rgb(249, 250, 208)",
    borderRadius: 20,
  },
  searchTagResultPostListImage: {
    width: SCREEN_WIDTH * 0.57,
    height: SCREEN_HEIGHT * 0.22,
    borderRadius: 20,
  },
  searchTagResultPostListBottomView: {
    width: SCREEN_WIDTH * 0.57,
    height: SCREEN_HEIGHT * 0.07,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "flex-start",
  },
  searchTagResultPostListTextView: {
    width: SCREEN_WIDTH * 0.46,
    height: SCREEN_HEIGHT * 0.06,
    justifyContent: "space-between",
  },
  searchTagResultPostListTitle: {
    fontSize: 19,
    fontWeight: "600",
  },
  searchTagResultPostListDate: {
    fontSize: 13,
    color: "grey",
  },

  searchTagResultPostListIconView: {
    width: SCREEN_WIDTH * 0.11,
    height: SCREEN_HEIGHT * 0.17,
    flexDirection: "row",
    justifyContent: "space-between",
  },
  searchTagResultPostLikeIcon: {
    width: SCREEN_WIDTH * 0.053,
    height: SCREEN_WIDTH * 0.053,
    
  },
  searchTagResultPostLikeCountView: {
    width: SCREEN_WIDTH * 0.053,
    height: SCREEN_WIDTH * 0.053,
    justifyContent: "flex-end",
  },
  searchTagResultPostLikeCountText: {
    fontSize: 16,
    color: "rgb(146, 146, 0)",
  },
});
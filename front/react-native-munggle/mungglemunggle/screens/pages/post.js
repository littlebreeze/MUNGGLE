import React, { useEffect, useState } from "react";
import { View, Text, Button,
  Image, Modal, Pressable,
  ScrollView, TouchableOpacity,
  StyleSheet, Dimensions
} from "react-native";
// import { Modal } from "react-native-modal";

import imgProfile1 from "../../assets/sample/profile1.jpg";
import imgProfile2 from "../../assets/sample/profile2.jpg";
import imgProfile3 from "../../assets/sample/profile3.jpg";
import imgProfile4 from "../../assets/sample/profile4.jpg";
import imgProfile5 from "../../assets/sample/profile5.jpg";
import imgProfile6 from "../../assets/sample/profile6.jpg";

import imgPost1 from "../../assets/sample/dog1.jpg";
import imgPost2 from "../../assets/sample/dog2.jpg";
import imgPost3 from "../../assets/sample/dog3.jpg";
import imgPost4 from "../../assets/sample/dog4.jpg";
import imgPost5 from "../../assets/sample/dog5.jpg";
import imgPost6 from "../../assets/sample/dog6.jpg";
import imgPost7 from "../../assets/sample/dog7.jpg";
import imgPost8 from "../../assets/sample/dog8.jpg";
import imgPost9 from "../../assets/sample/dog9.jpg";
import imgPost10 from "../../assets/sample/dog10.jpg";

import ProfileCircle from "../../components/profileCircle";
import FollowButton from "../../components/followButton";
import DirectMessageButton from "../../components/directMessageButton";

import iconScrap from "../../assets/icons/scrap.png";
import iconBornBlack from "../../assets/icons/bornBlack.png";
import iconBornWhite from "../../assets/icons/bornWhite.png";
import iconCreate from "../../assets/icons/create.png";

import PostDetail from "../../components/modal/postDetail";
import PostCreate from "../../components/modal/postCreate";

import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function PostScreen () {
  const profileList = [
    {
      imgProfile: imgProfile1,
      nameProfile: "user1",
    },
    {
      imgProfile: imgProfile2,
      nameProfile: "user2",
    },
    {
      imgProfile: imgProfile3,
      nameProfile: "user3",
    },
    {
      imgProfile: imgProfile4,
      nameProfile: "user4",
    },
    {
      imgProfile: imgProfile5,
      nameProfile: "user5",
    },
    {
      imgProfile: imgProfile6,
      nameProfile: "user6",
    },
  ]

  const postList = [
    {
      id: 1,
      user : {
        imgProfile: imgProfile1,
        name: 'user1',
        isFollow: false,
      },
      imgPost: imgPost1,
      title: "산책하는 댕댕이",
      content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
      createdAt: "2024-01-17",
      tagList: [
        "산책", "코기", "신났네",
      ],
    },
    {
      id: 2,
      user : {
        imgProfile: imgProfile2,
        name: 'user2',
        isFollow: false,
      },
      imgPost: imgPost2,
      title: "애기랑 오랜만에 공원",
      content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
      createdAt: "2024-01-17",
      tagList: [
        "산책", "아구", "힘들어?",
      ],
    },
    {
      id: 3,
      user : {
        imgProfile: imgProfile3,
        name: 'user3',
        isFollow: false,
      },
      imgPost: imgPost3,
      title: "귀여워라",
      content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
      createdAt: "2024-01-17",
      tagList: [
        "신남", "댕글댕글",
      ],
    },
    {
      id: 4,
      user : {
        imgProfile: imgProfile4,
        name: 'user4',
        isFollow: false,
      },
      imgPost: imgPost4,
      title: "산책하는 댕댕이",
      content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
      createdAt: "2024-01-17",
      tagList: [
        "산책", "댕댕이", "신났네",
      ],
    },
    {
      id: 5,
      user : {
        imgProfile: imgProfile5,
        name: 'user5',
        isFollow: false,
      },
      imgPost: imgPost5,
      title: "산책하는 댕댕이",
      content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
      createdAt: "2024-01-17",
      tagList: [
        "산책", "댕댕이", "신났네",
      ],
    },
    {
      id: 6,
      user : {
        imgProfile: imgProfile6,
        name: 'user6',
        isFollow: false,
      },
      imgPost: imgPost6,
      title: "산책하는 댕댕이",
      content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
      createdAt: "2024-01-17",
      tagList: [
        "산책", "댕댕이", "신났네",
      ],
    },
    {
      id: 7,
      user : {
        imgProfile: imgProfile1,
        name: 'user7',
        isFollow: false,
      },
      imgPost: imgPost7,
      title: "산책하는 댕댕이",
      content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
      createdAt: "2024-01-17",
      tagList: [
        "산책", "댕댕이", "신났네",
      ],
    },
    {
      id: 8,
      user : {
        imgProfile: imgProfile2,
        name: 'user8',
        isFollow: false,
      },
      imgPost: imgPost8,
      title: "산책하는 댕댕이",
      content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
      createdAt: "2024-01-17",
      tagList: [
        "산책", "댕댕이", "신났네",
      ],
    },
    {
      id: 9,
      user : {
        imgProfile: imgProfile3,
        name: 'user9',
        isFollow: false,
      },
      imgPost: imgPost9,
      title: "산책하는 댕댕이",
      content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
      createdAt: "2024-01-17",
      tagList: [
        "산책", "댕댕이", "신났네",
      ],
    },
    {
      id: 10,
      user : {
        imgProfile: imgProfile4,
        name: 'user10',
        isFollow: false,
      },
      imgPost: imgPost10,
      title: "산책하는 댕댕이",
      content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
      createdAt: "2024-01-17",
      tagList: [
        "산책", "댕댕이", "신났네",
      ],
    },
  ]

  
  const [testPostList, setTestPostList] = useState([]);
  
  const getPost = async () => {
    const apiUrl = await AsyncStorage.getItem("API_URL");

    const accessToken = await AsyncStorage.getItem("accessToken");

    axios.get(
      `${apiUrl}/posts/curating`,
      {headers: {
        "access-token": accessToken ,
      }}
    ).then((res) => {
      setTestPostList(res.data);
    }) .catch((err) => {
      console.log(err)
    })
  }

  useEffect(() => {
    getPost();
    console.log(testPostList);
  }, [])

  const profiles = () => {
    return (
      <ScrollView 
        horizontal={true}
        style={styles.profileCircleScrollView}
        contentContainerStyle={{
          alignItems: "center",
        }}
      >
        {profileList && profileList.map((profile, index) => {
          return (
            <View key={index} style={styles.profileCircleContainer}>
              <TouchableOpacity style={styles.profileCircleImageView}>
                <Image 
                  style={styles.profileCircleImage}
                  source={profile.imgProfile}
                />
              </TouchableOpacity>

              <View style={styles.profileCircleNameView}>
                <Text style={styles.profileCircleName}>{ profile.nameProfile }</Text>
              </View>
            </View>
          );
        })}
      </ScrollView>
    );
  }

  const posts = () => {
    return (
      <View style={styles.postBottomView}>
        {postList && postList.map((post, index) => {
          const [isDetailModalOpen, setIsDetailModalOpen] = useState(false);
          
          const openDetailModal = () => { setIsDetailModalOpen(true) };

          const closeDetailModal = () => { setIsDetailModalOpen(false) };

          return(
            <View key={index} style={styles.postListView}>
              <View style={styles.postListViewLeftView}>
                <ProfileCircle 
                  imageProfile={post.user.imgProfile}
                  nameProfile={post.user.name}
                />
                <View style={styles.postListProfileButtonView}>
                  <FollowButton />
                  <DirectMessageButton />
                </View>
              </View>
              
              <View style={styles.postListViewRightView}>
                <TouchableOpacity 
                  style={styles.postListImageView}
                  onPress={() => openDetailModal()}
                >
                  <Image 
                    style={styles.postListImage}
                    source={post.imgPost} 
                  />
                </TouchableOpacity>

                <View style={styles.postListBottomView}>
                  <View style={styles.postListTextView}>
                    <Text style={styles.postListTitle}>{post.title}</Text>
                    <Text style={styles.postListDate}>{post.createdAt}</Text>
                    <View style={styles.postListTagView}>
                      {post.tagList && post.tagList.map((tag, index) => {
                        return (
                          <View style={styles.postTagView} key={index}>
                            <Text style={styles.postTagText}># {tag}</Text>
                          </View>
                        );
                      })}
                    </View>
                  </View>
                  <View style={styles.postListIconView}>
                    <View style={styles.postLikeCountView}>
                      <Text style={styles.postLikeCountText}>12</Text>
                    </View>
                    <TouchableOpacity style={styles.postLikeIcon}>
                      <Image 
                        style={styles.postLikeIcon}
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
  }

  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);

  const openCreateModal = () => { setIsCreateModalOpen(true) }; 
  const closeCreateModal = () => { setIsCreateModalOpen(false) }; 

  return (
    <View style={styles.postContainer}>
      <ScrollView style={styles.postScrollView}>
        <View style={styles.postTopView}>
          <View style={styles.postTopViewToggleButtonView}>
            <TouchableOpacity style={styles.postToggleButtonLeft}>
              <Text style={styles.postToggleButtonLeftText}>추천</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.postToggleButtonRight}>
              <Text style={styles.postToggleButtonRightText}>팔로잉</Text>
            </TouchableOpacity>
          </View>

          {profiles()}        
        </View>

        {posts()}
      </ScrollView>
      <TouchableOpacity 
        onPress={() => openCreateModal()}
        style={styles.postCreateView}
      >
        <Image 
          style={styles.postCreateImage}
          source={iconCreate}
        />
      </TouchableOpacity>
      <Modal
        animationType="fade"
        transparent={true}
        visible={isCreateModalOpen}
        onRequestClose={() => closeCreateModal()}>
        <PostCreate closeCreateModal={closeCreateModal} />
      </Modal>
    </View>
  );
};

const styles = StyleSheet.create({
  postContainer: {
    width: SCREEN_WIDTH,
    backgroundColor: "rgb(249, 250, 208)",
    position: "relative",
  },
  postScrollView: {
    width: SCREEN_WIDTH,
  },
  postTopView: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.2,
  },
  
  // profile Toggle Button
  postTopViewToggleButtonView: {
    marginTop: SCREEN_HEIGHT * 0.005,
    height: SCREEN_HEIGHT * 0.05,
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
  },
  postToggleButtonLeft: {
    width: SCREEN_WIDTH * 0.17,
    height: SCREEN_HEIGHT * 0.04,
    backgroundColor: "rgb(255, 214, 139)",
    justifyContent: "center",
    alignItems: "center",
    borderWidth: 1,
    borderColor: "gainsboro",
    borderTopLeftRadius: 8,
    borderBottomLeftRadius: 8,
  },
  postToggleButtonLeftText: {
    fontSize: 17,
    fontWeight: "600",
  },
  postToggleButtonRight: {
    width: SCREEN_WIDTH * 0.17,
    height: SCREEN_HEIGHT * 0.04,
    backgroundColor: "rgb(253, 245, 169)",
    justifyContent: "center",
    alignItems: "center",
    borderWidth: 1,
    borderColor: "gainsboro",
    borderTopRightRadius: 8,
    borderBottomRightRadius: 8,
  },
  postToggleButtonRightText: {
    fontSize: 17,
    fontWeight: "600",
  },

  // Profile Circle Box
  profileCircleScrollView: {
    height: SCREEN_HEIGHT * 0.15,
  },

  profileCircleContainer: {
    width: SCREEN_WIDTH * 0.2,
    height: SCREEN_HEIGHT * 0.14,
    justifyContent: "center",
    alignItems: "center",
    marginHorizontal: SCREEN_WIDTH * 0.023,
  },
  profileCircleImageView: {
    borderRadius: 100,
  },
  profileCircleImage: {
    borderRadius: 100,
    // borderColor: "rgb(253, 255, 117)",
    borderColor: "lightgrey",
    borderWidth: 1,
    width: SCREEN_WIDTH * 0.16,
    height: SCREEN_HEIGHT * 0.08,
  },
  profileCircleNameView: {
    width: SCREEN_WIDTH * 0.18,
    justifyContent: "center",
    alignItems: "center",
  },
  profileCircleName: {
    fontSize: 18,
    fontWeight: "500",
  },

  // post List View
  postBottomView: {
    width: SCREEN_WIDTH,
    alignItems: "center",
    paddingTop: 10,
  },
  postListView: {
    marginVertical: 10,
    width: SCREEN_WIDTH * 0.95,
    height: SCREEN_HEIGHT * 0.42,
    flexDirection: "row",
    paddingHorizontal: 5,
    paddingVertical: 15,
    alignItems: "flex-start",
    justifyContent: "space-around",
  },
  postListViewLeftView: {
    width: SCREEN_WIDTH * 0.25,
    height: SCREEN_HEIGHT * 0.2,
    backgroundColor: "white",
    borderRadius: 7,
    alignItems: "center",
    justifyContent: "space-around",
  },
  postListProfileButtonView: {
    flexDirection: "row",
    width: SCREEN_WIDTH * 0.235,
    justifyContent: "space-between",
  },
  postListViewRightView: {
    width: SCREEN_WIDTH * 0.64,
    height: SCREEN_HEIGHT * 0.38,
    alignItems: "center",
    backgroundColor: "white",
    borderRadius: 9,
    justifyContent: "space-around",
  },
  postListImageView: {
    width: SCREEN_WIDTH * 0.60,
    height: SCREEN_HEIGHT * 0.25,
    
  },
  postListImage: {
    width: SCREEN_WIDTH * 0.60,
    height: SCREEN_HEIGHT * 0.25,
    
  },
  postListBottomView: {
    width: SCREEN_WIDTH * 0.60,
    height: SCREEN_HEIGHT * 0.1,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "flex-start",
  },
  postListTextView: {
    width: SCREEN_WIDTH * 0.48,
    height: SCREEN_HEIGHT * 0.09,
    justifyContent: "space-between",
  },
  postListTitle: {
    fontSize: 19,
    fontWeight: "600",
  },
  postListDate: {
    fontSize: 13,
    color: "grey",
  },
  postListTagView: {
    flexDirection: "row",
  },
  postTagView: {
    backgroundColor: "rgb(180, 180, 180)",
    borderRadius: 10,
    justifyContent: "center",
    alignItems: "center",
    paddingHorizontal: SCREEN_WIDTH * 0.025,
    paddingVertical: SCREEN_HEIGHT * 0.0015,
  },
  postTagText: {
    color: "white",
    fontSize: 14,
  },
  
  postListIconView: {
    width: SCREEN_WIDTH * 0.12,
    height: SCREEN_HEIGHT * 0.17,
    flexDirection: "row",
    justifyContent: "space-between",
  },
  postLikeIcon: {
    width: SCREEN_WIDTH * 0.055,
    height: SCREEN_WIDTH * 0.055,
    
  },
  postLikeCountView: {
    width: SCREEN_WIDTH * 0.055,
    height: SCREEN_WIDTH * 0.055,
    justifyContent: "flex-end",
  },
  postLikeCountText: {
    fontSize: 16,
    color: "rgb(146, 146, 0)",
  },
  postCreateView: {
    width: SCREEN_WIDTH * 0.19,
    height: SCREEN_WIDTH * 0.19,
    position: "absolute",
    bottom: SCREEN_WIDTH * 0.05,
    right: SCREEN_WIDTH * 0.05,
  },
  postCreateImage: {
    width: SCREEN_WIDTH * 0.19,
    height: SCREEN_WIDTH * 0.19,
  },
})
import React, { useEffect, useId, useState } from "react";
import { View, Text, Button, Image, 
  ScrollView, StyleSheet, Dimensions, 
  TouchableOpacity, Modal, TextInput
} from "react-native";

import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';

import ProfileDog from "./profile/profileDog";
import ProfilePost from "./profile/profilePost";
import ProfileScrap from "./profile/profileScrap";

import iconDog from "../../assets/icons/profileDog.png";
import iconPost from "../../assets/icons/profilePost.png";
import iconScrap from "../../assets/icons/profileScrap.png";
import iconDirectMessage from "../../assets/icons/directMessage.png";

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

import iconEdit from "../../assets/icons/infoEdit.png";

import FollowButton from "../../components/followButton";
import DirectMessageButton from "../../components/directMessageButton";

import imgDefaultProfile from "../../assets/icons/defaultProfile.png";

import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { AntDesign } from '@expo/vector-icons';
import * as ImagePicker from 'expo-image-picker';

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

const Tab = createMaterialTopTabNavigator();

export default function ProfileScreen () {
  const apiUrl = "http://i10a410.p.ssafy.io:8080";

  const [profile, setProfile] = useState({});

  const [authToken, setAuthToken] = useState("");

  const [dogList, setDogList] = useState([]);
  const [testPostList, setTestPostList] = useState([]);
  const [scrapList, setScrapList] = useState([]);
  
  const [isEdit, setIsEdit] = useState(false);

  const [backgroundImgUrl, setBackgroundImgUrl] = useState("");
  const [profileImgUrl, setProfileImgUrl] = useState("");
  const [nickname, setNickname] = useState("");
  const [description, setDescription] = useState("");

  // useEffect(() => { 
  //   // get token
  //   AsyncStorage.getItem("accessToken")
  //   .then((token) => {
  //     setAuthToken(token);
  //   })
  //   // get user Id
  //   .then(
  //     axios.get(
  //       `${apiUrl}/users/mypage`,
  //         {headers: {
  //           "Authorization": authToken ,
  //         }}
  //       ).then(async (res) =>{
  //         await setProfile(res.data);
  //       }).then(() => {
  //         console.log(profile.id);
  //       })
  //         // get Dog List
  //         .then(
  //           axios.get(
  //           `${apiUrl}/userpages/${profile.id}/dog`,
  //             {headers: {
  //               "Authorization": authToken ,
  //             }}
  //           ).then((dog) => {
  //             setDogList(dog.data); 
  //             console.log("dog====================");
  //             console.log(dog.data); 
  //             console.log("dog====================");
  //           })
  //           .catch((err) => console.log(err))
  //         )
  //         // get Post List
  //         .then(
  //           axios.get(
  //           `${apiUrl}/userpages/${profile.id}/post`,
  //             {headers: {
  //               "Authorization": authToken ,
  //             }}
  //           ).then((post) => {
  //             setTestPostList(post.data);
  //             console.log("post====================");
  //             console.log(post.data);
  //             console.log("post====================");
  //           })
  //           .catch((err) => console.log(err))
  //         )
  //         // // get Scrap List
  //         // .then(
  //         //   axios.get(
  //         //     `${apiUrl}/userpages/${profile.id}/scrap`,
  //         //       {headers: {
  //         //         "Authorization": authToken ,
  //         //       }}
  //         //     ).then((scrap) => {
  //         //       console.log("scrap===================="); 
  //         //       setScrapList(scrap.data);
  //         //       console.log(scrap.data);
  //         //       console.log("scrap====================");
  //         //     })
  //         //     .catch((err) => console.log(err))
  //         // ) 
  //         .catch((err) => console.log(err))
  //       .catch((err) => {
  //         console.log(err);
  //       })
  //   ) 
  // }, []);

  const changeBackgroundImg = async () => {
    const response = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.All,
      allowsEditing: true,
      aspect: [4, 3], //비율 변경 가능
      quality: 1,
    });

    if (response.canceled) {
      return null;
    }
    console.log(response.assets[0].uri);

    setBackgroundImgUrl(response.assets[0]);
  };

  const changeProfileImg = async () => {
    const response = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.All,
      allowsEditing: true,
      aspect: [4, 3], //비율 변경 가능
      quality: 1,
    });

    if (response.canceled) {
      return null;
    }
    console.log(response.assets[0].uri);

    setProfileImgUrl(response.assets[0]);
  };
  
  const editBackgroundImage = async () => {
    const localUri = backgroundImgUrl.uri;
    const fileName = localUri.split('/').pop();
    const match = /\.(\w+)$/.exec(fileName ?? '');
    const type = match ? `image/${match[1]}` : `image`;
    const formData = new FormData();
    formData.append('backgroundImage', { uri: localUri, name: fileName, type});

    console.log(formData);

    await axios.put(
      `${apiUrl}/users/background`,
      formData,
      {headers: {
        "Authorization": authToken ,
        "Content-Type": "multipart/form-data",
      }}
    ).then((res) => {
      console.log("배경 이미지 변경 ========================");
      console.log(res.status);
    }).catch((err) => {
      console.log("배경 이미지 변경 ========================");
      console.log(err);
    })
  };

  const editProfileImage = async () => {
    const localUri = profileImgUrl.uri;
    const fileName = localUri.split('/').pop();
    const match = /\.(\w+)$/.exec(fileName ?? '');
    const type = match ? `image/${match[1]}` : `image`;

    const formData = new FormData();
    formData.append('profileImage', { uri: localUri, name: fileName, type});

    console.log(formData);

    await axios.put(
      `${apiUrl}/users/profile-image`,
      formData,
      {headers: {
        "Authorization": authToken ,
        "Content-Type": "multipart/form-data",
      }}
    ).then((res) => {
      console.log("프로필 이미지 변경==================");
      console.log(res.status);
    }).catch((err) => {
      console.log("프로필 이미지 변경==================");
      console.log(err);
    })
  };

  const editProfileData = async () => {
    const payLoad = {
      newNickname: nickname,
      description: description,
    };

    await axios.put(
      `${apiUrl}/users`,
      payLoad,
      {headers: {
        "Authorization": authToken ,
        "Content-Type": "application/json",
      }}
    ).then((res) => {
      console.log("프로필 데이터 변경==================");
      console.log(res.status);
    }).catch((err) => {
      console.log("프로필 데이터 변경==================");
      console.log(err);
    })
  };


  const editProfile = async () => {
    await editBackgroundImage();
    await editProfileImage();
    await editProfileData();
    // setIsEdit(false);
  };

  const myProfileImg = () => {
    if (!profileImgUrl && !profile.profileImgUrl) {
      return (
        <View style={styles.profileDefaultTopView}>
          <View style={styles.profileDefaultImageView}>
            <Image 
              style={{
                ...styles.profileDefaultImage, 
                opacity: 0.8,
              }}
              source={imgDefaultProfile}
            /> 
            <TouchableOpacity 
              style={styles.profileEditDefaultProfileImgButtonView}
              onPress={changeProfileImg}
            >
              <AntDesign 
                name="pluscircleo" 
                size={60} 
                color="black" 
                style={styles.profileEditDefaultProfileImgButtonIcon}
              />
            </TouchableOpacity>
            <TouchableOpacity 
              style={styles.profileEditDefaultButtonView}
              onPress={editProfile}
            >
              <AntDesign 
                name="checksquareo" 
                size={33} 
                color="rgb(13, 110, 253)" 
                style={styles.profileEditButtonIcon}
              />
            </TouchableOpacity>
          </View>  
        </View>
      );
    } else {
      return (
        <View style={styles.profileTopViewMiddleView}>
          <View style={styles.profileImageView}>
            <Image 
              style={{
                ...styles.profileImage, 
                opacity: 0.8,
              }}
              source={
                profileImgUrl ? profileImgUrl : profile.profileImgUrl}
            />
            <TouchableOpacity 
              style={styles.profileEditProfileImgButtonView}
              onPress={changeProfileImg}
            >
              <AntDesign 
                name="pluscircleo" 
                size={60} 
                color="black" 
                style={styles.profileEditProfileImgButtonIcon}
              />
            </TouchableOpacity>
            <TouchableOpacity 
              style={styles.profileEditButtonView}
              onPress={editProfile}
            >
              <AntDesign 
                name="checksquareo" 
                size={33} 
                color="rgb(13, 110, 253)" 
                style={styles.profileEditButtonIcon}
              />
            </TouchableOpacity>
          </View>
        </View>
      );
    };
  };

  const myProfile = () => {
    if (isEdit) {
      return (
        <View style={styles.profileTopView}>
          <View style={styles.profileTopViewTopView}>
            <Image 
              style={{
                ...styles.profileTopViewTopView, 
                opacity:0.6, 
                backgroundColor: "rgb(249, 250, 208)"
              }}
              source={
                backgroundImgUrl 
                ? backgroundImgUrl 
                : profile.backgroundImgUrl
              }
              />
            <TouchableOpacity 
              style={styles.profileEditBackgroundImgButtonView}
              onPress={changeBackgroundImg}
            >
              <AntDesign 
                name="pluscircleo" 
                size={80}
                color="black" 
                style={styles.profileEditBackgroundImgButtonIcon}
                />
            </TouchableOpacity>
          </View>
          {myProfileImg()}
          <View style={styles.profileTopViewBottomView}>
            <View style={styles.profileTopViewBottomViewTopView}>
              <View style={styles.profileEditTopView}></View>
              <TextInput 
                placeholder={userProfile.name}
                style={styles.profileEditUsername}
                value={nickname}
                onChangeText={(e) => setNickname(e)}
              />
              <View style={styles.profileTopViewBottomViewButtonView}>
                <FollowButton />
                <DirectMessageButton />
              </View>
            </View>
            <View style={styles.profileTopViewBottomViewBottomView}>
              <Text style={styles.textFollow}>팔로워 {userProfile.follower}</Text>
              <Text style={{...styles.textFollow, marginLeft:SCREEN_HEIGHT * 0.02}}>팔로잉 {userProfile.following}</Text>
            </View>
            <TextInput 
              placeholder={userProfile.description}
              value={description}
              style={{...styles.textDescription, height: SCREEN_HEIGHT * 0.05, paddingBottom: SCREEN_HEIGHT * 0.005,}}
              multiline
              onChangeText={(e) => setDescription(e)}
            />
          </View>
        </View>
      );
    } else {
      return (
        <View style={styles.profileTopView}>
          <View style={styles.profileTopViewTopView}>
            <Image 
              style={styles.profileTopViewTopView}
              source={imgPost1}
            />
          </View>
          <View style={styles.profileTopViewMiddleView}>
            <View style={styles.profileImageView}>
              <Image 
                style={styles.profileImage}
                source={imgProfile1}
              />
              <TouchableOpacity 
                style={styles.profileEditButtonView}
                onPress={() => setIsEdit(true)}
              >
                <Image 
                  style={styles.profileEditButtonIcon}
                  source={iconEdit}
                />
              </TouchableOpacity>
            </View>
          </View>
          <View style={styles.profileTopViewBottomView}>
            <View style={styles.profileTopViewBottomViewTopView}>
              <Text style={styles.profileTopViewBottomViewName}>{ userProfile.name }</Text>
              <View style={styles.profileTopViewBottomViewButtonView}>
                <FollowButton />
                <DirectMessageButton />
              </View>
            </View>
            <View style={styles.profileTopViewBottomViewBottomView}>
              <Text style={styles.textFollow}>팔로워 {userProfile.follower}</Text>
              <Text style={{...styles.textFollow, marginLeft:SCREEN_HEIGHT * 0.02}}>팔로잉 {userProfile.following}</Text>
            </View>
            <Text style={styles.textDescription}>{userProfile.description}</Text>
          </View>
        </View>
      );
    };
  };
  
  console.log(dogList);
  console.log(testPostList);
  console.log(scrapList);

  const userProfile = {
    backGroundImg: imgPost1,
    profileImg: imgProfile1,
    name: "행복이아빠",
    isFollow: false,
    description: "소소하게 자주 즐겁게 행복하기. 행복이 행복이 행복이 행복이 행복이 행복이 행복이 행복이",
    follower: 2,
    following: 3,
    dogs: [
      {
        img: imgPost1,
        name: "김행복",
        kind: "웰시코기",
        weight: 2.8,
        birthDate: "22.02.08",
        gender: "남자",
      },
      {
        img: imgPost2,
        name: "댕댕이",
        kind: "리트리버",
        weight: 12.3,
        birthDate: "22.02.08",
        gender: "여자",
      },
    ]
  } 

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
  
  return (
    <ScrollView style={styles.profileContainer}>
      {myProfile()}
      <View style={styles.profileBottomView}>
        <Tab.Navigator
          initialRouteName="Dog"
          screenOptions={{
            tabBarShowLabel: false,
            tabBarStyle: {
              height: SCREEN_HEIGHT * 0.06,
            },
            tabBarItemStyle: {
              height: SCREEN_HEIGHT * 0.06,
            },
          }}
        >
          <Tab.Screen 
            name="Dog" 
            children={() => <ProfileDog  dogList={userProfile.dogs} />}
            options={{
              tabBarIcon: () => (
                <Image
                  style={styles.profileTabBarIcon}
                  source={iconDog}
                />
              ),
            }}
          />
          <Tab.Screen 
            name="Post" 
            children={() => <ProfilePost  postList={postList} />} 
            options={{
              tabBarIcon: () => (
                <Image
                  style={styles.profileTabBarIcon}
                  source={iconPost}
                />
              ),
            }}
          />
          <Tab.Screen 
            name="Scrap" 
            children={() => <ProfileScrap  postList={postList} />} 
            options={{
              tabBarIcon: () => (
                <Image
                  style={styles.profileTabBarIcon}
                  source={iconScrap}
                />
              ),
            }}
          />
        </Tab.Navigator>
      </View>

    </ScrollView>
  );
};

const styles = StyleSheet.create({
  profileContainer: {
    width: SCREEN_WIDTH,
    flex: 1,
    height: SCREEN_HEIGHT * 0.82,
  },
  profileTopView: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.35,
    position: "relative",
  },
  profileBottomView: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 2,
  },
  profileTabBarIcon: {
    width: SCREEN_HEIGHT * 0.04,
    height: SCREEN_HEIGHT * 0.04,
    marginTop: -5,
    marginLeft: -5,
  },
  profileTopViewTopView: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.2,
    position: "relative",
  },
  profileTopViewTopImage: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.2,
  },
  profileTopViewMiddleView: {
    position: "absolute",
    borderRadius: 100,
    width: SCREEN_WIDTH * 0.26,
    height: SCREEN_WIDTH * 0.26,
    top: SCREEN_HEIGHT * 0.127,
    left: SCREEN_WIDTH * 0.07,
  },
  profileImageView: {
    borderRadius: 100,
    width: SCREEN_WIDTH * 0.26,
    height: SCREEN_WIDTH * 0.26,
    position: "relative",
  },
  profileDefaultTopView: {
    position: "absolute",
    borderRadius: 100,
    width: SCREEN_WIDTH * 0.26,
    height: SCREEN_WIDTH * 0.26,
    top: SCREEN_HEIGHT * 0.14,
    left: SCREEN_WIDTH * 0.09,
  },
  profileEditDefaultProfileImgButtonView: {
    position: "absolute",
    left: SCREEN_WIDTH * 0.043,
    top: SCREEN_WIDTH * 0.043,
  },
  profileDefaultImageView: {
    borderRadius: 100,
    width: SCREEN_WIDTH * 0.26,
    height: SCREEN_WIDTH * 0.26,
    position: "relative",
  },
  profileDefaultImage: {
    borderRadius: 100,
    width: SCREEN_WIDTH * 0.23,
    height: SCREEN_WIDTH * 0.23,
    borderWidth: 1,
    borderColor: "gray",
  },
  profileImage: {
    borderRadius: 100,
    width: SCREEN_WIDTH * 0.26,
    height: SCREEN_WIDTH * 0.26,
  },
  
  profileEditDefaultButtonView: {
    position: "absolute",
    bottom: 0,
    right: 0,
    width: SCREEN_WIDTH * 0.09,
    height: SCREEN_WIDTH * 0.09,
  },
  profileEditButtonView: {
    position: "absolute",
    bottom: -5,
    right: -10,
    width: SCREEN_WIDTH * 0.09,
    height: SCREEN_WIDTH * 0.09,
  },
  profileEditButtonIcon: {
    width: SCREEN_WIDTH * 0.09,
    height: SCREEN_WIDTH * 0.09,
  },

  profileTopViewBottomView: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.15,
    paddingHorizontal: SCREEN_HEIGHT * 0.02,
  },
  profileTopViewBottomViewTopView: {
    marginVertical: SCREEN_HEIGHT * 0.01,
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
    position: "relative",
  },
  profileTopViewBottomViewName: {
    fontSize: 20,
    fontWeight: "600",
  },
  profileTopViewBottomViewButtonView: {
    flexDirection: "row",
    width: SCREEN_WIDTH * 0.235,
    justifyContent: "space-between",
    position: "absolute",
    right: SCREEN_WIDTH * 0.01,
    top: SCREEN_HEIGHT * 0.014,
  },
  profileTopViewBottomViewBottomView: {
    width: SCREEN_WIDTH,
    flexDirection: "row",
    marginTop: SCREEN_HEIGHT * 0.01,
    marginBottom: SCREEN_HEIGHT * 0.01,
  },
  textFollow: {
    fontSize: 16,
    fontWeight: "600",
  },
  textDescription: {
    fontSize: 14,
  },

  profileEditBackgroundImgButtonView: {
    position: "absolute",
    left: SCREEN_WIDTH * 0.41,
    top: SCREEN_HEIGHT * 0.06,
  },
  profileEditBackgroundImgButtonIcon: {
    opacity: 0.6,
  },
  profileEditProfileImgButtonView: {
    position: "absolute",
    left: SCREEN_WIDTH * 0.058,
    top: SCREEN_WIDTH * 0.058,
  },
  profileEditDefaultProfileImgButtonIcon: {
    opacity: 0.8,
  },
  profileEditProfileImgButtonIcon: {
    opacity: 0.5,
  },
  profileEditTopView: {
    width: SCREEN_WIDTH * 0.3,
    height: SCREEN_HEIGHT * 0.04,
  },
  profileEditUsername: {
    position: "absolute",
    backgroundColor: "white",
    fontSize: 20,
    fontWeight: "600",
    width: SCREEN_WIDTH * 0.3,
    height: SCREEN_HEIGHT * 0.04,
    textAlign: "center",
    borderColor: "gray",
    borderWidth: 1,
    borderRadius: 5,
  },
  // textDescription: {
  // },
  // textDescription: {
  // },
  // textDescription: {
  // },
  // textDescription: {
  // },
  // textDescription: {
  // },
  // textDescription: {
  // },
  // textDescription: {
  // },
  // textDescription: {
  // },
});
import React from "react";
import "./ProfilePage.css";
import { Route, Routes, Link } from "react-router-dom";

import ProfileDog from "../../components/Profile/ProfileDog";
import ProfilePost from "../../components/Profile/ProfilePost";
import ProfileStory from "../../components/Profile/ProfileStory";
import ProfileScrap from "../../components/Profile/ProfileScrap";

import FollowButton from "../../components/button/FollowButton";
import ProfileCircle from "../../components/ProfileCircle";

import iconDm from "../../assets/icons/dm.png";
import iconDog from "../../assets/icons/profileDog.png";
import iconPost from "../../assets/icons/profilePost.png";
import iconStory from "../../assets/icons/profileStory.png";
import iconScrap from "../../assets/icons/profileScrap.png";
import iconLine1 from "../../assets/icons/line1.png";

import imgProfile from "../../assets/example/profile.png";

import imgProfile1 from "../../assets/example/profile1.jpg";
import imgProfile2 from "../../assets/example/profile2.jpg";
import imgProfile3 from "../../assets/example/profile3.jpg";
import imgProfile4 from "../../assets/example/profile4.jpg";
import imgProfile5 from "../../assets/example/profile5.jpg";
import imgProfile6 from "../../assets/example/profile6.jpg";
import imgProfile7 from "../../assets/example/profile.png";

import imgPost1 from "../../assets/example/dog1.jpg";
import imgPost2 from "../../assets/example/dog2.jpg";
import imgPost3 from "../../assets/example/dog3.jpg";
import imgPost4 from "../../assets/example/dog4.jpg";
import imgPost5 from "../../assets/example/dog5.jpg";
import imgPost6 from "../../assets/example/dog6.jpg";
import imgPost7 from "../../assets/example/dog7.jpg";
import imgPost8 from "../../assets/example/dog8.jpg";
import imgPost9 from "../../assets/example/dog9.jpg";
import imgPost10 from "../../assets/example/dog10.jpg";

import imgDog from "../../assets/dogLogo.png";
import imgSearch from "../../assets/icons/search.png";
import imgNotification from "../../assets/icons/notification.png";
import imgDm from "../../assets/icons/dm.png";

export default function Profile(props) {
  const userProfile = {
    backGroundImg: imgPost1,
    profileImg: imgProfile1,
    name: "megar0829",
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
        name: 'megar0829',
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
        name: 'megar0829',
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
        name: 'megar0829',
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
        name: 'megar0829',
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
        name: 'megar0829',
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
        name: 'megar0829',
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
        name: 'megar0829',
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
        name: 'megar0829',
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
        name: 'megar0829',
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
        name: 'megar0829',
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

  const storyList = [
    {
      img: imgPost1,
      startDate: "24.01.01",
      endDate: "24.01.18",
    },
    {
      img: imgPost2,
      startDate: "24.01.02",
      endDate: "24.01.18",
    },
    {
      img: imgPost3,
      startDate: "24.01.03",
      endDate: "24.01.18",
    },
    {
      img: imgPost4,
      startDate: "24.01.04",
      endDate: "24.01.18",
    },
    {
      img: imgPost5,
      startDate: "24.01.05",
      endDate: "24.01.18",
    },
    {
      img: imgPost6,
      startDate: "24.01.06",
      endDate: "24.01.18",
    },
    {
      img: imgPost7,
      startDate: "24.01.07",
      endDate: "24.01.18",
    },
    {
      img: imgPost8,
      startDate: "24.01.08",
      endDate: "24.01.18",
    },
    {
      img: imgPost9,
      startDate: "24.01.09",
      endDate: "24.01.18",
    },
  ]

  return (
    <div className="profile-container-div">
      <div className="profile-top-div">
        <div className="profile-top-div-top-div">
           <img className="profile-top-div-top-img" src={userProfile.backGroundImg} />
        </div>

        <div className="profile-top-div-bottom-div">
          <div className="profile-top-div-bottom-div-top-div">
            {/* <img className="mx-2" src={iconDm} width={30} height={30} /> */}
            {/* <button className="me-3 btn btn-secondary">팔로잉</button> */}
            {/* <span className="ms-3 profile-top-div-bottom-div-bottom-span fw-bold">팔로워 {userProfile.follower}</span>
            <span className="mx-3 profile-top-div-bottom-div-bottom-span fw-bold">팔로잉 {userProfile.following}</span> */}
            <span className="profile-top-div-bottom-div-top-div-span fw-bold">{userProfile.name}</span>
            <button className="profile-top-div-bottom-div-top-div-button">팔로우</button>
            <div className="profile-top-div-bottom-div-top-div-container">
              <img className="profile-top-div-bottom-div-top-div-img" src={iconDm} width={21} height={21} />
            </div>
          </div>

          <div className="profile-top-div-bottom-div-middle-div">
          <span className="profile-top-div-bottom-div-middle-div-span fw-bold">팔로워 {userProfile.follower}</span>
            <span className="mx-3 profile-top-div-bottom-div-middle-div-span fw-bold">팔로잉 {userProfile.following}</span>
            <p className="profile-top-div-bottom-div-middle-span">{userProfile.description}</p>
          </div>

          {/* <div className="profile-top-div-bottom-div-bottom-div">
            <span className="ms-3 profile-top-div-bottom-div-bottom-span">팔로워 {userProfile.follower}</span>
            <span className="mx-3 profile-top-div-bottom-div-bottom-span">팔로잉 {userProfile.following}</span>
          </div> */}
        </div>

        <div className="profile-top-div-middle-div">
          <ProfileCircle
            img={userProfile.profileImg}
            // name={userProfile.name}
            width={120}
            height={120}
          />
        </div>
      </div>

      <div className="profile-nav-div">
        <Link to="/profile">
          <div className="profile-nav-div-img-div">
            <img className="profile-nav-div-img" src={iconDog} />
          </div>
        </Link>

        <div className="profile-nav-div-line-div">
          <img className="profile-nav-div-line" src={iconLine1} />
        </div>

        <Link to="/profile/post">
          <div className="profile-nav-div-img-div">
            <img className="profile-nav-div-img" src={iconPost} />
          </div>
        </Link>

        <div className="profile-nav-div-line-div">
          <img className="profile-nav-div-line" src={iconLine1} />
        </div>

        <Link to="/profile/scrap">
          <div className="profile-nav-div-img-div">
            <img className="profile-nav-div-img" src={iconScrap} />
          </div>
        </Link>
      </div>

      <div className="profile-bottom-div">
        <Routes>
          <Route path="/" element={<ProfileDog dogs={userProfile.dogs} />} />
          <Route path="/post" element={<ProfilePost postList={postList} />} />
          <Route path="/story" element={<ProfileStory storyList={storyList} />} />
          <Route path="/scrap" element={<ProfileScrap scrapList={postList} />} />
        </Routes>
      </div>
    </div>
  );
}
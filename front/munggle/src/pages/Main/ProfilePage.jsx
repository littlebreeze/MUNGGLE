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

import imgBackGround from "../../assets/example/dog1.jpg";
import imgProfile from "../../assets/example/profile.png";

import imgDog1 from "../../assets/example/dog2.jpg";
import imgDog2 from "../../assets/example/dog3.jpg";
import imgDog3 from "../../assets/example/dog4.jpg";
import imgDog4 from "../../assets/example/dog5.jpg";
import imgDog5 from "../../assets/example/dog6.jpg";
import imgDog6 from "../../assets/example/dog7.jpg";
import imgDog7 from "../../assets/example/dog8.jpg";
import imgDog8 from "../../assets/example/dog9.jpg";
import imgDog9 from "../../assets/example/dog10.jpg";

export default function Profile() {
  const userProfile = {
    backGroundImg: imgBackGround,
    profileImg: imgProfile,
    name: "megar0829",
    isFollow: false,
    description: "소소하게 자주 즐겁게 행복하기. 행복이 행복이 행복이 행복이 행복이 행복이 행복이 행복이",
    follower: 2,
    following: 3,
    dogs: [
      {
        img: imgBackGround,
        name: "김행복",
        kind: "웰시코기",
        weight: 2.8,
        birthDate: "22.02.08",
        gender: "남자",
      },
      {
        img: imgDog2,
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
      img: imgDog1,
      title: "제목 1",
    },
    {
      img: imgDog2,
      title: "제목 2",
    },
    {
      img: imgDog3,
      title: "제목 3",
    },
    {
      img: imgDog4,
      title: "제목 4",
    },
    {
      img: imgDog5,
      title: "제목 5",
    },
    {
      img: imgDog6,
      title: "제목 6",
    },
    {
      img: imgDog7,
      title: "제목 7",
    },
    {
      img: imgDog8,
      title: "제목 8",
    },
    {
      img: imgDog9,
      title: "제목 9",
    },
  ]

  const storyList = [
    {
      img: imgDog1,
      startDate: "24.01.01",
      endDate: "24.01.18",
    },
    {
      img: imgDog2,
      startDate: "24.01.02",
      endDate: "24.01.18",
    },
    {
      img: imgDog3,
      startDate: "24.01.03",
      endDate: "24.01.18",
    },
    {
      img: imgDog4,
      startDate: "24.01.04",
      endDate: "24.01.18",
    },
    {
      img: imgDog5,
      startDate: "24.01.05",
      endDate: "24.01.18",
    },
    {
      img: imgDog6,
      startDate: "24.01.06",
      endDate: "24.01.18",
    },
    {
      img: imgDog7,
      startDate: "24.01.07",
      endDate: "24.01.18",
    },
    {
      img: imgDog8,
      startDate: "24.01.08",
      endDate: "24.01.18",
    },
    {
      img: imgDog9,
      startDate: "24.01.09",
      endDate: "24.01.18",
    },
  ]

  const scrapList = [
    {
      img: imgDog1,
      title: "제목 1",
    },
    {
      img: imgDog2,
      title: "제목 2",
    },
    {
      img: imgDog3,
      title: "제목 3",
    },
    {
      img: imgDog4,
      title: "제목 4",
    },
    {
      img: imgDog5,
      title: "제목 5",
    },
    {
      img: imgDog6,
      title: "제목 6",
    },
    {
      img: imgDog7,
      title: "제목 7",
    },
    {
      img: imgDog8,
      title: "제목 8",
    },
    {
      img: imgDog9,
      title: "제목 9",
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
            <img className="mx-2" src={iconDm} width={30} height={30} />
            <button className="me-3 btn btn-secondary">팔로우</button>
          </div>

          <div className="profile-top-div-bottom-div-middle-div">
            <span className="profile-top-div-bottom-div-middle-span">{userProfile.description}</span>
          </div>

          <div className="profile-top-div-bottom-div-bottom-div">
            <span className="ms-3 profile-top-div-bottom-div-bottom-span">팔로워 {userProfile.follower}</span>
            <span className="mx-3 profile-top-div-bottom-div-bottom-span">팔로우 {userProfile.following}</span>
          </div>
        </div>

        <div className="profile-top-div-middle-div">
          <ProfileCircle
            img={userProfile.profileImg}
            name={userProfile.name}
          />
        </div>
      </div>

      <div className="profile-nav-div">
        <Link to="/profile">
          <div className="profile-nav-div-img-div">
            <img className="profile-nav-div-img" src={iconDog} />
          </div>
        </Link>
        <Link to="/profile/post">
          <div className="profile-nav-div-img-div">
            <img className="profile-nav-div-img" src={iconPost} />
          </div>
        </Link>
        <Link to="/profile/story">
          <div className="profile-nav-div-img-div">
            <img className="profile-nav-div-img" src={iconStory} />
          </div>
        </Link>
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
          <Route path="/scrap" element={<ProfileScrap scrapList={scrapList} />} />
        </Routes>
      </div>
    </div>
  );
}
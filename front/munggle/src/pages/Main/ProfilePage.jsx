import React from "react";
import "./ProfilePage.css";
import { Route, Routes, Link } from "react-router-dom";

import ProfileDog from "../../components/Profile/ProfileDog";
import ProfilePost from "../../components/Profile/ProfilePost";
import ProfileStory from "../../components/Profile/ProfileStory";
import ProfileScrap from "../../components/Profile/ProfileScrap";

import FollowButton from "../../components/button/FollowButton";
import iconDm from "../../assets/icons/dm.png";
import iconDog from "../../assets/icons/profileDog.png";
import iconPost from "../../assets/icons/profilePost.png";
import iconStory from "../../assets/icons/profileStory.png";
import iconScrap from "../../assets/icons/profileScrap.png";


export default function Profile() {
  const userProfile = {
    // backGroundImg:,
    // profileImg:,
    // name:,
    // isFollow:,
    // description:,
    // follower:,
    // following:,
    dogs: [
      {
        // img:,
        // name:,
        // kind:,
        // weight:,
        // birthDate:,
        // gender:,
      },
      {
        // img:,
        // name:,
        // kind:,
        // weight:,
        // birthDate:,
        // gender:,
      },
    ]
  } 

  return (
    <div className="profile-container-div">
      <div className="profile-top-div">
        <div className="profile-top-div-top-div">
            배경이미지
           {/* <img src={userProfile.backGroundImg} /> */}
        </div>
        <div className="profile-top-div-bottom-div">
          <div className="profile-top-div-bottom-div-top-div">
            <img src={iconDm} width={20} height={20} />
            <button className="btn btn-secondary">팔로우</button>
          </div>
          <div className="profile-top-div-bottom-div-middle-div">
            {/* <span>{userProfile.description}</span> */}
            설명
          </div>
          <div className="profile-top-div-bottom-div-bottom-div">
            {/* <span>팔로워 {userProfile.follower}</span> */}
            {/* <span>팔로우 {userProfile.following}</span> */}
            <span>몇명</span>
            <span>몇명</span>
          </div>
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
          <Route path="/" element={<ProfileDog />} />
          <Route path="/post" element={<ProfilePost />} />
          <Route path="/story" element={<ProfileStory />} />
          <Route path="/scrap" element={<ProfileScrap />} />
        </Routes>
      </div>
    </div>
  );
}
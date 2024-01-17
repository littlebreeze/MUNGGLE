import React, { useState } from "react";
import "./StoryPage.css";
import ProfileCircle from "../../components/ProfileCircle";
import axios from "axios";
import imgProfile from "../../assets/example/profile.png";
import imgStory from "../../assets/example/dog1.jpg"
import iconRecommend from "../../assets/icons/recommend.png";
import iconFollow from "../../assets/icons/follow.png";
import ToggleButton from "../../components/button/ToggleButton";
import StoryComponent from "../../components/Story/StoryComponent";


export default function Story() {
  const profiles = [
    {
      img: imgProfile,
      name: "user1",
    },
    {
      img: imgProfile,
      name: "user2",
    },
    {
      img: imgProfile,
      name: "user3",
    },
    {
      img: imgProfile,
      name: "user4",
    },
  ]

  const toggleButton = {
    img1: iconRecommend,
    text1: "추천",
    img2: iconFollow,
    text2: "팔로잉",
  };

  const profileList = profiles.
    map((profile) => {
      return (
        <ProfileCircle
          img={profile.img}
          name={profile.name}
        />
      );
  })

  const storyList = [
    {
      user : {
        imgProfile: imgProfile,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
    {
      user : {
        imgProfile: imgProfile,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
    {
      user : {
        imgProfile: imgProfile,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
  ]


  return (
    <div className="story-container-div">
      <div className="story-top-div">
        <div className="story-top-div-top-div">
          <ToggleButton 
            img1={iconRecommend}
            text1="추천"
            img2={iconFollow}
            text2="팔로잉"
          />
        </div>
        <div className="story-top-div-bottom-div">
          {profileList}
        </div>
      </div>
      <div className="story-bottom-div">
        <StoryComponent 
          storyList={storyList}
        />
      </div>
    </div>
  );
}
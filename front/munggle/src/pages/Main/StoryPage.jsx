import React, { useState } from "react";
import "./StoryPage.css";
import ProfileCircle from "../../components/ProfileCircle";
import axios from "axios";

import imgProfile1 from "../../assets/example/profile1.jpg";
import imgProfile2 from "../../assets/example/profile2.jpg";
import imgProfile3 from "../../assets/example/profile3.jpg";
import imgProfile4 from "../../assets/example/profile4.jpg";
import imgProfile5 from "../../assets/example/profile5.jpg";
import imgProfile6 from "../../assets/example/profile6.jpg";
import imgProfile7 from "../../assets/example/profile.png";

import imgStory1 from "../../assets/example/dog1.jpg"
import imgStory2 from "../../assets/example/dog2.jpg"
import imgStory3 from "../../assets/example/dog3.jpg"
import imgStory4 from "../../assets/example/dog4.jpg"
import imgStory5 from "../../assets/example/dog5.jpg"
import imgStory6 from "../../assets/example/dog6.jpg"
import imgStory7 from "../../assets/example/dog7.jpg"
import imgStory8 from "../../assets/example/dog8.jpg"
import imgStory9 from "../../assets/example/dog9.jpg"
import imgStory10 from "../../assets/example/dog10.jpg"

import iconRecommend from "../../assets/icons/recommend.png";
import iconFollow from "../../assets/icons/follow.png";
import ToggleButton from "../../components/button/ToggleButton";
import StoryComponent from "../../components/Story/StoryComponent";
import CreateButton from "../../components/button/CreateButton";

export default function Story() {
  const profiles = [
    {
      img: imgProfile1,
      name: "user1",
    },
    {
      img: imgProfile2,
      name: "user2",
    },
    {
      img: imgProfile3,
      name: "user3",
    },
    {
      img: imgProfile4,
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
    map((profile, index) => {
      return (
        <ProfileCircle
          img={profile.img}
          name={profile.name}
          key={index}
        />
      );
  })

  const storyList = [
    {
      user : {
        imgProfile: imgProfile1,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory1,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
    {
      user : {
        imgProfile: imgProfile2,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory2,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
    {
      user : {
        imgProfile: imgProfile3,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory3,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
    {
      user : {
        imgProfile: imgProfile4,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory4,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
    {
      user : {
        imgProfile: imgProfile5,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory5,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
    {
      user : {
        imgProfile: imgProfile6,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory6,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
    {
      user : {
        imgProfile: imgProfile7,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory7,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
    {
      user : {
        imgProfile: imgProfile2,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory8,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
    {
      user : {
        imgProfile: imgProfile3,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory9,
      title: "산책하는 댕댕이",
      startDate: "24.01.12",
      endDate: "24.01.17",
    },
    {
      user : {
        imgProfile: imgProfile4,
        name: 'megar0829',
        isFollow: false,
      },
      imgStory: imgStory10,
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
      <CreateButton />
    </div>
  );
}
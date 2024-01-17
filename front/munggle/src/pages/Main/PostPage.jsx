import React, { useState, useEffect } from "react";
import "./PostPage.css";
import ProfileCircle from "../../components/ProfileCircle";
import axios from "axios";
import imgProfile from "../../assets/example/profile.png";
import imgPost from "../../assets/example/dog1.jpg"
import iconRecommend from "../../assets/icons/recommend.png";
import iconFollow from "../../assets/icons/follow.png";
import ToggleButton from "../../components/button/ToggleButton";
import PostComponent from "../../components/Post/PostComponent";

export default function Post() {
  // const [posts, setPosts] = useState([]);
  // const [recommendProfile, setRecommendProfile] = useState([]);

  // const getPostList = () => {
  //   axios.get('url')
  //     .then((res) => {
  //       console.log(res.data)
  //       setPosts(res.data)
  //     })
  //     .catch((err) => console.log(err))
  // }

  // useEffect(() => {
  //   getPostList()
  // }, []);

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
    map((profile, index) => {
      return (
        <ProfileCircle
          key={index}
          img={profile.img}
          name={profile.name}
        />
      );
  })

  const postList = [
    {
      user : {
        imgProfile: imgProfile,
        name: 'megar0829',
        isFollow: false,
      },
      imgPost: imgPost,
      title: "산책하는 댕댕이",
      createdAt: "2024-01-17",
      tagList: [
        "산책", "댕댕이", "신났네",
      ],
    },
    {
      user : {
        imgProfile: imgProfile,
        name: 'megar0829',
        isFollow: false,
      },
      imgPost: imgPost,
      title: "산책하는 댕댕이",
      createdAt: "2024-01-17",
      tagList: [
        "산책", "댕댕이", "신났네",
      ],
    },
  ]


  return (
    <div className="post-container-div">
      <div className="post-top-div">
        <div className="post-top-div-top-div">
          <ToggleButton 
            img1={iconRecommend}
            text1="추천"
            img2={iconFollow}
            text2="팔로잉"
          />
        </div>
        <div className="post-top-div-bottom-div">
          {profileList}
        </div>
      </div>
      <div className="post-bottom-div">
        <PostComponent 
          postList={postList}
        />
      </div>
    </div>
  );
}
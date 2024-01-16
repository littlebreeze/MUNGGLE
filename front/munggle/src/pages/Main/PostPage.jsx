import React, { useState, useEffect } from "react";
import "../../styles/css/pages/PostPage.css";
import ProfileCircle from "../../components/ProfileCircle";
import axios from "axios";
import imgProfile from "../../assets/example/profile.png";

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

  const profileList = profiles.
    map((profile) => {
      return (
        <ProfileCircle
        img={profile.img}
        name={profile.name}
        />
      );
  })


  return (
    <div className="post-container-div">
      <div className="post-top-div">
        <div className="post-top-div-top-div">
        1
        </div>
        <div className="post-top-div-bottom-div">
          {profileList}
        </div>
      </div>
      <div className="post-bottom-div">
        1 
      </div>
    </div>
  );
}
import React, { useState } from "react";
import "./UserProfileComponent.css";
import ProfileCircle from "./ProfileCircle";
import FollowButton from "./button/FollowButton";
import iconDm from "../assets/icons/dm.png";

export default function UserProfileComponent(props) {
  const [isFollow, setIsFollow] = useState(props.isFollow)
  
  const handleIsFollow = () => {
    setIsFollow(!isFollow)
  }

  return (
    <div className="post-profile-container-div">
      <ProfileCircle
        img={props.imgProfile}
        name={props.name}
      />
      <div className="post-profile-bottom-div">
        <FollowButton 
          isFollow={isFollow}
          onClick={handleIsFollow}
        />
        <img className="post-profile-bottom-icon" src={iconDm} />
      </div>
    </div>
  );
}
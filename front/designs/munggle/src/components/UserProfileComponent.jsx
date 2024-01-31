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
        width={55}
        height={55}
      />
      <div className="post-profile-bottom-div">
        <div className="post-profile-bottom-div-left-div">
          <FollowButton 
            isFollow={isFollow}
            onClick={handleIsFollow}
          />
        </div>
        <div className="post-profile-bottom-div-right-div">
          <img className="post-profile-bottom-div-right-div-icon" src={iconDm} />
        </div>
      </div>
    </div>
  );
}
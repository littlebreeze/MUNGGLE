import React from "react";
import "./FollowButton.css";

export default function FollowButton(props) {
  if (props.isFollow) {
    return <div className="follow-button-div">
      <span className="follow-button-span">팔로잉</span>
    </div>
  } else {
    return <div className="follow-button-div">
      <span className="follow-button-span">팔로잉</span>
    </div>
  };
}
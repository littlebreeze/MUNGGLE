import React from "react";
import "./ProfilePostFrame.css";
import imgFrame from "../../assets/example/post-frame2.png";

export default function ProfilePostFrame(props) {
  return (
    <div className="profile-post-frame-container my-2">
      <img className="profile-post-frame-frame" src={imgFrame} />
      <img className="profile-post-frame-image" src={props.img} />
      <div className="profile-post-frame-title-div">
        <span className="profile-post-frame-title-span">{props.title}</span>
      </div>
    </div>
  );
}
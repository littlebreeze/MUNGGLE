import React from "react";
import "./ProfileStoryFrame.css";
import imgFrame from "../../assets/example/story-frame.png"

export default function ProfileStoryFrame(props) {
  return (
    <div className="profile-story-frame-container">
      <img className="profile-story-frame-frame" src={imgFrame} />
      <div className="profile-story-frame-img-div">
        <img className="profile-story-frame-img" src={props.img} />
      </div>
      <div className="profile-story-date-div">
        <span className="profile-story-date-span">{props.startDate} ~ {props.endDate}</span>
      </div>
    </div>
  );
}
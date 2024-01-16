import React from "react";
import "../styles/css/components/ProfileCircle.css";

export default function ProfileCircle(props) {
  return (
    <div className="profile-circle-container-div">
      <div className="profile-circle-img-div">
        <img src={props.img} className="profile-circle-img-div"/>
      </div>
      <div className="profile-circle-name-div">
        <span className="fs-5">{props.name}</span>
      </div>
    </div>
  );
}
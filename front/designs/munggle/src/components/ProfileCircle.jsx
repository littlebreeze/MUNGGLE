import React from "react";
import "./ProfileCircle.css";

export default function ProfileCircle(props) {
  return (
    <div className="profile-circle-container-div">
      <div className="profile-circle-img-div">
        <img 
          className="profile-circle-img-div" 
          src={props.img} 
          width={props.width} 
          height={props.height}
        />
      </div>
      <div className="profile-circle-name-div">
        <span className="profile-circle-name-span">{props.name}</span>
      </div>
    </div>
  );
}
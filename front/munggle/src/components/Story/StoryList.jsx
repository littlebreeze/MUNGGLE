import React from "react";
import "./StoryList.css";
import imgMenu from "../../assets/icons/menu.png"

export default function StoryList(props) {
  return (
    <div className="story-list-container-div">
      <div className="story-list-menu-icon">
        <img className="ms-1 mt-3 position-relative" src={imgMenu} width={20} height={20}/>
      </div>
      <div className="story-list-top-div">
        <img className="story-list-top-img" src={props.imgStory} />
        <span className="story-list-top-text-span">{props.startDate} ~ {props.endDate}</span>
      </div>
      <div className="story-list-bottom-div">
        <span className="story-list-bottom-span">{props.title}</span>
      </div>
    </div>
   ); 
  }
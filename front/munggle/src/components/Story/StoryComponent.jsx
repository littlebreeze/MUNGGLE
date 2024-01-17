import React from "react";
import "./StoryComponent.css";
import UserProfileComponent from "../UserProfileComponent";
import StoryList from "./StoryList";

export default function StoryComponent(props) {
  const storyList = props.storyList

  const Stories = storyList
    .map((story) => {
      return (
        <div className="story-list-detail-container-div">
          <div className="story-list-left-div">
            <UserProfileComponent 
              imgProfile={story.user.imgProfile}
              name={story.user.name}
              isFollow={story.user.isFollow}
            />
          </div>
          <div className="story-list-right-div">
            <StoryList
              imgStory={story.imgStory}
              title={story.title}
              startDate={story.startDate}
              endDate={story.endDate}
            />
          </div>
        </div>
      );
    })
  
  return (
    <div className="story-container-div">
      {Stories}
    </div>
  );
}
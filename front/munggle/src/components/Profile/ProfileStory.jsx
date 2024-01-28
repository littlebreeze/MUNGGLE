import React from "react";
import "./ProfileStory.css";
import ProfileStoryFrame from "./ProfileStoryFrame";

export default function ProfileStory(props) {
  const storyList = props.storyList
  
  const stories = storyList.
      map((story, index) => {
        return (
          <ProfileStoryFrame 
            img={story.img}
            startDate={story.startDate}
            endDate={story.endDate}
            key={index}
          />
        );
      })

  return (
    <div className="profile-story-container">
      {stories}
    </div>
  );
}
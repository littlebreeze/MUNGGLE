import React from "react";
import "./PostListContent.css";
import HashTagButton from "../button/HashTagButton";

export default function PostListContent(props) {
  const tagList = props.tagList
  
  const tagButton = tagList
    .map((tag, index) => {
      return (
        <HashTagButton 
          key={index}
          tagText={tag}
        />
      );
    })

  return (
    <div className="post-list-content-container-div">
      <div className="post-list-content-top-div">
        <div className="post-list-content-top-div-left-div">
          <span className="post-list-content-top-div-left-span">{props.title}</span>
        </div>
        <div className="post-list-content-top-div-right-div">
          <span className="post-list-content-top-div-right-span">{props.createdAt}</span>
        </div>
      </div>
      <div className="post-list-content-bottom-div">
        {tagButton} 
      </div>
    </div>
  );
}
import React from "react";
import "./PostListContent.css";
import HashTagButton from "../button/HashTagButton";

import imgLike from "../../assets/icons/bornWhite.png"
import imgScrap from "../../assets/icons/scrap.png"

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
        <p></p>
        <div className="post-list-content-top-div-right-div">
          <img 
            className="post-list-content-top-div-right-left-img" 
            src={imgLike}
            width={20}
            height={20}
          />
          <img 
            className="post-list-content-top-div-right-right-img" 
            src={imgScrap}
            width={20}
            height={20}
          />
        </div>
      </div>
      <div className="post-list-content-middle-div">
          <span className="post-list-content-middle">{props.createdAt}</span>
        </div>
      <div className="post-list-content-bottom-div">
        {tagButton} 
      </div>
    </div>
  );
}
import React from "react";
import "./PostDetailContent.css";
import imgMenu from "../../assets/icons/menu.png"
import HashTagButton from "../button/HashTagButton";

export default function PostDetailContent(props) {
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
    <div className="post-detail-container-div">
      <div className="post-detail-menu-icon">
        <img className="ms-1 mt-3" src={imgMenu} width={20} height={20}/>
      </div>
      <div className="post-detail-top-div">
        <img className="post-detail-top-img" src={props.imgPost} />
      </div>
      <div className="post-detail-content-container-div">
        <div className="post-detail-content-top-div">
          <div className="post-detail-content-top-div-left-div">
            <span className="post-detail-content-top-div-left-span">{props.title}</span>
          </div>
          <div className="post-detail-content-top-div-right-div">
            <span className="post-detail-content-top-div-right-span">{props.createdAt}</span>
          </div>
        </div>
        <div className="post-detail-content-middle-div">
          <span>{ props.content }</span>
        </div>
        <div className="post-detail-content-bottom-div">
          {tagButton} 
        </div>
      </div>
    </div>
  );
}
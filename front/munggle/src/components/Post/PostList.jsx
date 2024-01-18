import React from "react";
import "./PostList.css";
import PostListContent from "./PostListContent";
import imgMenu from "../../assets/icons/menu.png"

export default function PostList(props) {
 return (
  <div className="post-list-container-div">
    <div className="post-list-menu-icon">
      <img className="ms-1 mt-3" src={imgMenu} width={20} height={20}/>
    </div>
    <div className="post-list-top-div">
      <img className="post-list-top-img" src={props.imgPost} />
    </div>
    <PostListContent
      title={props.title}
      createdAt={props.createdAt}
      tagList={props.tagList}
    />
  </div>
 ); 
}
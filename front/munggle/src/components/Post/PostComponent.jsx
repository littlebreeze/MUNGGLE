import React from "react";
import "./PostComponent.css";
import UserProfileComponent from "../UserProfileComponent";
import PostList from "./PostList";
import PostDetailContent from "./PostDetailContent";

export default function PostComponent(props) {
  const postList = props.postList

  const posts = postList
    .map((post, index) => {
      return (
        <div key={index} className="post-list-detail-container-div">
          <div className="post-list-left-div">
            <UserProfileComponent 
              imgProfile={post.user.imgProfile}
              name={post.user.name}
              isFollow={post.user.isFollow}
            />
          </div>
          <div className="post-list-right-div">
            <PostList
              imgPost={post.imgPost}
              title={post.title}
              createdAt={post.createdAt}
              tagList={post.tagList}
            />
          </div>
        </div>
      );
    })
  
  return (
    <div className="post-container-div">
      {posts}
    </div>
  );
}
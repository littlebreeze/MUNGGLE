import React, { useState } from "react";
import "./PostComponent.css";
import UserProfileComponent from "../UserProfileComponent";
import PostList from "./PostList";
import PostDetailContent from "./PostDetailContent";
import PostDetailModal from "../modal/PostDetailModal";

export default function PostComponent(props) {
  const postList = props.postList
  
  const [postDetailModalIsOpen, setPostDetailModalIsOpen] = useState(false);

  const [postData, setPost] = useState({})

  function openPostDetailModal(postData) {
    setPost(postData)
    setPostDetailModalIsOpen(true);
  }

  function closePostDetailModal() {
    setPostDetailModalIsOpen(false);
  }

  const posts = postList
    .map((post) => {
      return (
        <div key={post.id}  onClick={(e) => openPostDetailModal(post)} className="post-list-detail-container-div">
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
    <div className="post-bottom-div-container-div">
      {posts}
      <PostDetailModal 
        postData={postData}
        isOpen={postDetailModalIsOpen}
        closeModal={closePostDetailModal}
      />
    </div>
  );
}
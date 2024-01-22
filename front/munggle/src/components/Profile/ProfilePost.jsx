import React, { useState } from "react";
import "./ProfilePost.css";
import ProfilePostFrame from "./ProfilePostFrame";
import PostDetailModal from "../modal/PostDetailModal";

export default function ProfilePost(props) {
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

  const posts = postList.
        map((post) => {
          return (
            <div onClick={(e) => openPostDetailModal(post)}>
              <ProfilePostFrame 
                img={post.imgPost}
                title={post.title}
                key={post.id}
              />
            </div>
          );
        })

  return (
    <div className="profile-post-container">
      <div className="profile-post-list-container">
        {posts}
        <PostDetailModal 
          postData={postData}
          isOpen={postDetailModalIsOpen}
          closeModal={closePostDetailModal}
        />
      </div>
    </div>
  );
}
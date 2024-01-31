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
            <div className="profile-post-frame-container-div col" key={post.id} onClick={(e) => openPostDetailModal(post)}>
              <ProfilePostFrame 
                img={post.imgPost}
                title={post.title}
              />
            </div>
          );
        })

  return (
    <div className="profile-post-container">
      <div className="row row-cols-3 g-1">
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
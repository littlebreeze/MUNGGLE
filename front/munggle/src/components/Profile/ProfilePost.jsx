import React from "react";
import "./ProfilePost.css";
import ProfilePostFrame from "./ProfilePostFrame";

export default function ProfilePost(props) {
  const postList = props.postList

  const posts = postList.
        map((post, index) => {
          return (
            <ProfilePostFrame 
              img={post.img}
              title={post.title}
              key={index}
            />
          );
        })

  return (
    <div className="profile-post-container">
      <div className="profile-post-list-container">
        {posts}
      </div>
    </div>
  );
}
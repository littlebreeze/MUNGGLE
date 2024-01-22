import React, { useState } from "react";
import "./ProfileScrap.css";
import { ImageList, ImageListItem } from '@mui/material';
import PostDetailModal from "../modal/PostDetailModal";

export default function ProfileScrap(props) {
  const scrapList = props.scrapList

  const [postDetailModalIsOpen, setPostDetailModalIsOpen] = useState(false);

  const [postData, setPost] = useState({})

  function openPostDetailModal(postData) {
    setPost(postData)
    setPostDetailModalIsOpen(true);
  }

  function closePostDetailModal() {
    setPostDetailModalIsOpen(false);
  }

  return (
    <div>
      <ImageList variant="masonry" cols={3} gap={8}>
        {scrapList.map((scrap) => (
          <ImageListItem key={scrap.id}>
            <div onClick={(e) => openPostDetailModal(scrap)}>
              <img
                srcSet={`${scrap.imgPost}?w=248&fit=crop&auto=format&dpr=2 2x`}
                src={`${scrap.imgPost}?w=248&fit=crop&auto=format`}
                alt={scrap.title}
                loading="lazy"
                />
            </div>
          </ImageListItem>
        ))}
      </ImageList>
      <PostDetailModal 
        postData={postData}
        isOpen={postDetailModalIsOpen}
        closeModal={closePostDetailModal}
      />
    </div>
  );
}
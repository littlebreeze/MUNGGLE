import React, { useState } from "react";
import "./SearchPostDetailComponent.css";
import ProfileCircle from "../../ProfileCircle";

import iconComment from "../../../assets/icons/dm.png";
import iconLikeBlack from "../../../assets/icons/bornBlack.png";
import iconLikeWhite from "../../../assets/icons/bornWhite.png";

export default function SearchPostDetailComponent (props) {
  const postData = props.postData

  const [isLike, setIsLike] = useState(false)

  const handleClick = () => {
    setIsLike(!isLike)
  }

  const checkLike = () => {
    if (isLike) {
      return iconLikeBlack
    } else {
      return iconLikeWhite
    }
  }

  const iconLikeUrl = checkLike()

  return (
    <div className="search-post-detail-container">
      <div className="search-post-detail-left-div">
        <ProfileCircle  
          img={postData.user.imgProfile}
          name={postData.user.name}
        />
      </div>
      <div className="search-post-detail-right-div">
        <div className="search-post-detail-right-top-div">
          <div className="search-post-detail-right-top-img-div">
            <img className="search-post-detail-right-top-img" src={postData.imgPost} />
          </div>
          <div className="search-post-detail-right-top-icon-div">
            <img onClick={handleClick} className="search-post-detail-right-top-icon" src={iconLikeUrl} />
            <img className="search-post-detail-right-top-icon" src={iconComment} />
          </div>
        </div>
        <div className="search-post-detail-right-bottom-div">
          <div className="search-post-detail-right-bottom-left-div">
            <span className="search-post-detail-right-bottom-left-span">{postData.title}</span>
          </div>
          <div className="search-post-detail-right-bottom-right-div">
            <span className="search-post-detail-right-bottom-right-span">{postData.createdAt}</span>
          </div>
        </div>
      </div>
    </div>
  );
}
import React, { useEffect, useRef, useState } from "react";
import "./PostDetailContent.css";
import imgMenu from "../../assets/icons/menu.png"
import HashTagButton from "../button/HashTagButton";
import imgLike from "../../assets/icons/bornWhite.png"
import imgBottomArrow from "../../assets/icons/bottomArrow.png"
import Comment from "../comment/Comment";

export default function PostDetailContent(props) {
  const tagList = props.tagList
  const comments = props.comments

  const [commentsCount, setCommentsCount] = useState(0)

  useEffect(() => {
    setCommentsCount(comments.length)
  }, [comments.length])

  const scrollRef = useRef(null);

  const scrollToBottom = () => {
    scrollRef.current.scrollIntoView({ behavior: "smooth" });
  };

  const commentList = comments
    .map((comment) => {
      return (
        <Comment
          key={comment.id}
          name={comment.name}
          content={comment.content}
        />
      )
    })
  
  const tagButton = tagList
    .map((tag, index) => {
      return (
        <HashTagButton 
          key={index}
          tagText={tag}
          height={26}
          fontSize={17}
        />
      );
    })

  return (
    <div className="post-detail-container-div">
      {/* <div className="post-detail-menu-icon">
        <img className="ms-1 mt-3" src={imgMenu} width={20} height={20}/>
      </div> */}
      <div className="post-detail-top-div">
        <img className="post-detail-top-img" src={props.imgPost} />
      </div>
      <div className="post-detail-content-container-div">
        <div className="post-detail-content-top-div">
          <div className="post-detail-content-top-div-left-div">
            <span className="post-detail-content-top-div-left-span">{props.title}</span>
          </div>
          <div className="post-detail-content-top-div-right-div">
            <span className="post-detail-content-top-div-right-span">{props.likes}</span>
            <img 
              className="post-detail-content-top-div-right-img" 
              src={imgLike}
              width={19}
              height={19}
            />
          </div>
        </div>
        <div className="post-detail-content-middle-div">
          <div className="post-detail-content-middle-div-top-div">{props.createdAt}</div>
          <div className="post-detail-content-middle-div-bottom-div">
            <span className="post-detail-content-middle-div-bottom-div-span">{ props.content }</span>
          </div>
        </div>
        <div className="post-detail-content-bottom-div">
          {tagButton} 
        </div>
      </div>
      <div className="post-detail-bottom-div">
        <div className="post-detail-bottom-div-top-div">
          <div className="post-detail-bottom-div-top-div-left-div" onClick={scrollToBottom}>
            <span className="post-detail-bottom-div-top-div-left-div-span">댓글</span>
            <img className="post-detail-bottom-div-top-div-left-div-img"
              src={imgBottomArrow}
              width={23}
              height={23}
            />  
          </div>
          <div className="post-detail-bottom-div-top-div-right-div">
            <span className="post-detail-bottom-div-top-div-right-div-span">
              {commentsCount} 개
            </span>  
          </div>
        </div>
        <div className="post-detail-bottom-div-bottom-div">
          <div className="post-detail-bottom-div-bottom-div-top-div">
            {commentList}  
          </div>
          <div className="post-detail-bottom-div-bottom-div-bottom-div" ref={scrollRef}>
            <input className="comment-input-content" placeholder="내용을 입력해주세요." type="text" />
          </div>
        </div>
      </div>
    </div>
  );
}
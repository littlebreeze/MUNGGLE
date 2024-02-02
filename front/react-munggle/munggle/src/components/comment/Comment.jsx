import React from "react";
import "./Comment.css"

export default function comment(props) {
  return (
    <div className="comment-container-div">
      <div className="comment-container-div-left-div">
        {props.name}
      </div>
      <div className="comment-container-div-right-div">
        {props.content}
      </div>
    </div>
  );
}
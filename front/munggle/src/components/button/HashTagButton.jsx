import React from "react";
import "./HashTagButton.css";

export default function HashTagButton(props) {
  return (
    <div className="hashtag-button-div">
      <span className="hashtag-button-span"># {props.tagText}</span>
    </div>
  );
}
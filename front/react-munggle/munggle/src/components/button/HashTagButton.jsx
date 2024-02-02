import React from "react";
import "./HashTagButton.css";

export default function HashTagButton(props) {
  return (
    <div className="hashtag-button-div" style={{ height: props.height }}>
      <span className="hashtag-button-span" style={{ fontSize: props.fontSize }}>
        # {props.tagText}
      </span>
    </div>
  );
}
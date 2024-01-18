import React from "react";
import "./ToggleButton.css";

export default function ToggleButton(props) {
  return (
    <div className="toggle-button-container-div">
      <div className="toggle-button-left-div">
        <img src={props.img1} width={30} height={30} />
        <span>{props.text1}</span>
      </div>
      <div className="toggle-button-right-div">
        <img className="ms-2" src={props.img2} width={30} height={30} />
        <span>{props.text2}</span>
      </div>
    </div>
  );
}
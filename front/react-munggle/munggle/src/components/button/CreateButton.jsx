import React from "react";
import "./CreateButton.css";
import iconCreate from "../../assets/icons/create.png";

export default function CreateButton () {
  return (
    <div className="create-button-div">
      <img className="create-button" src={iconCreate} />
    </div>
  );
}

import React, { useState } from "react";
import "./Nav.css";
import imgLogo from "../../assets/logo.png";
import imgSearch from "../../assets/icons/search.png";
import imgNotification from "../../assets/icons/notification.png";
import imgDm from "../../assets/icons/dm.png";
import { Link } from "react-router-dom";

export default function Nav(props) {

  return (
    <div className="nav-container-div">
      <div className="nav-left-div">
        <Link to="/">
          <img src={imgLogo} width={100} />
        </Link>
      </div>
      <div onClick={props.openSearchModal} className="nav-right-div">
        <div className="nav-right-div-left">
          <img src={imgSearch} width={30} height={30} />
        </div>
        <div className="nav-right-div-middle">
          <Link to="/notification">
            <img src={imgNotification} width={30} height={30} />
          </Link>
        </div>
        <div className="nav-right-div-right">
          <Link to="/directmessage">
            <img src={imgDm} width={30} height={30} />
          </Link>
        </div>
      </div>
    </div>
  );
}
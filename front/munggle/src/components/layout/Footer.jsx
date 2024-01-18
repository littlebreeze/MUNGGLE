import React from "react";
import "./Footer.css";
import iconHome from "../assets/icons/home.png";
import iconStory from "../assets/icons/story.png";
import iconWalk from "../assets/icons/walk.png";
import iconProfile from "../assets/icons/profile.png";
import { Link } from "react-router-dom";

export default function Footer() {
  return (
    <div className="footer-container-div">
      <div className="footer-div-1">
        <Link to="/">
          <img src={iconHome} width={55} height={55} />
        </Link>
      </div>
      <div className="footer-div-2">
        <Link to="/story">
          <img src={iconStory} width={55} height={55} />
        </Link>
      </div>
      <div className="footer-div-3">
        <Link to="/walk">
          <img src={iconWalk} width={55} height={55} />
        </Link>
      </div>
      <div className="footer-div-4">
        <Link to="/profile">
          <img src={iconProfile} width={55} height={55} />
        </Link>
      </div>
    </div>
  );
}
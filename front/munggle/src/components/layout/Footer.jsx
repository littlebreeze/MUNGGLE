import React from "react";
import "./Footer.css";
import iconHome from "../../assets/icons/home.png";
import iconWalk from "../../assets/icons/walk.png";
import iconMatching from "../../assets/icons/matching.png";
import iconProfile from "../../assets/icons/profile.png";
import { Link } from "react-router-dom";

export default function Footer() {
  return (
    <div className="footer-container-div">
      <div className="footer-div-1">
        <Link to="/">
          {/* <img src={iconHome} width={55} height={55} /> */}
          <img src={iconHome} width={38} height={38} />
        </Link>
      </div>
      <div className="footer-div-2">
        <Link to="/walk">
          {/* <img src={iconWalk} width={55} height={55} /> */}
          <img src={iconWalk} width={40} height={40} />
        </Link>
      </div>
      <div className="footer-div-3">
        <Link to="/matching">
          {/* <img src={iconWalk} width={55} height={55} /> */}
          <img src={iconMatching} width={40} height={40} />
        </Link>
      </div>
      <div className="footer-div-4">
        <Link to="/profile">
          {/* <img src={iconWalk} width={55} height={55} /> */}
          <img src={iconProfile} width={40} height={40} />
        </Link>
      </div>
    </div>
  );
}
import React, { useState } from "react";
import "./Nav.css";
import imgLogo from "../../assets/logo.png";
import imgSearch from "../../assets/icons/search.png";
import imgNotification from "../../assets/icons/notification.png";
import imgDm from "../../assets/icons/dm.png";
import { Link } from "react-router-dom";
import { Route, Routes } from "react-router-dom";
import NavMain from "./NavMain"
import NavWalk from "./NavWalk"
import NavMatching from "./NavMatching"
import NavProfile from "./NavProfile"

export default function Nav(props) {

  return (
    <div className="nav-router-div">
      <Routes>
        <Route path="/*" element={<NavMain />} />
        <Route path="/matching/*" element={<NavMatching />} />
        <Route path="/walk" element={<NavWalk />} />
        <Route path="/profile/*" element={<NavProfile />} />
      </Routes>
    </div>
  );
}
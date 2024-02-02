import React, { useState, useEffect } from "react";
import "./WalkPage.css";

import { Route, Routes } from "react-router-dom";
import { Link } from "react-router-dom";

import WalkMainPage from "../../components/Walk/WalkMain";

export default function Walk(props) {

  return (
    <div className="walk-container-div">
      <Routes>
        <Route path="/" element={<WalkMainPage />} />
      </Routes>
    </div>
  );
}

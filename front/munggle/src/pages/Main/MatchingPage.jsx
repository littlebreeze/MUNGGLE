import React, { useState } from "react";
import "./MatchingPage.css";
import { Route, Routes } from "react-router-dom";

import MatchingMainPage from "../../components/Matching/MatchingMainPage";
import MatchingMatchingPage from "../../components/Matching/MatchingMatchingPage";
import MatchingInfoEditPage from "../../components/Matching/MatchingInfoEditPage";

export default function Matching() {
  
  return (
    <div className="matching-container-div">
      <Routes>
        <Route path="/" element={<   MatchingMainPage />} />
        <Route path="/start" element={<MatchingMatchingPage />} />
        <Route path="/edit" element={<MatchingInfoEditPage />} />
      </Routes>
    </div>
  );
}
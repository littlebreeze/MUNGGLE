import React, { useState } from "react";
import "./MatchingPage.css";
import { Route, Routes } from "react-router-dom";

import imgDog from "../../assets/dogLogo.png";
import imgSearch from "../../assets/icons/search.png";
import imgNotification from "../../assets/icons/notification.png";
import imgDm from "../../assets/icons/dm.png";
import { Link } from "react-router-dom";

import MatchingMainPage from "../../components/Matching/MatchingMainPage";
import MatchingMatchingPage from "../../components/Matching/MatchingMatchingPage";
import MatchingInfoEditPage from "../../components/Matching/MatchingInfoEditPage";

export default function Matching(props) {
  
  return (
    <div className="matching-container-div">
          <div className="matching-nav-container-div">
      <div className="matching-nav-left-div">
        <Link to="/matching">
          <img src={imgDog} width={80} height={40} />
        </Link>
      </div>
      <div className="matching-nav-right-div">
        <div onClick={props.openSearchModal} className="matching-nav-right-div-left">
          {/* <img src={imgSearch} width={30} height={30} /> */}
          <img src={imgSearch} width={27} height={27} />
        </div>
        <div className="matching-nav-right-div-middle">
          <Link to="/notification">
            {/* <img src={imgNotification} width={30} height={30} /> */}
            <img src={imgNotification} width={34} height={34} />
          </Link>
        </div>
        <div className="matching-nav-right-div-right">
          <Link to="/directmessage">
            {/* <img src={imgDm} width={32} height={32} /> */}
            <img src={imgDm} width={32} height={32} />
          </Link>
        </div>
      </div>
    </div>
      <Routes>
        <Route path="/" element={<MatchingMainPage />} />
        <Route path="/start" element={<MatchingMatchingPage />} />
        <Route path="/edit" element={<MatchingInfoEditPage />} />
      </Routes>
    </div>
  );
}
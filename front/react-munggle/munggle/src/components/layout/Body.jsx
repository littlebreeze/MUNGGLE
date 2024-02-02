import React from "react";
import "./Body.css";
import { Route, Routes } from "react-router-dom";
import Post from "../../pages/Main/PostPage";
import Profile from "../../pages/Main/ProfilePage";
import Matching from "../../pages/Main/MatchingPage";
import Walk from "../../pages/Main/WalkPage";
import DirectMessage from "../../pages/Sub/DirectMessagePage";
import Notification from "../../pages/Sub/NotificationPage";
import Search from "../../pages/Sub/SearchPage";
import Login from "../../pages/Auth/LoginPage";
import SignUp from "../../pages/Auth/SignUpPage";

export default function Body(props) {
  return (
    <div className="body-container-div">
      <Routes>
        <Route path="/*" element={<Post openSearchModal={props.openSearchModal}/>}/>
        <Route path="/matching/*" element={<Matching openSearchModal={props.openSearchModal}/>} />
        <Route path="/walk" element={<Walk openSearchModal={props.openSearchModal}/>} />
        <Route path="/profile/*" element={<Profile openSearchModal={props.openSearchModal}/>} />
        <Route path="/directmessage" element={<DirectMessage />} />
        <Route path="/notification" element={<Notification />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
      </Routes>
    </div>
  );
}
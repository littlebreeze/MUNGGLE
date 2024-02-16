import React from "react";
import "./Body.css";
import { Route, Routes } from "react-router-dom";
import Post from "../../pages/Main/PostPage";
import PostTest from "../../pages/Main/PostPageTest";
import Profile from "../../pages/Main/ProfilePage";
import Matching from "../../pages/Main/MatchingPage";
import Walk from "../../pages/Main/WalkPage";
import DirectMessage from "../../pages/Sub/DirectMessagePage";
import Notification from "../../pages/Sub/NotificationPage";
import Search from "../../pages/Sub/SearchPage";
import Login from "../../pages/Auth/LoginPage";
import SignUp from "../../pages/Auth/SignUpPage";
import APITest from "../../pages/Main/APITest";

export default function Body() {
  return (
    <div className="body-container-div">
      <Routes>
        <Route path="/*" element={<APITest />} />
        <Route path="/matching/*" element={<Matching />} />
        <Route path="/walk" element={<Walk />} />
        <Route path="/profile/*" element={<Profile />} />
        <Route path="/directmessage" element={<DirectMessage />} />
        <Route path="/notification" element={<Notification />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
      </Routes>
    </div>
  );
}
import React from "react";
import "../styles/css/layout/Body.css";
import { Route, Routes } from "react-router-dom";
import Post from "../pages/Main/PostPage";
import Profile from "../pages/Main/ProfilePage";
import Story from "../pages/Main/StoryPage";
import Walk from "../pages/Main/WalkPage";
import DirectMessage from "../pages/Sub/DirectMessagePage";
import Notification from "../pages/Sub/NotificationPage";
import Search from "../pages/Sub/SearchPage";
import Login from "../pages/Auth/LoginPage";
import SignUp from "../pages/Auth/SignUpPage";

export default function Body() {
  return (
    <div className="body-container-div">
      <Routes>
        <Route path="/" element={<Post />} />
        <Route path="/story" element={<Story />} />
        <Route path="/walk" element={<Walk />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/directmessage" element={<DirectMessage />} />
        <Route path="/notification" element={<Notification />} />
        <Route path="/search" element={<Search />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
      </Routes>
    </div>
  );
}
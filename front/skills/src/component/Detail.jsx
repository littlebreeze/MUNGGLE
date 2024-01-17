import React from "react";
import './Detail.css';
import { Navigate, useLocation, useNavigate } from 'react-router-dom';

export default function Detail() {
    const navigate = useNavigate();
    const location = useLocation();
    const detail = { ...location.state};
  return (
    <div>
        <button className="back-button" onClick={()=>{navigate(-1)}}>뒤로가기</button>
        디테일페이지
        <br/>
        {`날짜:${detail.walk_date}`}
        <br/>
        {`산책명:${detail.walk_name}`}
        <br/>
        {`강아지:${detail.dog_no}`}
        <br/>
        {`산책시간:${detail.duration}`}
        <br/>
        {`거리:${detail.distance}`}
        <br/>
        {`평점:${detail.rating}`}
        <br/>
        {`설명:${detail.describe}`}
        <br/>
    </div>
  );
}

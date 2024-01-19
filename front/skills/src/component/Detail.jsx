import React, { useEffect } from "react";
import './Detail.css';
import { useLocation, useNavigate } from 'react-router-dom';

export default function Detail() {

    const { kakao } = window;
    const navigate = useNavigate();
    const location = useLocation();
    const detail = { ...location.state};

    useEffect(() => {
        //맵 생성
        const container = document.getElementById("map");
        const options = {
            //위치
            center: new kakao.maps.LatLng(37, 127),
            level: 7
            };
        const map = new kakao.maps.Map(container, options);},[]);

    return (
        <>
        <div id="map" style={{
            //크기 : 30%
            width: "100%",
            height: "30%"
            }}>
        </div>
        <div>
            <button className="back-button" onClick={()=>{navigate(-1)}}>
                뒤로가기
            </button>

            <br/><br/>
            {`날짜:${detail.walk_date}`}
            <br/><br/>
            {`산책명:${detail.walk_name}`}
            <br/><br/>
            {`강아지:${detail.dog_no}`}
            <br/><br/>
            {`산책시간:${detail.duration}`}
            <br/><br/>
            {`거리:${detail.distance}`}
            <br/><br/>
            {`평점:${detail.rating}`}
            <br/><br/>
            {`설명:${detail.describe}`}
            <br/>

        </div>
        </>
    );
}

import React, { useEffect } from "react";
import './Map.css';
import walks from "./walks";
import {Link, BrowserRouter, Routes, Route } from "react-router-dom";
import Record from "./Record.jsx"
import Save from "./Save.jsx"
import Calender from "./Calender.jsx"
import Detail from "./Detail.jsx"

const { kakao } = window;

//가정
//유저 위치 -> 주변 산책로 JSON 반환(walks.json)
//유저ID -> 유저 이미지 url 반환(users.json)

export default function Map() {
  
  return (
    //핸드폰
    <div className="Phone">
      <div className="screen">
        <BrowserRouter>
          <div className="header"><b>멍글멍글</b></div>
            <div className="content">
            <Routes>
              <Route path="/" element={<Space/>}></Route>
              <Route path="/record" element={<Record/>}></Route>
              <Route path="/save" element={<Save/>}></Route>
              <Route path="/calender" element={<Calender/>}></Route>
              <Route path="/detail" element={<Detail/>}></Route>
            </Routes>
            </div>
        </BrowserRouter>
        <div className="footer"><b>버튼들</b></div>
      </div>
    </div>
  );
}

//메인페이지
function Space() {

  useEffect(() => {
    const container = document.getElementById("space");
    const options = {
    
    //이용자 현재 위치
    center: new kakao.maps.LatLng(37.54699, 127.09598),

    level: 3
    };
    const map = new kakao.maps.Map(container, options);

    const imgSrc = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Evaluar%C3%A1n_las_distintas_estrategias_para_ense%C3%B1ar_a_leer_en_los_establecimientos_subvencionados_chilenos.jpg/450px-Evaluar%C3%A1n_las_distintas_estrategias_para_ense%C3%B1ar_a_leer_en_los_establecimientos_subvencionados_chilenos.jpg";
    const imgSize = new kakao.maps.Size(30,30);
    const imgOption = {offset: new kakao.maps.Point(27,69)};

    const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize, imgOption);
    const markerPosition = new kakao.maps.LatLng(37.54699, 127.09598);

    const marker = new kakao.maps.Marker({
      position: markerPosition,
      image: markerImage
    });

    marker.setMap(map);

    kakao.maps.event.addListener(marker, 'click', function() {
      console.log("123");
});

  }, [])

  return (
    <div>
      <br/>
      메인페이지
      <div id="space" style={{
        width: "300px",
        height: "600px"
      }}></div>
      <Link to="/calender">
        <button className="calender-button">캘린더</button>
      </Link>
      <Link to="/record">
        <button className="walk-button">산책하기</button>
      </Link>
    </div>
  )
}

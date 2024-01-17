import React, { useEffect, useState, useRef } from "react";
import "./Record.css"
import { useNavigate } from "react-router-dom";
const { kakao } = window;

export default function Record() {
    const navigate = useNavigate();
    const [record, setRecord] = useState(false);
    const [lat, setLat] = useState(37.54699);
    const [lng, setLng] = useState(127.09598);
    const [num, setNum] = useState(0);
    const changeRecord = () => {
        setRecord(!record);
    };

    const latRef = useRef(lat);
    const lngRef = useRef(lng);

    const latUp = () => {
      setLat((prevLat) => {
        latRef.current = prevLat + 0.0002;
        return prevLat + 0.0002;
      });
    };

    const latDown = () => {
        setLat(lat-0.0002);
   
    };

    const lngUp = () => {
        setLng(lng+0.0002);

    };

    const lngDown = () => {
        setLng(lng-0.0002);

    };
    
    //랜더링 될때 한번
    useEffect(() => {
      const container = document.getElementById("space");
      const options = {
      
      //이용자 현재 위치
      center: new kakao.maps.LatLng(lat, lng),
  
      level: 3
      };
      const map = new kakao.maps.Map(container, options);
      const panTo = setInterval( () => {
        console.log(lat);
        const moveLatLon = new kakao.maps.LatLng(latRef.current,lngRef.current);
        map.panTo(moveLatLon);
      },3000);
      return () => {
        clearInterval(panTo);
        console.log(444);
      }
    }, []);

    
  
    return (
      <div>
        <button className="back-button" onClick={()=>{navigate(-1)}}>뒤로가기</button>
        <br/>
        기록페이지
        <div id="space" style={{
          width: "300px",
          height: "600px"
        }}></div>

        <div className="time-page">
            <div>0:00:00</div>
            <div style={{fontSize:"30px"}}>산책 거리: </div>
        </div>

        <div onClick={changeRecord}>
        {!record && <button className="start-button">시작</button>}
        {record && <button className="reset-button">리셋</button>}
        {record && <button className="save-button">저장</button>}
        </div>

        <div className="control">
            {`현재 위도 : ${lat}`}
            <br/>
            {`현재 경도 : ${lng}`}
            <br/>
            <button onClick={latUp}>위도+0.0002</button>
            <button onClick={latDown}>위도-0.0002</button>
            <button onClick={lngUp}>경도+0.0002</button>
            <button onClick={lngDown}>경도-0.0002</button>
        </div>
      </div>
    )
  }
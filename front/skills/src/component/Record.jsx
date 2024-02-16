import React, { useEffect, useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import "./Map2.css";
const { kakao } = window;
export default function Record() {
    const navigate = useNavigate();
    const [record, setRecord] = useState(false);
    const [lat, setLat] = useState(37.54699);
    const [lng, setLng] = useState(127.09598);
    const latRef = useRef(37.54699);
    const lngRef = useRef(127.09598);

    const changeRecord = () => {
        setRecord(!record);
    };

    //임의로 만든 컨트롤러(좌표 이동)
    const latUp = () => {
        setLat((prevLat) => {
            latRef.current = prevLat + 0.0002;
            return prevLat + 0.0002;
              });
        };

    const latDown = () => {
        setLat((prevLat) => {
            latRef.current = prevLat - 0.0002;
            return prevLat - 0.0002;
            });
        };

    const lngUp = () => {
        setLng((prevLng) => {
            lngRef.current = prevLng + 0.0002;
            return prevLng + 0.0002;
            });
        };

    const lngDown = () => {
        setLng((prevLng) => {
            lngRef.current = prevLng - 0.0002;
            return prevLng - 0.0002;
            });
        };
    
    //랜더링 될때 한번
    useEffect(() => {
        const container = document.getElementById("map");
        const options = {
            //이용자 현재 위치
            center: new kakao.maps.LatLng(lat, lng), 
            level: 3
            };
        const map = new kakao.maps.Map(container, options);
        const moveMap = setInterval( () => {
        //Main2와 동일한 의문점
        const moveLatLon = new kakao.maps.LatLng(latRef.current,lngRef.current);
        map.panTo(moveLatLon);
        },3000);//테스트할때 3000으로 바꾸기
        return () => {
            clearInterval(moveMap);
            }
        }, []);
  
    return (
      <div>
        <button className="back-button" onClick={()=>{navigate(-1)}}>뒤로가기</button>
        <br/>
        기록페이지
        <div id="map" style={{
          width: "100%",
          height: "80%"
        }}></div>

        <div className="time-page">
          {!record && <h4>산책 시간 0:00:00</h4>}
          {record && <Timer></Timer>}
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

  function Timer() {
    const [time, setTime] = useState(0);

    useEffect(() => {
      const clock = setInterval(() => {
        setTime(prev => prev+1);
    }, 1000);
    return () => clearInterval(clock);
    },[time]);

    return <h4>{`산책 시간 ${parseInt(time/3600)}:${parseInt(time/60).toString().padStart(2, '0')}:${(time%60).toString().padStart(2, '0')}`}</h4>;
  }
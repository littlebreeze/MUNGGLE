import React, { useEffect, useState, useRef } from "react";
import { Link } from "react-router-dom";
import './Map2.css';

export default function Main2() {

    const { kakao } = window;
    const [lat, setLat] = useState(37.54699);
    const [lng, setLng] = useState(127.09598);
    const latRef = useRef(37.54699);
    const lngRef = useRef(127.09598);

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
    //컨트롤러 끝

    useEffect(() => {

        //맵 생성
        const container = document.getElementById("map");
        const options = {
            //유저 위치
            center: new kakao.maps.LatLng(latRef.current, lngRef.current),
            level: 3
            };
        const map = new kakao.maps.Map(container, options);

        //이동 위치에 따라서 맵 이동
        const moveMap = setInterval(()=>{
            const moveLatLon = new kakao.maps.LatLng(latRef.current,lngRef.current);

            // const moveLatLon2 = new kakao.maps.LatLng(lat,lng);
            // lat입력시 클로저 적용되고, latRef.current 입력시 변화 반영 잘됨 -> 이유 집가서 알아오기
            map.panTo(moveLatLon);
        },3000);//3초
    
        // //아무렇게나 만들어본 마커
        // const imgSrc = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Evaluar%C3%A1n_las_distintas_estrategias_para_ense%C3%B1ar_a_leer_en_los_establecimientos_subvencionados_chilenos.jpg/450px-Evaluar%C3%A1n_las_distintas_estrategias_para_ense%C3%B1ar_a_leer_en_los_establecimientos_subvencionados_chilenos.jpg";
        // const imgSize = new kakao.maps.Size(30,30);
        // const imgOption = {offset: new kakao.maps.Point(27,69)};
        // const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize, imgOption);
        // const markerPosition = new kakao.maps.LatLng(37.54699, 127.09598);
        // const marker = new kakao.maps.Marker({
        //     position: markerPosition,
        //     image: markerImage
        // });
        // marker.setMap(map);
  
        // //마커 클릭시 이벤트
        // kakao.maps.event.addListener(marker, 'click', function() {
        //     console.log("123");
        // });

        return () => clearInterval(moveMap);
  
    }, [])
  
    return (
      <>
        <div id="map" style={{
            //크기 : 화면 가득
            width: "100%",
            height: "100%"
        }}>
        </div>
        {/*버튼 화면에 붙어있도록 디자인 수정하기*/}
        <Link to="/calender">
          <button className="calender-button">캘린더</button>
        </Link>
        <Link to="/record">
          <button className="walk-button">산책하기</button>
        </Link>
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
      </>
    )
  }
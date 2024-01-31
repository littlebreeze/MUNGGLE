import React, { useState, useEffect } from "react";
import "./WalkPage.css";

import iconCalender from "../../assets/icons/calender.png";

const { kakao } = window;

export default function Walk() {
  const [location, setLocation] = useState({});

  const success = (position) => {
    setLocation({
      latitude: position.coords.latitude,
      longitude: position.coords.longitude,
    });
    console.log(`More or less ${position.coords.accuracy} meters.`);
  };
  
  const error = (err) => {
    console.warn(`ERROR(${err.code}): ${err.message}`);
  }

  const options = {
    enableHighAccuracy: true,
    timeout: 27000,
    maximumAge: 30000,
  };

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(success, error, options);
    console.log(location)
  }, []);

  useEffect(() => {
    // 지도 그리기
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(location.latitude, location.longitude),
      level: 3
    };
    const map = new kakao.maps.Map(container, options);

    const moveMap = setInterval(() => {
      const moveLatLon = new kakao.maps.LatLng(location.latitude, location.longitude);

      map.panTo(moveLatLon);
    }, 3000)

    return () => {clearInterval(moveMap);
      clearInterval(getLocation);}

  }, [location]);

  setInterval(() => {
    navigator.geolocation.getCurrentPosition(success, error, options);
  }, 5000);

  const getLocation = () => {
    navigator.geolocation.getCurrentPosition(success, error, options);
  }

  return (
    <div className="walk-container-div">
      <div className="walk-page-map-div" id="map"></div>

      <div className="walk-calender-img-div">
        <img className="walk-calender-img" src={iconCalender} />
      </div>

      <div onClick={(e) => getLocation()} className="walk-start-button-div">
        <button className="walk-start-button">산책 갈까?</button>
      </div>
    </div>
  );
}

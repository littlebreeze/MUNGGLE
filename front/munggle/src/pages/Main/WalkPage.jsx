import React, { useState, useEffect } from "react";
import "./WalkPage.css";

export default function Walk() {
  const [x, setX] = useState(0)
  const [y, setY] = useState(0)

  const success = (pos) => {
    setX(pos.coords.latitude)
    setY(pos.coords.longitude)
    console.log(`More or less ${pos.coords.accuracy} meters.`);
  }
  
  const error = (err) => {
    console.warn(`ERROR(${err.code}): ${err.message}`);
  }

  const options = {
    enableHighAccuracy: true,
    timeout: 27000,
    maximumAge: 30000,
  };

  const changePosition = () => {
    navigator.geolocation.getCurrentPosition(success, error, options);
  }

  // navigator.geolocation.getCurrentPosition(success, error, options);
  // navigator.geolocation.watchPosition(success, error, options);

  return (
    <div className="walk-container-div">
    
      <div className="text-div">
        <button className="btn btn-secondary" onClick={changePosition} >위치 측정</button>
        <span className="text-span">위도 : {x}</span>
        <span className="text-span">경도 : {y}</span>
      </div>
    </div>
  );
}
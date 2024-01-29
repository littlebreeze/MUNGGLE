import React, { useState, useEffect } from "react";
import "./WalkPage.css";

import imgDog from "../../assets/dogLogo.png";
import imgSearch from "../../assets/icons/search.png";
import imgNotification from "../../assets/icons/notification.png";
import imgDm from "../../assets/icons/dm.png";
import { Link } from "react-router-dom";

import iconCalender from "../../assets/icons/calender.png";

const { kakao } = window;

export default function Walk(props) {
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

  // useEffect(() => {
  //   navigator.geolocation.getCurrentPosition(success, error, options);
  //   console.log(location)
  // }, []);

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

  // setInterval(() => {
  //   navigator.geolocation.getCurrentPosition(success, error, options);
  // }, 5000);

  const getLocation = () => {
    navigator.geolocation.getCurrentPosition(success, error, options);
  }

  return (
    <div className="walk-container-div">
      <div className="walk-nav-container-div">
        <div className="walk-nav-left-div">
          <Link to="/walk">
            <img src={imgDog} width={80} height={40} />
          </Link>
        </div>
        <div className="walk-nav-right-div">
          <div onClick={props.openSearchModal} className="walk-nav-right-div-left">
            {/* <img src={imgSearch} width={30} height={30} /> */}
            <img src={imgSearch} width={27} height={27} />
          </div>
          <div className="walk-nav-right-div-middle">
            <Link to="/notification">
              {/* <img src={imgNotification} width={30} height={30} /> */}
              <img src={imgNotification} width={34} height={34} />
            </Link>
          </div>
          <div className="walk-nav-right-div-right">
            <Link to="/directmessage">
              {/* <img src={imgDm} width={32} height={32} /> */}
              <img src={imgDm} width={32} height={32} />
            </Link>
          </div>
        </div>
      </div>
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

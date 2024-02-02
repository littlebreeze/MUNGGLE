import React, { useState, useEffect } from "react";
import "./WalkMain.css";

import imgDog from "../../assets/dogLogo.png";
import imgSearch from "../../assets/icons/search.png";
import imgNotification from "../../assets/icons/notification.png";
import imgDm from "../../assets/icons/dm.png";
import { Link } from "react-router-dom";

import iconCalender from "../../assets/icons/calender.png";

const { kakao } = window;

export default function Walk(props) {
  const [dayOfTheWeek, setDayoOfWeek] = useState('');
  const [monthDate, setMonthDate] = useState('');

  useEffect(() => {
    const currentDate = new Date();
    const days = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
    const dayOfWeek = days[currentDate.getDay()];
    setDayoOfWeek(dayOfWeek);

    // const month = currentDate.getMonth() + 1;
    const date = currentDate.getDate();
    setMonthDate(`${date}`);

    navigator.geolocation.getCurrentPosition(success, error, options);
  }, []);

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

  // setInterval(() => {
  //   navigator.geolocation.getCurrentPosition(success, error, options);
  // }, 5000);

  const getLocation = () => {
    navigator.geolocation.getCurrentPosition(success, error, options);
  }

  return (
    <div className="walk-main-container-div">
      <div className="walk-main-page-map-div" id="map"></div>

      <div className="walk-main-calender-div">
        <div className="walk-main-calender-div-top-div">{dayOfTheWeek}</div>
        {/* <div className="walk-calender-div-middle-div"></div> */}
        <div className="walk-main-calender-div-bottom-div">{monthDate}</div>
        {/* <img className="walk-calender-img" src={iconCalender} /> */}
      </div>

      <div onClick={(e) => getLocation()} className="walk-main-start-button-div">
        <button className="walk-main-start-button">산책갈까?</button>
      </div>
    </div>
  );
}

import React, { useEffect, useState, useRef } from "react";


export default function RecordOnly3(){

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


  return(<>
-    <button onClick={changePosition} >click</button>
  
    <div>
      위도 : {x}
      경도 : {y}
    </div>
  </>);
}

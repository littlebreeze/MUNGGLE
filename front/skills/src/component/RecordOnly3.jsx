import React, { useEffect, useState, useRef } from "react";


export default function RecordOnly3(){

  const [lat, setLat] = useState(0);
  const [lng, setLng] = useState(0);
  const [acc, setAcc] = useState(0);
  const [num, setNum] = useState(0);

  const plus = (() => {
    setNum(num+1);
  })

  const options = {
    enableHighAccuracy: true,
    timeout: 5000,
    maximumAge: 0,
  };

  function success(pos) {
    const crd = pos.coords;
    setLat(crd.latitude);
    setLng(crd.longitude)
    setAcc(crd.accuracy);
  
    console.log("Your current position is:");
    console.log(`Latitude : ${crd.latitude}`);
    console.log(`Longitude: ${crd.longitude}`);
    console.log(`More or less ${crd.accuracy} meters.`);
  }

  function error(err) {
    console.warn(`ERROR(${err.code}): ${err.message}`);
  }

  useEffect(() => {
    const requestLocationPermission = async () => {
      try {
        const permissionStatus = await navigator.permissions.query({
          name: 'geolocation',
        });
  
        if (permissionStatus.state === 'granted') {
          navigator.geolocation.getCurrentPosition(success, error, options);
        } else if (permissionStatus.state === 'prompt') {
          // You can show a prompt to the user here
        } else {
          console.warn('Geolocation permission denied');
        }
      } catch (error) {
        console.error('Error checking geolocation permission:', error);
      }
    };
  
    requestLocationPermission();
  }, []);
  
  return(<>
    <div>{`위도 : ${lat}`}</div>
    <div>{`경도 : ${lng}`}</div>
    <div>{`유효거리 : ${acc}`}</div>
    <div>{num}</div>
    <button onClick={plus}>123</button>
  </>)
}









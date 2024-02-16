import React, { useEffect, useState, useRef } from "react";



export default function RecordOnly() {

    /*geoLocation**********************************/
    const [location, setLocation] = useState({});

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        setLocation({
          latitude: position.coords.latitude,
          longitude: position.coords.longitude,
        });
      },
      (error) => console.log(error)
    );
  }, []);
     
    /*geoLocation**********************************/




    const { kakao } = window;
    const [lat, setLat] = useState(37.54699);
    const [lng, setLng] = useState(127.09598);
    const latRef = useRef(37.54699);
    const lngRef = useRef(127.09598);
    const [record, setRecord] = useState(false); //기록여부
    const recordRef = useRef(record);
    const drawingFlagRef = useRef(false);//그리는중인지여부
    const LineRef = useRef(null); //그리는선객체
    const pathRef = useRef([]);//좌표배열객체
    const distanceRef = useRef(0); //총거리
    const positionRef = useRef(null); //좌표객체

  


    //기록 중단
    const changeStop = () => {
        console.log("changeStop");
        if (drawingFlagRef.current) {
            // 마우스 클릭으로 그린 선의 좌표 배열을 얻어옵니다
            pathRef.current = LineRef.current.getPath();
        
            // 선을 구성하는 좌표의 개수가 2개 이상이면
            if (pathRef.current.length > 1) {
    
                distanceRef.current = Math.round(LineRef.current.getLength()); // 선의 총 거리를 계산합니다
                console.log(`path: ${LineRef.current.getPath()}`);
                console.log(`distance:${distanceRef.current}`);
                deleteClickLine();
            } else {
                // 선을 구성하는 좌표의 개수가 1개 이하이면 
                // 지도에 표시되고 있는 선과 정보들을 지도에서 제거합니다.
                deleteClickLine();
            }
            
            // 상태를 false로, 그리지 않고 있는 상태로 변경합니다
            drawingFlagRef.current=false;          
        } 
    };

    const changeRecord = () => {
        setRecord(!record);
        recordRef.current = !recordRef.current;
    };

    //그려져있는 선 제거
    function deleteClickLine() {
        console.log("deleteClickLine")
        if (LineRef.current) {
            LineRef.current.setMap(null);
            LineRef.current = null;    
        }
    }

    useEffect(() => {

        //맵 생성
        const container = document.getElementById("map");
        const options = {
            //유저 위치
            center: new kakao.maps.LatLng(location.latitude===undefined?latRef.current:location.latitude,location.longitude===undefined?latRef.current:location.longitude),
            level: 3
            };
        const map = new kakao.maps.Map(container, options);

        // 이동 위치에 따라서 맵 이동
        const moveMap = setInterval(()=>{
            console.log(location.latitude)
            const moveLatLon = new kakao.maps.LatLng(location.latitude===undefined?latRef.current:location.latitude,location.longitude===undefined?latRef.current:location.longitude);

            // const moveLatLon2 = new kakao.maps.LatLng(lat,lng);
            map.panTo(moveLatLon);
        },1000);//3초

        const drawLine = setInterval( () => {
            console.log("drawLine");

            //Main2와 같은 의문점
            if(recordRef.current){
                positionRef.current = new kakao.maps.LatLng(location.latitude===undefined?latRef.current:location.latitude,location.longitude===undefined?latRef.current:location.longitude);

                // 지도 클릭이벤트가 발생했는데 선을 그리고있는 상태가 아니면
                if (!drawingFlagRef.current) {
                    console.log("flagFalse");
                        // 상태를 true로, 선이 그리고있는 상태로 변경합니다
                    drawingFlagRef.current = true;            
                        // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
                    deleteClickLine();
                    
                        // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
                    LineRef.current = new kakao.maps.Polyline({
                        map: map, // 선을 표시할 지도입니다 
                        path: [positionRef.current], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
                        strokeWeight: 10, // 선의 두께입니다 
                        strokeColor: '#db4040', // 선의 색깔입니다
                        strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
                        strokeStyle: 'solid' // 선의 스타일입니다
                        });
                    }
                else{ 
                            
                    console.log("flagTrue");
                        // 선이 그려지고 있는 상태이면

                        // 그려지고 있는 선의 좌표 배열을 얻어옵니다
                    pathRef.current = LineRef.current.getPath();

                        // 좌표 배열에 클릭한 위치를 추가합니다
                    pathRef.current.push(positionRef.current);
                        
                        // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
                    LineRef.current.setPath(pathRef.current);
                    }
                }
            },3000); 

        return () => {clearInterval(moveMap);
                    clearInterval(drawLine);}
  
    }, [location]);

    

  return (
    <>
        <div id="map" style={{//임의로 정한 가로 세로
          width: "400px",
          height: "400px"
        }}></div>
        <div style={{
            width: "400px",
            height: "200px",
            backgroundColor: "red"
        }}>
            <button className="back-button">뒤로가기(x)</button>

            <div className="time-page">
            {!record && <div>산책 시간 0:00:00</div>}
            {record && <Timer/>}
            <div>위도 : {location.latitude}</div>
            <div>경도 : {location.longitude}</div>
            </div>

            <div onClick={changeRecord}>
            {!record && <button className="start-button">시작</button>}
            {record && <button onClick={changeStop} className="reset-button">리셋</button>}
            {record && <button className="save-button">저장(x)</button>}
            </div>

        </div>
      </>
  );
}

function Timer() {
    const [time, setTime] = useState(0);

    useEffect(() => {
        const clock = setInterval(() => {
            setTime(prev => prev+1);
        }, 1000);
        return () => clearInterval(clock);
    },[time]);

    return <div>{`산책 시간 ${parseInt(time/3600)}:${parseInt(time/60).toString().padStart(2, '0')}:${(time%60).toString().padStart(2, '0')}`}</div>;
}

/***********************************************geoLocation*/

/***********************************************geoLocation*/
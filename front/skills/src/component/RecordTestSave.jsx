//망했을때 복구용
import React, { useEffect, useState, useRef } from "react";
import "./Record.css"
import { useNavigate } from "react-router-dom";
const { kakao } = window;

export default function RecordTest() {
    const navigate = useNavigate();
    const [record, setRecord] = useState(false);
    const [lat, setLat] = useState(37.54699);
    const [lng, setLng] = useState(127.09598);
    const [stop, setStop] = useState(0);

    const changeStop = () => {
        setStop((prev) => {return 1 - prev;})
    };

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
      const container = document.getElementById("space");
      const options = {
      
      //이용자 현재 위치
        center: new kakao.maps.LatLng(lat, lng),
  
        level: 3
      };
      const map = new kakao.maps.Map(container, options);

      ///////////////////////////

      var drawingFlag = false; // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
      var clickLine // 마우스로 클릭한 좌표로 그려질 선 객체입니다, 중요 정보 다있다

      // 지도에 클릭 이벤트를 등록합니다
      // 지도를 클릭하면 선 그리기가 시작됩니다 그려진 선이 있으면 지우고 다시 그립니다
      //setinterval로 바꿀예정
      kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

        // 마우스로 클릭한 위치입니다
        var clickPosition = mouseEvent.latLng;

        // 지도 클릭이벤트가 발생했는데 선을 그리고있는 상태가 아니면
        if (!drawingFlag) {

        // 상태를 true로, 선이 그리고있는 상태로 변경합니다
            drawingFlag = true;
        
        // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
            deleteClickLine();
    
        // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
            clickLine = new kakao.maps.Polyline({
                map: map, // 선을 표시할 지도입니다 
                path: [clickPosition], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
                strokeWeight: 10, // 선의 두께입니다 
                strokeColor: '#db4040', // 선의 색깔입니다
                strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
                strokeStyle: 'solid' // 선의 스타일입니다
            });
            
        }
        else { // 선이 그려지고 있는 상태이면

        // 그려지고 있는 선의 좌표 배열을 얻어옵니다
        var path = clickLine.getPath();

        // 좌표 배열에 클릭한 위치를 추가합니다
        path.push(clickPosition);
        
        // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
        clickLine.setPath(path);
        }
    });          



// 지도에 마우스 오른쪽 클릭 이벤트를 등록합니다
// 선을 그리고있는 상태에서 마우스 오른쪽 클릭 이벤트가 발생하면 선 그리기를 종료합니다
// 리셋버튼 누른 경우로 바꿀 예정
// kakao.maps.event.addListener(map, 'rightclick', function (mouseEvent) {

//     // 지도 오른쪽 클릭 이벤트가 발생했는데 선을 그리고있는 상태이면
//     if (drawingFlag) {
        
//         // 마우스 클릭으로 그린 선의 좌표 배열을 얻어옵니다
//         var path = clickLine.getPath();
    
//         // 선을 구성하는 좌표의 개수가 2개 이상이면
//         if (path.length > 1) {

//             var distance = Math.round(clickLine.getLength()); // 선의 총 거리를 계산합니다
//             console.log(distance);
//         } else {

//             // 선을 구성하는 좌표의 개수가 1개 이하이면 
//             // 지도에 표시되고 있는 선과 정보들을 지도에서 제거합니다.
//             deleteClickLine();
//         }
        
//         // 상태를 false로, 그리지 않고 있는 상태로 변경합니다
//         drawingFlag = false;          
//     }  
// });    

// 클릭으로 그려진 선을 지도에서 제거하는 함수입니다
    function deleteClickLine() {
        if (clickLine) {
            clickLine.setMap(null);    
            clickLine = null;        
        }
    }
      ///////////////////////////////

    const panTo = setInterval( () => {
        const moveLatLon = new kakao.maps.LatLng(latRef.current,lngRef.current);
        map.panTo(moveLatLon);
        },300000);//테스트할때 3000으로 바꾸기
        return () => {
            clearInterval(panTo);
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
          {!record && <h4>산책 시간 0:00:00</h4>}
          {record && <Timer></Timer>}
        </div>

        <div onClick={changeRecord}>
        {!record && <button className="start-button">시작</button>}
        {record && <button onClick={changeStop} className="reset-button">리셋</button>}
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
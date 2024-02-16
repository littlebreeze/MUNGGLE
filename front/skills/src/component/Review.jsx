import React from "react";
import { useLocation, useNavigate } from 'react-router-dom';
import './Map2.css';

export default function Review() {
    const navigate = useNavigate();
    const location = useLocation();
    const lat = JSON.stringify(location.state.lat);
    const LaArray = location.state.lat.map((item)=>{return item.La});
    const MaArray = location.state.lat.map((item)=>{return item.Ma});

    return (
        <>
            <button className="back-button" onClick={()=>{navigate(-1)}}>
                뒤로가기
            </button>
            <br/>
            {`총 거리 : ${location.state.distance}m`}
            <br/>
            <div>
                {LaArray.map((item, index) => (
                    <div>
                    <div>{`${index}번째 좌표 : `}</div>
                    <span key={index}>
                    {`La : ${item} `}
                    {`Ma : ${MaArray[index]}`}
                    <br/>
                    </span>
                    </div>
                ))}
            </div>
            산책이름 입력 : <input></input><br/>
            짧은 설명 : <input></input><br/>
            별점 입력 : <input></input><br/>
        </>
    );
}

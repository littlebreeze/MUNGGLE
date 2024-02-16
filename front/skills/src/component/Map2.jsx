import React from "react";
import './Map2.css';
import Main2 from "./Main2.jsx";
import Calender from "./Calender.jsx"
import Detail from "./Detail.jsx"
import Record from "./RecordTest.jsx"
import Review from "./Review.jsx"
import { BrowserRouter, Routes, Route } from "react-router-dom";

export default function Map2() {
  return (
    <>
    {/*핸드폰*/}
    <div className="Phone">
        {/*스크린(헤더+컨텐츠+푸터)*/}
        <div className="screen">
            {/*헤더*/}
            <div className="header">
                <b>멍글멍글</b>
            </div>
            {/*컨텐츠*/}
            <div className="content">
                <BrowserRouter>
                    <Routes>
                        <Route path="/" element={<Main2/>}></Route>
                        <Route path="/calender" element={<Calender/>}></Route>
                        <Route path="/detail" element={<Detail/>}></Route>
                        <Route path="/record" element={<Record/>}></Route>
                        <Route path="/review" element={<Review/>}></Route>
                    </Routes>      
                </BrowserRouter>
            </div>
            {/*푸터*/}
            <div className="footer">
                <b>버튼들</b>
            </div>
      </div>
    </div>
    </>
  );
}

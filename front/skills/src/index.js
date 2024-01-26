import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ReactDOM from 'react-dom/client';
import './index.css';
import Geo from './component/UseGeoLocation.jsx';
import RecordOnly from './component/RecordOnly.jsx';
import RecordOnly2 from './component/RecordOnly2.jsx';
import RecordOnly3 from './component/RecordOnly3.jsx';
import reportWebVitals from './reportWebVitals';
import Map2 from './component/Map2.jsx';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

    <RecordOnly3 />
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

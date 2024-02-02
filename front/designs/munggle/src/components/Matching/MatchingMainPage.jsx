import React, { useState } from "react";
import "./MatchingMainPage.css";
import { Link } from "react-router-dom";

import imgNose from "../../assets/icons/nose.png";
import iconSwitchOn from "../../assets/icons/matchingSwitchOn.png";
import iconSwitchOff from "../../assets/icons/matchingSwitchOff.png";
import iconSwitchToggle from "../../assets/icons/matchingSwitchToggle.gif";
import iconGo from "../../assets/icons/go.png";
import iconInfoEdit from "../../assets/icons/infoEdit.png";
import imgWink from "../../assets/icons/wink.png";
import imgNormal from "../../assets/icons/normal.png";

export default function MatchingMainPage () {
  const [isMatching, setIsMatching] = useState(false)

  const handleIsMatching = () => {
    setIsMatching(!isMatching)
  }

  return (
    <div className="matching-main-container-div">
      <div className="matching-main-top-div">

      </div>
      <div className="matching-main-bottom-div">

      </div>
      <div className="matching-main-title-div">
        <p>킁킁</p>
      </div>
      <div className="matching-main-background-img-div">
        {/* <img className="matching-main-background-img" src={imgDogs} /> */}
        <img className="matching-main-background-img" src={imgWink} />
      </div>

      <div onClick={handleIsMatching} className="matching-main-toggle-button-div">
        <img className="matching-main-toggle-button" src={isMatching ? iconSwitchOn : iconSwitchOff} />
        {/* <img className="matching-toggle-button" src={iconSwitchToggle} /> */}
      </div>
      
      <div className="matching-main-start-button-div">
        <Link to="/matching/start">
          {/* <img className="matching-main-start-button" src={iconGo}/> */}
          <img className="matching-main-start-button" src={imgNose}/>
        </Link> 
      </div>

      <div className="matching-main-info-edit-button-div">
        <Link to="/matching/edit">
          <img className="matching-main-info-edit-button" src={iconInfoEdit} />
        </Link> 
      </div>
    </div>
  );
}
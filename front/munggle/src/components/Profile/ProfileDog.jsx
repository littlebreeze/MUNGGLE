import React from "react";
import "./ProfileDog.css";

export default function ProfileDog(props) {
  const dogs = props.dogs
  
  const dogList = dogs.
      map((dog, index) => {
        return (
          <div key={index} className="profile-dog-list-container-div">
            <div className="profile-dog-list-top-div">
              <img className="profile-dog-list-top-img" src={dog.img} />
            </div>
            <div className="profile-dog-list-bottom-div">
              <div className="profile-dog-list-bottom-div-left-div">
                <span>이름 : {dog.name}</span>
                <span>생년월일 : {dog.birthDate}</span>
                <span>성별 : {dog.gender}</span>
              </div>
              <div className="profile-dog-list-bottom-div-right-div">
                <span>견종 : {dog.kind}</span>
                <span>몸무게 : {dog.weight} kg</span>
              </div>
            </div>
          </div>
        );
      })

  return (
    <div className="profile-dog-container-div">
      {dogList}
    </div>
  );
}
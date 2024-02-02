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
              <div className="profile-dog-list-bottom-div-top-div fw-bold fs-4">
                {dog.name}
              </div>
              <div className="profile-dog-list-bottom-div-bottom-div">
                <div className="profile-dog-list-bottom-div-bottom-div-left-div">
                    <div>생일 : {dog.birthDate}</div>
                    <div>성별 : {dog.gender}</div>
                </div>
                <div className="profile-dog-list-bottom-div-bottom-div-right-div">
                  <div>견종 : {dog.kind}</div>
                  <div>무게 : {dog.weight} kg</div>
                </div>
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

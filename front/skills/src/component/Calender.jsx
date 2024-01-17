import React from "react";
import { Link, useNavigate } from "react-router-dom";
import mywalks from "./mywalks.json";

export default function Calender() {
    //라우터 이동할때 데이터 들고가게 한다
    const navigate = useNavigate();
  return (
    <div>
        <Link to="/">
        <button className="calender-button">메인으로</button>
      </Link>
      <br></br>
      <br></br>
        산책기록
        <ol>
            {mywalks.walks.map((walk) => (
                <li style={{marginBottom: "10px"}}>
                    <ul>
                        <li>{`날짜 : ${walk.walk_date}`}</li>
                        <li>{`이름 : ${walk.walk_name}`}</li>
                        <button
                        onClick={() =>
                        navigate('/detail',{
                            state: {
                                walk_date: walk.walk_date,
                                walk_name: walk.walk_name,
                                dog_no: walk.dog_no,
                                duration: walk.duration,
                                distance: walk.distance,
                                rating: walk.rating,
                                describe: walk.describe,
                                is_deleted: walk.is_deleted,
                                location: walk.location
                            }
                        })}
                        >상세정보</button>
                    </ul>
                </li>
            ))}
        </ol>
    </div>
  );
}

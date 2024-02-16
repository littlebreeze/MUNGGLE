import React from "react";
import { Link, useNavigate } from "react-router-dom";
import mywalks from "./mywalks.json";

export default function Calender() {

    //Detail 페이지 이동할때 데이터 들고감
    const navigate = useNavigate();

    return (
        <div>
            {/*뒤로가기 버튼*/}
            <Link to="/">
                <button className="calender-button">메인으로</button>
            </Link>
            <ol>
                {/*데이터 하나씩 분리*/}
                {mywalks.walks.map((walk) => (
                    <li style={{marginBottom: "10px"}}>
                        <ul>
                            <li>{`날짜 : ${walk.walk_date}`}</li>
                            <li>{`이름 : ${walk.walk_name}`}</li>
                            {/*상세정보 버튼*/}
                            <button
                                onClick={() => navigate('/detail',{
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
                                    })}>

                                상세정보

                            </button>
                        </ul>
                    </li>
                ))}
            </ol>
        </div>
    );
}

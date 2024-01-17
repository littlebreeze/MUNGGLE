import React from "react";
import walks from "./component/walks.json";
import users from "./component/users.json";

export default function Test() {
  
  return (
    <div>
        {walks.walks.map((walk) => (
            <div>
                <div>{walk.walk_no}</div>
                <div>{walk.walk_name}</div>
            </div>

        ))}
    </div>
  );
}


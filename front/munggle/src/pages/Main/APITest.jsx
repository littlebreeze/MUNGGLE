import React, { useState, useEffect } from "react";
import axios from "axios";

export default function APITest() {

  const test = axios.get("https://koreanjson.com/posts/1", {
        username: "zx3024@ssafy.com",
        password: "12345678"
      })
      .then((res) => {
        console.log(res.data.content)
      })
      .catch((err) => {
          console.log(err);
        })
      
  return(<>123</>)

}

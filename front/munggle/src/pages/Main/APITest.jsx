import React, { useEffect, useRef } from "react";
import axios from "axios";

export default function APITest() {

  const tokenRef = useRef();

  useEffect(()=>{
    async function login() {
      try {
        const response = await axios.post('http://i10a410.p.ssafy.io:8080/login',
        {
          username: "az3024@ssafy.com",
          password: "11111111",
        },
        {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        }
        );
        console.log(response);
        console.log(response.headers.authorization);
        tokenRef.current = response.headers.authorization;
      } catch (error) {
        console.error(error);
      }
    };
    login();
  },[]);

    async function postData() {
      try {
        const jsonData = {postTitle: "제목1",
          postContent:"내용1",
          isPrivate: false,
          hashtags: ["태그1"]};

        const formdata = new FormData();
        formdata.append('dto', new Blob([JSON.stringify(jsonData)],{type:"application/json"}))
        console.log(formdata.get('dto'));
        const response = await axios.post('http://i10a410.p.ssafy.io:8080/posts',
          formdata
      ,
        {
          headers: {
            "Content-Type": "multipart/form-data",
            "Authorization": tokenRef.current,
          },
        }
        );
        console.log(formdata.get('dto'));
        console.log(response);
      } catch (error) {
        console.error(error);
        
      }
    };

      
  return(<>
    <button onClick={postData}>글 생성 버튼</button>
  </>)

}

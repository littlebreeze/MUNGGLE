import React, { useState } from "react";
import "./SearchModal.css";
import Modal from "react-modal";

import SearchPostComponent from "./SearchPostComponent";
import SearchAccountComponent from "./SearchAccountComponent";
import SearchTagComponent from "./SearchTagComponent";

import iconClose from "../../../assets/icons/close1.png";
import iconSearch from "../../../assets/icons/search.png";
import iconLine from "../../../assets/icons/line1.png";

import imgProfile1 from "../../../assets/example/profile1.jpg";
import imgProfile2 from "../../../assets/example/profile2.jpg";
import imgProfile3 from "../../../assets/example/profile3.jpg";
import imgProfile4 from "../../../assets/example/profile4.jpg";
import imgProfile5 from "../../../assets/example/profile5.jpg";
import imgProfile6 from "../../../assets/example/profile6.jpg";
import imgProfile7 from "../../../assets/example/profile.png";

import imgPost1 from "../../../assets/example/dog1.jpg"
import imgPost2 from "../../../assets/example/dog2.jpg"
import imgPost3 from "../../../assets/example/dog3.jpg"
import imgPost4 from "../../../assets/example/dog4.jpg"
import imgPost5 from "../../../assets/example/dog5.jpg"
import imgPost6 from "../../../assets/example/dog6.jpg"
import imgPost7 from "../../../assets/example/dog7.jpg"
import imgPost8 from "../../../assets/example/dog8.jpg"
import imgPost9 from "../../../assets/example/dog9.jpg"
import imgPost10 from "../../../assets/example/dog10.jpg"

const customStyles = {
  overlay: {
    position: "fixed",
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    backgroundColor: " rgba(0, 0, 0, 0.4)",
    width: "400px",
    height: "800px",
    borderRadius: "40px",
    zIndex: "10",
  },
  content: {
    width: "360px",
    height: "620px",
    zIndex: "150",
    position: "absolute",
    top: "390px",
    left: "200px",
    transform: "translate(-50%, -50%)",
    borderRadius: "10px",
    boxShadow: "2px 2px 2px rgba(0, 0, 0, 0.25)",
    backgroundColor: "white",
    justifyContent: "center",
    overflow: "auto",
  },
};

Modal.setAppElement('#root');

const postList = [
  {
    id: 1,
    user : {
      imgProfile: imgProfile1,
      name: 'megar0829',
      isFollow: false,
    },
    imgPost: imgPost1,
    title: "산책하는 댕댕이",
    content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
    createdAt: "2024-01-17",
    tagList: [
      "산책", "코기", "신났네",
    ],
  },
  {
    id: 2,
    user : {
      imgProfile: imgProfile2,
      name: 'megar0829',
      isFollow: false,
    },
    imgPost: imgPost2,
    title: "애기랑 오랜만에 공원",
    content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
    createdAt: "2024-01-17",
    tagList: [
      "산책", "아구", "힘들어?",
    ],
  },
  {
    id: 3,
    user : {
      imgProfile: imgProfile3,
      name: 'megar0829',
      isFollow: false,
    },
    imgPost: imgPost3,
    title: "귀여워라",
    content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
    createdAt: "2024-01-17",
    tagList: [
      "신남", "댕글댕글",
    ],
  },
  {
    id: 4,
    user : {
      imgProfile: imgProfile4,
      name: 'megar0829',
      isFollow: false,
    },
    imgPost: imgPost4,
    title: "산책하는 댕댕이",
    content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
    createdAt: "2024-01-17",
    tagList: [
      "산책", "댕댕이", "신났네",
    ],
  },
  {
    id: 5,
    user : {
      imgProfile: imgProfile5,
      name: 'megar0829',
      isFollow: false,
    },
    imgPost: imgPost5,
    title: "산책하는 댕댕이",
    content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
    createdAt: "2024-01-17",
    tagList: [
      "산책", "댕댕이", "신났네",
    ],
  },
  {
    id: 6,
    user : {
      imgProfile: imgProfile6,
      name: 'megar0829',
      isFollow: false,
    },
    imgPost: imgPost6,
    title: "산책하는 댕댕이",
    content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
    createdAt: "2024-01-17",
    tagList: [
      "산책", "댕댕이", "신났네",
    ],
  },
  {
    id: 7,
    user : {
      imgProfile: imgProfile1,
      name: 'megar0829',
      isFollow: false,
    },
    imgPost: imgPost7,
    title: "산책하는 댕댕이",
    content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
    createdAt: "2024-01-17",
    tagList: [
      "산책", "댕댕이", "신났네",
    ],
  },
  {
    id: 8,
    user : {
      imgProfile: imgProfile2,
      name: 'megar0829',
      isFollow: false,
    },
    imgPost: imgPost8,
    title: "산책하는 댕댕이",
    content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
    createdAt: "2024-01-17",
    tagList: [
      "산책", "댕댕이", "신났네",
    ],
  },
  {
    id: 9,
    user : {
      imgProfile: imgProfile3,
      name: 'megar0829',
      isFollow: false,
    },
    imgPost: imgPost9,
    title: "산책하는 댕댕이",
    content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
    createdAt: "2024-01-17",
    tagList: [
      "산책", "댕댕이", "신났네",
    ],
  },
  {
    id: 10,
    user : {
      imgProfile: imgProfile4,
      name: 'megar0829',
      isFollow: false,
    },
    imgPost: imgPost10,
    title: "산책하는 댕댕이",
    content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
    createdAt: "2024-01-17",
    tagList: [
      "산책", "댕댕이", "신났네",
    ],
  },
]

export default function SearchModal (props) {
  const [componentType, setComponentType] = useState("post")

  const [searchValue, setSearchValue] = useState("")

  const showComponent = () => {
    if (componentType === "post") {
      return <SearchPostComponent postList={postList} searchValue={searchValue} />
    } else if (componentType === "account") {
      return <SearchAccountComponent postList={postList} searchValue={searchValue} />
    } else if (componentType === "tag") {
      return <SearchTagComponent postList={postList} searchValue={searchValue} />
    }
  }

  const handleSearchValue = (e) => {
    setSearchValue(e.target.value)
  }

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') {
      handleSubmit();
    }
  };

  const handleSubmit = () => {
    console.log(searchValue)
  }

  const component = showComponent()

  return (
    <Modal
      isOpen={props.isOpen}
      onRequestClose={props.closeModal}
      shouldCloseOnOverlayClick={false}
      style={customStyles}
      contentLabel="Search Modal"
    >
      <div className="search-modal-container">
        <div className="search-modal-search-div">
          <label className="search-modal-search-left-div" htmlFor="search">
            <img className="search-modal-search-left-img" src={iconSearch} />
          </label>
          <div className="search-modal-search-middle-div">
            <input onKeyDown={handleKeyDown} onChange={handleSearchValue} className="search-modal-search-middle-input" type="search" name="" id="search" />
          </div>
          <div onClick={props.closeModal} className="search-modal-search-right-div">
            <img className="search-modal-search-right-img" src={iconClose} />
          </div>
        </div>
        <div className="search-modal-nav-div">
          <div onClick={(e) => setComponentType("post")} className="search-modal-nav-left-div">
            <span className="search-modal-nav-left-span">게시물</span>
          </div>
          <div className="search-modal-nav-line-div">
            <img className="search-modal-nav-line-img" src={iconLine}/>
          </div>
          <div onClick={(e) => setComponentType("account")} className="search-modal-nav-middle-div">
            <span className="search-modal-nav-middle-span">계정</span>
          </div>
          <div className="search-modal-nav-line-div">
            <img className="search-modal-nav-line-img" src={iconLine}/>
          </div>  
          <div onClick={(e) => setComponentType("tag")} className="search-modal-nav-right-div">
            <span className="search-modal-nav-right-span">태그</span>
          </div>
        </div>
          
        { component }
      </div>
    </Modal>
  );
}
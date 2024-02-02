import React from "react";
import "./SearchPostComponent.css";
import SearchPostDetailComponent from "./SearchPostDetailComponent";

export default function SearchPostComponent (props) {
  const searchValue = props.searchValue
  
  const postList = props.postList.filter((post) => {
    return (searchValue != '') && post.title.includes(searchValue)
  }).map((post) => {
    return <SearchPostDetailComponent key={post.id} postData={post} />
  })

  return (
    <div className="search-post-container">
      {postList}
    </div>
  );
}
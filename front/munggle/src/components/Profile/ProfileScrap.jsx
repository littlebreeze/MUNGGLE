import React from "react";
import "./ProfileScrap.css";
import { ImageList, ImageListItem } from '@mui/material';

export default function ProfileScrap(props) {
  const scrapList = props.scrapList

  return (
    <div>
      <ImageList variant="masonry" cols={3} gap={8}>
        {scrapList.map((item) => (
          <ImageListItem key={item.img}>
            <img
              srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
              src={`${item.img}?w=248&fit=crop&auto=format`}
              alt={item.title}
              loading="lazy"
            />
          </ImageListItem>
        ))}
      </ImageList>
    </div>
  );
}
import React, { useState } from 'react';
import Button from '@mui/material/Button';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';

import PhotoCamera from '@mui/icons-material/PhotoCamera';
import ClearIcon from '@mui/icons-material/Clear';
import Grid from '@mui/material/Grid';

import BackButton from '../../assets/icons/back-button.png';

export default function CreateModalTest(props) {

  const [selectedImages, setSelectedImages] = useState([]);

  const handleImageUpload = (e) => {
    const files = e.target.files;

    // 이미 선택된 이미지와 새로 선택한 이미지 합치기
    const updatedImages = [...selectedImages, ...files];

    // 선택한 이미지 업데이트
    setSelectedImages(updatedImages);
  };

  const handleRemoveImage = (index) => {
    // 선택한 이미지 중에서 index에 해당하는 이미지 제거
    const updatedImages = [...selectedImages];
    updatedImages.splice(index, 1);

    // 선택한 이미지 업데이트
    setSelectedImages(updatedImages);
  };

  const renderPreview = () => {
    return selectedImages.map((image, index) => (
      <Grid key={index} item xs={5}>
      <div style={{ position: 'relative', marginBottom: 8 }}>
          <img
            src={URL.createObjectURL(image)}
            alt={`preview-${index}`}
            style={{ width: '100%', height: 'auto', borderRadius: 8 }}
          />
          <IconButton
            style={{ position: 'absolute', top: 0, right: 0 }}
            onClick={() => handleRemoveImage(index)}
          >
            <ClearIcon />
          </IconButton>
        </div>
      </Grid>
    ));
  };
  
  return(<>
  <AppBar sx={{ position: 'relative', backgroundColor: 'white' }}>
          <Toolbar>
            <IconButton
              edge="start"
              color="inherit"
              onClick={props.onClose}
              aria-label="close"
            >
              <img width={30} src={BackButton} />
            </IconButton>
            <Typography sx={{ textAlign: 'center', flexGrow: 1, color:'black' }} variant="h6" component="div">
              게시물 생성
            </Typography>
          </Toolbar>
        </AppBar>

        {selectedImages.length === 0 ? (
         <div style={{height:200}}>
          <br></br>
        <Typography variant="body2">사진을 추가해주세요.</Typography>
        </div>
      ) : (
        <Grid container wrap='nowrap'>
          {renderPreview()}
        </Grid>
      )}

<div>
      <input
        accept="image/*"
        style={{ display: 'none' }}
        id="image-upload-input"
        type="file"
        multiple
        onChange={handleImageUpload}
      />
      <label htmlFor="image-upload-input">
        <Button
          variant="contained"
          component="span"
          startIcon={<PhotoCamera />}
        >
        </Button>
      </label>

  
    </div>

        <br/>
        <TextField
          label="제목"
          helperText="1~30자"
        />

        <TextField
          label="내용"
          helperText="1~300자"
          multiline
          rows={4}
        />
        <TextField
          label="태그"
          helperText="1~30자"
        />
        <Button autoFocus color="inherit" onClick={props.onClose}>
              저장
            </Button>
  
  </>)
}
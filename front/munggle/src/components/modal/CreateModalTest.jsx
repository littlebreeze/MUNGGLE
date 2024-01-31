import React, { useState } from 'react';
import Button from '@mui/material/Button';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import CropTest from './CropTest.jsx';
import Dialog from '@mui/material/Dialog';

import PhotoCamera from '@mui/icons-material/PhotoCamera';
import ClearIcon from '@mui/icons-material/Clear';
import Grid from '@mui/material/Grid';

import BackButton from '../../assets/icons/back-button.png';
import './CreateModalTest.css';

export default function CreateModalTest(props) {

  const [selectedImages, setSelectedImages] = useState([]);

  const [cropModalIsOpen, setCropModalIsOpen] = useState(false);

  const openCropPage = (e) => {
    const files = e.target.files;
    const fileType = files[0].type;
    if (fileType.startsWith('image/')) {
      console.log('이미지를 받았다');
      setCropModalIsOpen(true);
    } else if (fileType.startsWith('video/')) {
      console.log('비디오를 받았다');
      setCropModalIsOpen(true);
    } else {
      window.alert('이미지 또는 비디오를 올리시오')
    }
  };

  function closeCropModal() {
    setCropModalIsOpen(false);
  };

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
      <div key={index} style={{ position: 'relative', marginBottom: 8 }}>
          <img
            src={URL.createObjectURL(image)}
            alt={`preview-${index}`}
            style={{ height: '200px', borderRadius: 8 }}
          />
          <IconButton
            style={{ position: 'absolute', top: 0, right: 0 }}
            onClick={() => handleRemoveImage(index)}
          >
            <ClearIcon />
          </IconButton>
        </div>
    ));
  };
  
  return(<>
  <Dialog
        open={cropModalIsOpen}
        onClose={closeCropModal}
      >
        <CropTest
          onClose={closeCropModal}
        />

      </Dialog>
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
         <div>
          <br></br>
        <Typography variant="body2" height={160}>사진을 추가해주세요.</Typography>
        </div>
      ) : (
        <div className='imageBox'>
          {renderPreview()}
        </div>
      )}

    <div style={{
      textAlign: 'right'
    }}>
      <input
        accept="image/*"
        style={{ display: 'none' }}
        id="image-upload-input"
        type="file"
        multiple
        // onChange={handleImageUpload}
        onChange={openCropPage}
        
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
 
        <div style={{
          textAlign: 'center'
        }}>
        <Button variant="contained" onClick={props.onClose}>
              저장
            </Button>
            </div>
   
  
  </>)
}
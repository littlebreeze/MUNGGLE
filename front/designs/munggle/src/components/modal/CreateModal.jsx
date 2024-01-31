import React from "react";
import Modal from "react-modal";
import ReactQuill from "react-quill";

import "./CreateModal.css";

import imgClose from "../../assets/icons/close1.png";
import imgClose2 from "../../assets/icons/close2.png";

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
    height: "630px",
    zIndex: "150",
    position: "absolute",
    top: "385px",
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

export default function CreateModal(props) {
  const modules = {
    toolbar: {
      container: [
        ["image"],
        [{ header: [1, 2, 3, 4, 5, false]}],
        ["bold", "underline"],
      ],
    },
  };

  return (
    <Modal
      isOpen={props.isOpen}
      onRequestClose={props.closeModal}
      shouldCloseOnOverlayClick={false}
      style={customStyles}
      contentLabel="Create Modal"
    >
      <div className="create-modal-container">
        <div className="create-modal-top-div">
          <span className="create-modal-top-span">게시물 생성</span>
        </div>
        
        <div onClick={props.closeModal} className="create-modal-close-button-div">
          <img className="create-modal-close-button" src={imgClose2} />
        </div>

        <div className="create-modal-form-div">
          <form className="create-modal-form">
            <div className="create-modal-input-file-div">
              <input className="create-modal-input-file" type="file" />
            </div>
            <div className="create-modal-input-title-div">
              <input className="create-modal-input-title" placeholder="제목을 입력해주세요." type="text" />
            </div>
            <div className="create-modal-input-content-div">
              <ReactQuill 
                modules={modules}
                className="create-modal-input-content"
                placeholder="내용을 입력하세요."
                />
            </div>
            <div className="create-modal-input-tag-div">
              <input className="create-modal-input-tag" placeholder="태그를 선택해주세요." type="text" />
            </div>
            <div className="create-modal-input-submit-div">
              <input type="submit" className="create-modal-input-submit btn btn-secondary" onClick={props.closeModal} />
            </div>
          </form>
        </div>
      </div>
    </Modal>
  );
}
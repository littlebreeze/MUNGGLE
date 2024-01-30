import "./App.css";
import { useState } from "react";
import Nav from "./components/layout/Nav.jsx";
import Body from "./components/layout/Body.jsx";
import Footer from "./components/layout/Footer.jsx";
import SearchModal from "./components/modal/Search/SearchModal.jsx";

export default function App() {
  const [searchModalIsOpen, setSearchModalIsOpen] = useState(false);

  function openSearchModal() {
    setSearchModalIsOpen(true);
  }

  function closeSearchModal() {
    setSearchModalIsOpen(false);
  }

  return (
    <div className="app-container-div">
      <Nav
        openSearchModal={openSearchModal}
      />
      <Body 
      />
      <Footer />
      <SearchModal 
        isOpen={searchModalIsOpen}
        closeModal={closeSearchModal}
      />
    </div>
  );
}


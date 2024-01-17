import "./App.css";
import Nav from "./layout/Nav.jsx";
import Body from "./layout/Body.jsx";
import Footer from "./layout/Footer.jsx";

export default function App() {
  return (
    <div className="app-container-div">
      <Nav />
      <Body />
      <Footer />
    </div>
  );
}


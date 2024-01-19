import "./App.css";
import Nav from "./components/layout/Nav.jsx";
import Body from "./components/layout/Body.jsx";
import Footer from "./components/layout/Footer.jsx";

export default function App() {
  return (
    <div className="app-container-div">
      <Nav />
      <Body />
      <Footer />
    </div>
  );
}


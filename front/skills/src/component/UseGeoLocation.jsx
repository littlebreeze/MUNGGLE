import React, { useState, useEffect } from 'react';

function App() {
  const [location, setLocation] = useState({});

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        setLocation({
          latitude: position.coords.latitude,
          longitude: position.coords.longitude,
        });
      },
      (error) => console.log(error)
    );
  }, []);

  return (
    <div>
      <h1>Latitude: {location.latitude}</h1>
      <h1>Longitude: {location.longitude}</h1>
    </div>
  );
}

export default App;
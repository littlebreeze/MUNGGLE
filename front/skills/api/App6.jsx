import React, {useEffect, useState} from 'react';
import {View, Text, StyleSheet} from 'react-native';
import axios from 'axios';
import {edit} from './App5';

const App = () => {
  useEffect(() => {
    // Call the edit function when the component mounts
    edit();
  }, []);
  return <View></View>;
};

export default App;

import React, { useState, useEffect } from "react";
import { View, Text, Image, Switch, 
  Button, ScrollView, StyleSheet,
  Dimensions, TouchableOpacity
} from "react-native";
import ToggleSwitch from 'toggle-switch-react-native';

import imageWink from "../../assets/icons/wink.png";
import imageNose from "../../assets/icons/nose.png";
import iconInfoEdit from "../../assets/icons/infoEdit.png";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function MatchScreen ({ navigation }) {
  const [isMatch, setIsMatch] = useState(false)
  
  const handleIsMatch = () => {
    setIsMatch(!isMatch)
  };

  return (
    <View style={styles.matchContainer}>
      <View style={styles.matchWinkView}>
        <Text style={styles.matchWinkText}>킁킁</Text>
        <Image
          style={styles.matchWinkImage}
          source={imageWink}
        />
      </View>

      <View style={styles.matchSwitchView}>
        <ToggleSwitch
          trackOnStyle={{
            borderColor: "lightgrey",
            borderWidth: 1
          }}
          trackOffStyle={{
            borderColor: "lightgrey",
            borderWidth: 1
          }}
          thumbOnStyle={{
            borderColor: "lightgrey",
            borderWidth: 1
          }}
          thumbOffStyle={{
            borderColor: "lightgrey",
            borderWidth: 1
          }}
          isOn={isMatch}
          onColor="green"
          offColor="lightgrey"
          size="large"
          onToggle={handleIsMatch}
        />
      </View>

      <TouchableOpacity style={styles.matchNoseView}>
        <Image 
          style={styles.matchNoseImage}
          source={imageNose}
        />
      </TouchableOpacity>

      <TouchableOpacity style={styles.matchInfoEditIconView}>
        <Image 
          style={styles.matchInfoEditIcon}
          source={iconInfoEdit}
        />
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  matchContainer: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.82,
    backgroundColor: "white",
    position: "relative",
  },
  matchWinkView: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.50,
    backgroundColor: "rgb(253, 245, 169)",
    justifyContent: "center",
    alignItems: "center",
  },
  matchWinkText: {
    fontSize: 40,
    fontWeight: "600",
  },
  matchWinkImage: {
    marginTop: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.6,
    height: SCREEN_WIDTH * 0.3,
    marginBottom: SCREEN_HEIGHT * 0.02,
  },
  matchSwitchView: {
    position: "absolute",
    top: SCREEN_WIDTH * 0.05,
    right: SCREEN_WIDTH * 0.05,
  },
  matchNoseView: {
    width: SCREEN_WIDTH * 0.7,
    height: SCREEN_WIDTH * 0.7,
    backgroundColor: "white",
    borderColor: "gainsboro",
    borderWidth: 1,
    borderRadius: 200,
    position: "absolute",
    left: SCREEN_WIDTH * 0.15,
    bottom: SCREEN_WIDTH * 0.31,
  },
  matchNoseImage: {
    width: SCREEN_WIDTH * 0.7,
    height: SCREEN_WIDTH * 0.7,
    borderRadius: 200,
    
  },
  matchInfoEditIconView: {
    width: SCREEN_WIDTH * 0.16,
    height: SCREEN_WIDTH * 0.16,
    position: "absolute",
    bottom: SCREEN_WIDTH * 0.08,
    right: SCREEN_WIDTH * 0.07,
  },
  matchInfoEditIcon: {
    width: SCREEN_WIDTH * 0.16,
    height: SCREEN_WIDTH * 0.16,
  },
});
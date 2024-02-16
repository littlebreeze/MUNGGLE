import React from "react";
import { View, Text, StyleSheet, Image, Dimensions } from "react-native";

import imageLogo from "../../assets/icons/logo.png"
import iconSearch from "../../assets/icons/search.png"
import iconNotification from "../../assets/icons/notification.png"
import iconDirectMessage from "../../assets/icons/directMessage.png"

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function Nav () {
  return (
    <View style={styles.navContainer}>
      <View style={styles.navLogoView}>
        <Image 
          style={styles.navLogoImage}
          source={imageLogo}
        />
      </View>

      <View style={styles.navIconView}>
        <Image 
          style={styles.navIconSearch}
          source={iconSearch}
        />
        <Image 
          style={styles.navIconNotification}
          source={iconNotification}
        />
        <Image 
          style={styles.navIconDirectMessage}
          source={iconDirectMessage}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create ({
  navContainer: {
    flex: 1,
    flexDirection: "row",
    alignItems: "center",
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.08,
    paddingTop: SCREEN_HEIGHT * 0.03,
  },
  navLogoView: {
    flex: 1,
    height: SCREEN_HEIGHT * 0.06,
    alignItems: "start",
    justifyContent: "center",
  },
  navLogoImage: {
    marginLeft: SCREEN_WIDTH * 0.05,
    width: SCREEN_WIDTH * 0.2,
    height: SCREEN_HEIGHT * 0.05,
  },
  navIconView: {
    flex: 1,
    flexDirection: "row",
    justifyContent: "flex-end",
    width: SCREEN_WIDTH * 0.3,
    height: SCREEN_HEIGHT * 0.06,
  },
  navIconSearch: {
    width: SCREEN_WIDTH * 0.065,
    height: SCREEN_HEIGHT * 0.031,
    marginHorizontal: SCREEN_WIDTH * 0.015,
    marginTop: SCREEN_HEIGHT * 0.017,

  },
  navIconNotification: {
    width: SCREEN_WIDTH * 0.075,
    height: SCREEN_HEIGHT * 0.037,
    marginHorizontal: SCREEN_WIDTH * 0.015,
    marginTop: SCREEN_HEIGHT * 0.015,
  },
  navIconDirectMessage: {
    width: SCREEN_WIDTH * 0.075,
    height: SCREEN_HEIGHT * 0.037,
    marginHorizontal: SCREEN_WIDTH * 0.015,
    marginTop: SCREEN_HEIGHT * 0.017,
  },
});
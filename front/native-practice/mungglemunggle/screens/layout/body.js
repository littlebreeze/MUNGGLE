import React from "react";
import { Dimensions, Image, StyleSheet } from "react-native";

import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";

import PostScreen from "../pages/post";
import MatchScreen from "../pages/match";
import WalkScreen from "../pages/walk";
import ProfileScreen from "../pages/profile";

import iconPost from "../../assets/icons/post.png";
import iconWalk from "../../assets/icons/walk.png";
import iconMatch from "../../assets/icons/match.png";
import iconProfile from "../../assets/icons/profile.png";

const Tab = createBottomTabNavigator();
const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function Body (navigation) {
  return (
    <Tab.Navigator
    initialRouteName="Post"
    screenOptions={{
      headerShown: false,
      tabBarShowLabel: false,
      tabBarActiveTintColor: '#e91e63',
      tabBarStyle: {
        height: SCREEN_HEIGHT * 0.08,
      },
      tabBarItemStyle: {
        width: SCREEN_HEIGHT * 0.08,
        height: SCREEN_HEIGHT * 0.08,
      },
    }}
  >
    <Tab.Screen
      name="Post"
      component={PostScreen}
      options={{
        tabBarIcon: () => (
          <Image
            style={styles.tabBarIcon}
            source={iconPost}
          />
        ),
      }}
    />
    <Tab.Screen
      name="Walk"
      component={WalkScreen}
      options={{
        tabBarIcon: () => (
          <Image
            style={styles.tabBarIcon}
            source={iconWalk}
          />
        ),
      }}
    />
    <Tab.Screen
      name="Match"
      component={MatchScreen}
      options={{
        tabBarIcon: () => (
          <Image
            style={styles.tabBarIcon}
            source={iconMatch}
          />
        ),
      }}
    />
    <Tab.Screen
      name="Profile"
      component={ProfileScreen}
      options={{
        tabBarIcon: () => (
          <Image
            style={styles.tabBarIcon}
            source={iconProfile}
          />
        ),
      }}
    />
  </Tab.Navigator>
  );
}

const styles = StyleSheet.create({
  tabBarIcon: {
    width: SCREEN_HEIGHT * 0.06,
    height: SCREEN_HEIGHT * 0.06,
  }
})
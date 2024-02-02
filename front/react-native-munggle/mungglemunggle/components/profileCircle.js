import React from "react";
import { View, Text, StyleSheet, Image, TouchableOpacity } from "react-native";

export default function ProfileCircle (props) {
  return (
    <View style={styles.profileCircleContainer}>
      <TouchableOpacity style={styles.profileCircleImageView}>
        <Image 
          style={styles.profileCircleImage}
          source={props.imageProfile}
        />
      </TouchableOpacity>

      <View style={styles.profileCircleNameView}>
        <Text style={styles.profileCircleName}>{ props.nameProfile }</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create ({
  profileCircleContainer: {
    width: 100,
    height: 115,
    justifyContent: "center",
    alignItems: "center",
  },
  profileCircleImageView: {
    borderRadius: 100,
  },
  profileCircleImage: {
    borderRadius: 100,
    borderColor: "lightgrey",
    borderWidth: 1,
    width: 90,
    height: 90,
  },
  profileCircleNameView: {
    width: 90,
    justifyContent: "center",
    alignItems: "center",
  },
  profileCircleName: {
    fontSize: 18,
    fontWeight: "500",
  },
}); 
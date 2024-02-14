import React, { useState } from "react";
import { View, Text, Modal, StyleSheet, Dimensions, TouchableOpacity, Alert } from "react-native";
import axios from "axios";

export default function WalkDetail({ details }) {

  return (
    <View>
      {details.map((detail, index) => (
        <View key={index}>
          <Text>Walk Name: {detail.walkName}</Text>
          <Text>Description: {detail.description}</Text>
          <Text> </Text>
        </View>
      ))}
    </View>
  )
}

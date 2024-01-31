import React from "react";
import { View, Text, Button, ScrollView } from "react-native";

export default function PostScreen ({ navigation }) {
  return (
    <ScrollView style={{ flex: 1 }}>
      <Text>PostScreen</Text>
      <Button
        title="Go To Profile"
        onPress={() => navigation.navigate('Profile')} 
      />
      <Button
        title="Go To Match"
        onPress={() => navigation.navigate('Match')} 
      />
      <Button
        title="Go To Walk"
        onPress={() => navigation.navigate('Walk')} 
      />
    </ScrollView>
  );
};
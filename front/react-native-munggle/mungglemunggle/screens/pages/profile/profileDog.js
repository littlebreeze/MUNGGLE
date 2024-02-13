import React, { useState, useEffect } from "react";
import { View, ScrollView, Text, Image, StyleSheet, Dimensions } from "react-native";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function ProfileDog (props) {
  const dogList = props.dogList;

  const dogs = () => {
    return (
      <View style={styles.profileDogListView}>
        {dogList && dogList.map((dog, index) => {
          return (
            <View style={styles.profileDogView} key={index}>
              <View style={styles.profileDogImageView}>
                <Image 
                  style={styles.profileDogImage}
                  src={dog.image}
                />
              </View>
              <View style={styles.profileDogNameView}>
                <Text style={styles.profileDogName}>{dog.name}</Text>
              </View>
              <View style={styles.profileDogViewBottomView}>
                <View style={styles.profileDogViewBottomViewLeftView}>
                  <Text style={styles.profileDogViewBottomText}>생일 : {dog.birthDate}</Text>
                  <Text style={styles.profileDogViewBottomText}>성별 : {dog.gender}</Text>
                </View>
                <View style={styles.profileDogViewBottomViewRightView}>
                  <Text style={styles.profileDogViewBottomText}>견종 : {dog.kindId}</Text>
                  <Text style={styles.profileDogViewBottomText}>무게 : {dog.weight} kg</Text>
                </View>
              </View>
            </View>
          );
        })}
      </View>
    );
  };

  return (
    <View style={styles.profileDogContainer}>
      {dogs()}
    </View>
  );
};

const styles = StyleSheet.create({
  profileDogContainer: {
    width: SCREEN_WIDTH,
  },
  profileDogListView: {
    width: SCREEN_WIDTH,
    alignItems: "center",
  },
  profileDogView: {
    width: SCREEN_WIDTH * 0.8,
    height: SCREEN_HEIGHT * 0.38,
    padding: 20,
    alignItems: "center",
    marginVertical: 20,
    backgroundColor: "white",
  },
  profileDogImageView: {
    width: SCREEN_WIDTH * 0.7,
    height: SCREEN_HEIGHT * 0.23,
  },
  profileDogImage: {
    width: SCREEN_WIDTH * 0.7,
    height: SCREEN_HEIGHT * 0.23,
  },
  profileDogNameView: {
    width: SCREEN_WIDTH * 0.7,
    height: SCREEN_HEIGHT * 0.044,
    justifyContent: "center",
    alignItems: "center",
    borderBottomWidth: 1,
    borderBottomColor: "gainsboro",
  },
  profileDogName: {
    fontSize: 19,
    fontWeight: "500",
  },  
  profileDogViewBottomView: {
    marginTop: 5,
    width: SCREEN_WIDTH * 0.7,
    height: SCREEN_HEIGHT * 0.1,
    position: "relative",
    flexDirection: 'row',
  },
  profileDogViewBottomViewLeftView: {
    width: SCREEN_WIDTH * 0.31,
    height: SCREEN_HEIGHT * 0.05,
    justifyContent: "space-around",
    alignItems: "flex-start",
    marginLeft: 20,
    borderRightColor: "gainsboro",
    borderRightWidth: 1,
  },
  profileDogViewBottomViewRightView: {
    width: SCREEN_WIDTH * 0.3,
    height: SCREEN_HEIGHT * 0.05,
    justifyContent: "space-around",
    alignItems: "flex-start",
    marginLeft: 20,
    
  },
  profileDogViewBottomText: {

  },
});
import React, { useState } from "react";
import { View, Text, Modal, StyleSheet, Dimensions, TouchableOpacity, Alert } from "react-native";
import { Calendar } from "react-native-calendars";
import WalkDetail from "./walkDetail";
import axios from "axios";

export default function WalkCalendar() {
  const apiUrl = "http://i10a410.p.ssafy.io:8080";
  const [selectedDate, setSelectedDate] = useState(null);
  const [modalVisible, setModalVisible] = useState(false);
  const [filteredDetails, setFilteredDetails] = useState([]);

  const handleDatePress = (date) => {
    setSelectedDate(date);
    const filtered = details.filter(detail => isSameDate(detail.createdAt, date));
    setFilteredDetails(filtered);
    setModalVisible(true);
  };

  const details = [
    {
      walkId: 2,
      walkName: "산책222",
      dogId: 1,
      description: "2222좋아요",
      duration: 11,
      distance: 11,
      rating: 4.3,
      createdAt: "2024-02-16T23:23:22",
      user: {
          id: 1,
          backgroundImgUrl: null,
          profileImgUrl: null,
          nickname: "닉네임을적습니다",
          desc: null
      },
      location: [
          {
              walkId: 2,
              lat: 1.123,
              lng: 2.222,
          },
          {
              walkId: 2,
              lat: 1.133,
              lng: 2.232,
          },
          {
              walkId: 2,
              lat: 1.143,
              lng: 2.242,
          }
      ],
      deleted: false
    },
    {
      walkId: 3,
      walkName: "산책444",
      dogId: 1,
      description: "좋아싫어좋아싫어",
      duration: 11,
      distance: 11,
      rating: 4.3,
      createdAt: "2024-02-15T23:23:22",
      user: {
          id: 1,
          backgroundImgUrl: null,
          profileImgUrl: null,
          nickname: "닉네임을적습니다",
          desc: null
      },
      location: [
          {
              walkId: 2,
              lat: 1.123,
              lng: 2.222,
          },
          {
              walkId: 2,
              lat: 1.133,
              lng: 2.232,
          },
          {
              walkId: 2,
              lat: 1.143,
              lng: 2.242,
          }
      ],
      deleted: false
    }
  ];

  const isSameDate = (date1, date2) => {
    const d1 = new Date(date1);
    const d2 = new Date(date2);
    return d1.getFullYear() === d2.getFullYear() &&
           d1.getMonth() === d2.getMonth() &&
           d1.getDate() === d2.getDate();
  };

  return (
    <View style={{ flex: 1 }}>
      <Calendar
        style={styles.calendar}
        onDayPress={(day) => handleDatePress(day.dateString)}
      />
      <Modal
        animationType="slide"
        transparent={true}
        visible={modalVisible}
        onRequestClose={() => {
          setModalVisible(false);
        }}
      >
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            <Text>Selected Date: {selectedDate}</Text>
            <Text> </Text>
            <WalkDetail details={filteredDetails} />
            <TouchableOpacity onPress={() => setModalVisible(false)}>
              <Text style={{ color: 'blue', marginTop: 10 }}>Close</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>
    </View>
  );
}

const styles = StyleSheet.create({
  calendar: {
    borderBottomWidth: 1,
    borderBottomColor: '#e0e0e0',
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  modalContent: {
    backgroundColor: 'white',
    padding: 20,
    borderRadius: 10,
    elevation: 5,
  },
});
import React, { useState, useEffect } from "react";
import { StatusBar, View, Text, Image, Switch, 
  Button, ScrollView, StyleSheet,
  Dimensions, TouchableOpacity, Alert, Modal, FlatList
} from "react-native";
import ToggleSwitch from 'toggle-switch-react-native';

import imageWink from "../../../assets/icons/wink.png";
import imageNose from "../../../assets/icons/nose.png";
import iconInfoEdit from "../../../assets/icons/infoEdit.png";
import { useNavigation } from "@react-navigation/native";
import profile from "../../../assets/icons/profile.png";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

export default function MatchMain () {

  const Mydog = [
    {
        "dogId": 28,
        "kindId": 55,
        "birthDate": "2023-07-07T23:25:22",
        "size": "",
        "weight": 0.0,
        "gender": "",
        "isNeutering": true,
        "name": "초롱",
        "image": "https://flexible.img.hani.co.kr/flexible/normal/850/567/imgdb/original/2023/0111/20230111503366.jpg",
        "description": "스윗 리를 도그"
    },
    {
        "dogId": 43,
        "kindId": 55,
        "birthDate": "2023-07-07T23:25:22",
        "size": "",
        "weight": 0.0,
        "gender": "",
        "isNeutering": true,
        "name": "푸푸",
        "image": "https://flexible.img.hani.co.kr/flexible/normal/850/567/imgdb/original/2023/0111/20230111503366.jpg",
        "description": "스윗 리를 도그"
    },
    {
        "dogId": 53,
        "kindId": 55,
        "birthDate": "2023-07-07T23:25:22",
        "size": "",
        "weight": 0.0,
        "gender": "",
        "isNeutering": true,
        "name": "삼성",
        "image": "https://flexible.img.hani.co.kr/flexible/normal/850/567/imgdb/original/2023/0111/20230111503366.jpg",
        "description": "스윗 리를 도그"
    },
    {
        "dogId": 59,
        "kindId": 77,
        "birthDate": "2023-07-07T23:25:22",
        "size": "",
        "weight": 0.0,
        "gender": "",
        "isNeutering": true,
        "name": "핑크",
        "image": null,
        "description": "큩큩큩!"
    }
  ];

  const navigation = useNavigation();

  //매칭하려고 선택한 강아지 PK
  const [currentDogID, setCurrentDogID] = useState(0);
  //강아지가 1마리 이상 있는지 여부
  const [haveDog, setHaveDog] = useState(true);
  //강아지 프로필 url
  const [dogImage, setdogImage] = useState(null);

  //매칭하고싶은지 여부
  const [isMatch, setIsMatch] = useState(false)
  
  //내 특징, 원하는 강아지 특징을 적었는지 여부
  const [mySetting, setMySetting] = useState(true)
  const [wantSetting, setWantSetting] = useState(true)


  //토글 버튼 클릭
  const handleIsMatch = () => {
    haveNoDog();
    if (haveDog == true){
    setIsMatch(!isMatch);}
  };

  //강아지가 없는 경우, 등록한다고 하면 모달 띄우는 함수
  const haveNoDog = () => {
    if (haveDog === false) {
      Alert.alert(
        "강아지 등록 안내",
        "매칭페이지를 이용하려면 강아지를 등록하세요",
        [
          {
            text: "취소",
            onPress: () => {
              console.log("강아지 등록 x");
            },
            style: "cancel",
          },
          {
            text: "등록",
            onPress: () => {
              console.log("강아지 등록 o");
              //모달생성코드 미작성
            },
          },
        ]
      );
    }
  }

  //코 클릭
  const clickNose = () => {
    haveNoDog();
    if (haveDog == true && mySetting == true && wantSetting){
      navigation.navigate('MatchLoading')
    }
  }

  //편집버튼 누를때 나오는 모달
  const [isEditModalVisible, setEditModalVisible] = useState(false);

  //편집버튼 클릭
  const clickEditModal = () => {
    haveNoDog();
    if (haveDog == true){
    toggleEditModal();}
  }

  const toggleEditModal = () => {
    setEditModalVisible(!isEditModalVisible);
  };

  const openMySetting = () => {
    setEditModalVisible(!isEditModalVisible);
    navigation.navigate('MatchMySetting')
  }

  const openWantSetting = () => {
    setEditModalVisible(!isEditModalVisible);
    navigation.navigate('MatchWantSetting')
  }

  //매칭 대상 강아지 변경 모달
  const [isDogModalVisible, setDogModalVisible] = useState(false);

  const clickDogModal = () => {
    haveNoDog();
    if (haveDog == true){
      toggleDogModal();
    }
  }

  const toggleDogModal = () => {
    setDogModalVisible(!isDogModalVisible);
  };


  return (
    <View style={styles.matchContainer}>
      {/*노란 배경 부분*/}
      <View style={styles.matchTopView}>
        {/*프로필 사진, 토글 버튼*/}
        <View style={styles.matchTopViewTop}>
          <TouchableOpacity onPress={clickDogModal} style={styles.imageButtonOpacity}>
            <Image
               source={dogImage ? { uri: dogImage } : profile} // 이미지 경로 지정
              style={styles.imageButton}
            />
          </TouchableOpacity>
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
          /></View>
        {/*친구 찾기*/}
        <View style={styles.matchTopViewMiddle}>
          <Text style={styles.matchWinkText}>친구 찾기</Text>
        </View>
        {/*윙크 눈*/}
        <View style={styles.matchTopViewBottom}>
          <Image
            style={styles.matchWinkImage}
            source={imageWink}
          />
        </View>
      </View>

      {/*코 버튼(position:absolute)*/}
      <TouchableOpacity style={styles.matchNoseView} onPress={clickNose}>
        <Image 
          style={styles.matchNoseImage}
          source={imageNose}
        />
      </TouchableOpacity>

      {/*하얀 배경 부분*/}
      <View style={styles.matchBottomView}>
        <View style={styles.matchBottomViewBottom}>
          <TouchableOpacity style={styles.matchInfoEditIconView} onPress={clickEditModal}>
            <Image 
              style={styles.matchInfoEditIcon}
              source={iconInfoEdit}
            />
          </TouchableOpacity>
        </View>
      </View>

      {/* 설정 변경 모달 */}
      <Modal
        transparent={true}
        visible={isEditModalVisible}
        onRequestClose={toggleEditModal}
      >
        <View style={styles.modalContainer}>
          <StatusBar backgroundColor="rgba(0, 0, 0, 0.5)" barStyle="dark-content" />
          <View style={styles.modalContent}>
            {/* 모달 내용 */}
            <TouchableOpacity onPress={toggleEditModal}>
              <Text>X</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={openMySetting}>
              <Text style={styles.modalText} >내 강아지 설정 변경</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={openWantSetting}>
              <Text style={styles.modalText}>원하는 강아지 설정 변경</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>

      {/* 프로필 변경 모달 */}
      <Modal
        transparent={true}
        visible={isDogModalVisible}
        onRequestClose={toggleDogModal}
      >
        <View style={styles.modalContainer}>
          <StatusBar backgroundColor="rgba(0, 0, 0, 0.5)" barStyle="dark-content" />
          <View style={styles.modalContent}>
            <TouchableOpacity onPress={toggleDogModal}>
              <Text>X</Text>
            </TouchableOpacity>
            <FlatList
            horizontal
            data={Mydog}
            keyExtractor={(item) => item.dogId.toString()}
            renderItem={({ item }) => (
              <TouchableOpacity style={styles.imageContainer} onPress={() => {
                setdogImage(item.image);
                setCurrentDogID(item.dogId);
                setDogModalVisible(!isDogModalVisible);
              }}>
                <Image source={item.image ? { uri: item.image } : profile} style={styles.image} />
                <Text>{item.name}</Text>
              </TouchableOpacity>
            )}
          />
          </View>
        </View>
      </Modal>


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
  matchTopView: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.50,
    backgroundColor: "rgb(253, 245, 169)",
    alignItems: "center",
  },

  //matchTopViewTop관련
  matchTopViewTop: {
    flexDirection: 'row',
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.10,
    justifyContent: 'space-between',
    alignItems: "center",
    padding: 10,
  },
  imageButtonOpacity:{
    width:60,
    height: 60,
    backgroundColor:'white',
    borderRadius: 10,
    alignItems:'center',
    justifyContent: 'center',
  },
  imageButton: {
    width: 50,
    height: 50,
    borderRadius: 10
  },

  //matchTopViewMiddle관련
  matchTopViewMiddle: {
    width: SCREEN_WIDTH,
    height: SCREEN_WIDTH * 0.3,
    alignItems:'center',
    justifyContent: 'center',
  },
  matchWinkText: {
    fontSize: 40,
    fontWeight: "600",
  },

  //matchTopViewBottom관련
  matchTopViewBottom:{
    width: SCREEN_WIDTH,
    height: SCREEN_WIDTH * 0.3,
    alignItems:'center',
    justifyContent: 'center',
  },
  matchWinkImage: {
    marginTop: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.6,
    height: SCREEN_WIDTH * 0.3,
    marginBottom: SCREEN_HEIGHT * 0.02,
  },

  //코 버튼 관련
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
    shadowColor: "black",
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.3,
    shadowRadius: 4,
    elevation: 5,
    zIndex: 2,
  },
  matchNoseImage: {
    width: SCREEN_WIDTH * 0.7,
    height: SCREEN_WIDTH * 0.7,
    borderRadius: 200, 
  },

  matchBottomView:{
    width: SCREEN_WIDTH,
    height: SCREEN_WIDTH * 0.7,
    alignItems:'center',
    justifyContent: 'flex-end',
  },

  //matchBottomViewBottom 관련
  matchBottomViewBottom:{
    width: SCREEN_WIDTH,
    height: SCREEN_WIDTH * 0.3,
    alignItems:'flex-end',
    justifyContent: 'flex-end',
  },
  matchInfoEditIconView: {
    width: SCREEN_WIDTH * 0.16,
    height: SCREEN_WIDTH * 0.16,
    bottom: SCREEN_WIDTH * 0.08,
    right: SCREEN_WIDTH * 0.07,
  },
  matchInfoEditIcon: {
    width: SCREEN_WIDTH * 0.16,
    height: SCREEN_WIDTH * 0.16,
  },
  modalContainer: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT,
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  modalContent: {
    marginTop: SCREEN_HEIGHT * 0.8,
    height: SCREEN_HEIGHT * 0.2,
    backgroundColor: 'white',
    padding: 20,
    borderTopLeftRadius: 10,
    borderTopRightRadius: 10,
    
  },

  modalText:{
    fontSize:20,
    fontWeight:'bold',
    marginVertical: 10
  },

  imageContainer: {
    marginRight: 10,
    width: SCREEN_WIDTH * 0.15,
    height: SCREEN_WIDTH * 0.15,
    alignItems:'center',
    margin:10
    
  },
  image: {
    width: '100%',
    height: '100%',
    borderRadius: 30,
  },
});
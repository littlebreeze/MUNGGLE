import React, { useState } from 'react';
import { Modal, Dimensions, StyleSheet, View, Text, Image, TouchableOpacity, ScrollView } from 'react-native';
import back from '../../../assets/icons/back.png';
import change from '../../../assets/icons/change.png';
import RadioButtonGroup, { RadioButtonItem } from "expo-radio-button";
import dm from '../../../assets/icons/directMessage.png';
import { useNavigation } from '@react-navigation/native';
import ModalComponent from '../../../components/modal/directMessage';
import Checkbox from 'expo-checkbox';

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

const dog =  {
  "dogId": 1,
  "characterId": ["말썽꾸러기", "모험을 좋아함", "사냥꾼", "상냥함"],
  "isNeutering": false}

export default function MatchMySetting() {

  const navigation = useNavigation();
  const [showModal, setShowModal] = useState(false);


  const [isNeuteredModalVisible, setNeuteredModalVisible] = useState(false);
  const [isPersonalityModalVisible, setPersonalityModalVisible] = useState(false);
  const [neutered, setNeutered] = useState(dog.isNeutering);
  const [personality, setPersonality] = useState({
    "말썽꾸러기": false,
    "모험을 좋아함": false,
    "사냥꾼": false,
    "상냥함": false
  });

  const handleNeuteredChange = (value) => {
    setNeutered(value);
  };

  const handlePersonalityChange = (trait) => {
    setPersonality((prevPersonality) => ({
      ...prevPersonality,
      [trait]: !prevPersonality[trait],
    }));
  };


  return (
    <View style={styles.matchContainer}>
      <View style={styles.TopView}>
        <View style={styles.TopViewTextBox}>
          <Text style={styles.detailText}>내 강아지 특징 등록</Text>
        </View>

        <View style={styles.TopViewEndButton}>
          <TouchableOpacity 
            style={styles.matchInfoEditIconViewCenter} 
            onPress={() => {
              navigation.navigate('MatchMain')
            }}
          >
            <Image 
              style={styles.matchInfoEditIcon}
              source={back}
            />
          </TouchableOpacity>
        </View>
      </View>

      <View style={styles.Box}>
        <View style={{width : SCREEN_WIDTH * 0.7, flexDirection: 'row', alignItems: 'center'}}>
          <Text style={styles.resultText}>중성화 여부 : </Text>
          <Text style={styles.normalText} > {neutered=="true" ? "O" : "X"} </Text>
        </View>
        {/* 동그란 이미지 버튼 */}
        <View style={{ flexDirection: 'row', marginLeft: SCREEN_WIDTH * 0.1}}>
          <TouchableOpacity onPress={(()=>{
            setNeuteredModalVisible(true);
          })}>
            <Image source={change} style={styles.buttonImage} />
          </TouchableOpacity>
        </View>
      </View>

      <View style={styles.Box}>
        <View style={{width : SCREEN_WIDTH * 0.7, flexDirection: 'row', alignItems: 'center'}}>
        <Text style={styles.resultText}>성격 : </Text>
        <View style={styles.normalText}>
        <Text>{personality["말썽꾸러기"] ? " 말썽꾸러기 " : ""}</Text>
        <Text >{personality["모험을 좋아함"] ? " 모험을 좋아함 " : ""}</Text>
        <Text >{personality["사냥꾼"] ? " 사냥꾼 " : ""}</Text>
        <Text >{personality["상냥함"] ? " 상냥함 " : ""}</Text>

        </View>
        </View>
        {/* 동그란 이미지 버튼 */}
        <View style={{ flexDirection: 'row', marginLeft: SCREEN_WIDTH * 0.1 }}>
          <TouchableOpacity onPress={(() => {
            setPersonalityModalVisible(true);
          })}>
            <Image source={change} style={styles.buttonImage} />
          </TouchableOpacity>
        </View>
      </View>


      <Modal transparent={true} visible={isNeuteredModalVisible}>
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            <Text style={{fontSize:15, fontWeight:'bold', marginTop:20}}>중성화 여부를 선택해주세요</Text>
            <RadioButtonGroup
        containerStyle={{ marginBottom: 10 }}
        selected={neutered}
        onSelected={(value) => handleNeuteredChange(value)}
        radioBackground="green"
      >
    
        <RadioButtonItem value="true" label="O" style={{marginTop:20}}/>
        <RadioButtonItem value="false" label="X" />
      </RadioButtonGroup>

            <TouchableOpacity onPress={(() => {
            setNeuteredModalVisible(false);
          })}>
              <Text>확인</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>

      {/* Personality Modal */}
      <Modal transparent={true} visible={isPersonalityModalVisible}>
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            <Text style={{fontSize:15, fontWeight:'bold', marginTop:20}}>내 강아지의 성격을 선택해주세요</Text>
            <View style={styles.modalContentView}>
              <Text style={styles.modalContentViewText}>말썽꾸러기</Text>
            <Checkbox
              label="말썽꾸러기"
              value={personality["말썽꾸러기"]}
              onValueChange={() => handlePersonalityChange("말썽꾸러기")}
            />
            </View>
            <View style={styles.modalContentView}>
              <Text style={styles.modalContentViewText}>모험을 좋아함</Text>
            <Checkbox
              label="모험을 좋아함"
              value={personality["모험을 좋아함"]}
              onValueChange={() => handlePersonalityChange('모험을 좋아함')}
            />
            </View>
            <View style={styles.modalContentView}>
              <Text style={styles.modalContentViewText}>사냥꾼</Text>
            <Checkbox
              label="사냥꾼"
              value={personality["사냥꾼"]}
              onValueChange={() => handlePersonalityChange('사냥꾼')}
            />
            </View>
            <View style={styles.modalContentView}>
              <Text style={styles.modalContentViewText}>상냥함</Text>
            <Checkbox
              label="상냥함"
              value={personality["상냥함"]}
              onValueChange={() => handlePersonalityChange('상냥함')}
            />
            </View>
            <TouchableOpacity onPress={(() => {
            setPersonalityModalVisible(false);
          })} style={{marginTop:20}}>
              <Text>확인</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>




    </View>

    
    )
};

const styles = StyleSheet.create({
  matchContainer: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.82,
    backgroundColor: 'rgb(253, 245, 169)',
    position: "relative",
  },
  matchTopView: {
    height: SCREEN_HEIGHT * 0.05,
  },
  TopView:{
    marginVertical: SCREEN_HEIGHT * 0.1,
    height: SCREEN_HEIGHT *  0.07,
    width: SCREEN_WIDTH,
    alignItems: 'center',
    zIndex: -1,
    flexDirection:'row',
    justifyContent: 'flex-start',
    paddingHorizontal: 16,
    paddingVertical: 8,
  },
  TopViewTextBox: {
    flex:1,
    alignItems: 'center',
  },

  TopViewEndButton: {
    position: 'absolute',
    right: SCREEN_WIDTH * 0.85,
    width: 50,
    height: 50,
    borderRadius: 25,
    borderColor: 'black',
    backgroundColor: 'white',
    justifyContent: 'center',
    alignItems: 'center',
  },

  matchInfoEditIconViewCenter: {
    width: 28,
    height: 28,
  },
  matchInfoEditIcon: {
    width: '100%',
    height: '100%',
    resizeMode: 'contain',
  },
  detailText:{
    fontSize: 25,
    fontWeight: 'bold',
    color: 'black',
    borderBottomColor: "gray",
    borderBottomWidth: 2,
  },

  Box: {
    padding: 20,
    marginHorizontal: SCREEN_WIDTH * 0.02,
    marginVertical: SCREEN_HEIGHT * 0.01,
    borderRadius: 7,
    flexDirection: 'row',
    alignItems: 'center',
    elevation: 2.5,
    backgroundColor: 'white',
    borderTopWidth: 0.2,
    borderColor:'gray',
  },
  resultText:{
    fontSize: 25,
    fontWeight: 'bold',
    color: 'black',
    paddingHorizontal: 7
  },
  buttonImage:{
    width: 30,
    height: 30,
  },
  resultBoxImage:{
    width: SCREEN_WIDTH * 0.15,
    height: SCREEN_WIDTH * 0.15,
    marginRight: 0,
    borderRadius:100
  },
  resultBoxText:{
    width: SCREEN_WIDTH * 0.4,
    alignItems: 'center'
  },
  normalText:{
    width: SCREEN_WIDTH * 0.5,
    fontSize:20,
    color:'gray',
    flexDirection:'row'
  },
  modalContainer: {
    marginLeft: SCREEN_WIDTH * 0.15,
    marginTop:SCREEN_HEIGHT * 0.35,
    width: SCREEN_WIDTH * 0.7,
    height: SCREEN_HEIGHT * 0.3,
    justifyContent: 'center',
    alignItems: 'center',
  },
  modalContent: {
    width: SCREEN_WIDTH * 0.7,
    height: SCREEN_HEIGHT * 0.3,
    backgroundColor: 'white',
    paddingHorizontal: 20

  },
  modalContentView: 
    {flexDirection:'row', marginTop:20, alignItems:'center'},
  modalContentViewText:{width:'70%', fontSize:20, fontWeight:'bold'}
  
});

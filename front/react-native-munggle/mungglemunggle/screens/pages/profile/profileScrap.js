import React, {useEffect} from "react";
import { View, Text, ScrollView, ActivityIndicator, Dimensions, StyleSheet, Image, TouchableOpacity } from "react-native";
import MasonryList from '@react-native-seoul/masonry-list';
import imgPost1 from "../../../assets/sample/dog1.jpg";

// 이미지 사이즈를 react native crop 으로 조정할 것이기 때문에 지금은 사이즈 조절 x

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

// 이미지의 높이 200 ~ 400 사이의 랜덤 정수 값으로 설정
const getRandomHeight = () => {
  return Math.floor(Math.random() * 200) + 200;
}

export default function ProfileScrap (props) {
  const postList = props.postList;

  const renderItem = ({ item }) => (
    <TouchableOpacity style={[styles.scrapImageBox, { height: getRandomHeight() }]}>
      <Image style={styles.scrapImage} source={item.imgPost} />
    </TouchableOpacity>
  );


return (
  <ScrollView style={styles.postScrapContainer}>
    <MasonryList
      // loading={true}
      // LoadingView={<ActivityIndicator/>}
      data={postList}
      renderItem={renderItem}
      keyExtractor={(item, index) => index.toString()}
      numColumns={3} // 원하는 열 수 설정
      columnWrapperStyle={styles.postScrapListContainer}
    />
  </ScrollView>
);
};

const styles = StyleSheet.create({
  postScrapContainer: {
  flex: 1,
},
postScrapListContainer: {
},
scrapImageBox: {
  flex: 1,
  margin: 4,
  borderRadius: 8,
  resizeMode: "center",
},
scrapImage: {
  width: "100%", 
  height: "100%",
},
});
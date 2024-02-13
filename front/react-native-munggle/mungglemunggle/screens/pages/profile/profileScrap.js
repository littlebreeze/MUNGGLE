import React, {useEffect, useState} from "react";
import { View, Text, ScrollView, ActivityIndicator, 
  Dimensions, StyleSheet, Image, TouchableOpacity,
  Modal, 
} from "react-native";
import MasonryList from '@react-native-seoul/masonry-list';

import PostDetail from "../../../components/modal/postDetail";

// 이미지 사이즈를 react native crop 으로 조정할 것이기 때문에 지금은 사이즈 조절 x

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

// 이미지의 높이 200 ~ 400 사이의 랜덤 정수 값으로 설정
const getRandomHeight = () => {
  return Math.floor(Math.random() * 200) + 200;
}

export default function ProfileScrap (props) {
  const scrapList = props.scrapList;

  const [detailPost, setDetailPost] = useState(false);

  const [isDetailModalOpen, setIsDetailModalOpen] = useState(false);

  const changeDetailPost = (postId) => {
    setDetailPost(postId);
  }

  const openDetailModal = (postId) => { 
    changeDetailPost(postId); 
    setIsDetailModalOpen(true);
  };

  const closeDetailModal = () => { setIsDetailModalOpen(false) };

  const renderItem = ({ item, i }) => (
    <TouchableOpacity 
      key={i} 
      style={[styles.scrapImageBox, 
      { height: getRandomHeight() }]}
      onPress={() => openDetailModal(item.postId)}
    >
      <Image style={styles.scrapImage} src={item.imageURL} />
    </TouchableOpacity>
  );

  const scraps = () => {
    if (scrapList) {
      return (
        <MasonryList
          // loading={true}
          // LoadingView={<ActivityIndicator/>}
          data={scrapList}
          renderItem={renderItem}
          keyExtractor={(item, index) => index.toString()}
          numColumns={3} // 원하는 열 수 설정
          columnWrapperStyle={styles.postScrapListContainer}
        />
      );
    } else {
      return (
        <View style={styles.indicatorView}>
          <ActivityIndicator size={100} />
        </View>
      );
    }
  }

return (
  <ScrollView style={styles.postScrapContainer}>
    {scraps()}
    <Modal
      animationType="fade"
      transparent={true}
      visible={isDetailModalOpen}
      onRequestClose={() => closeDetailModal()}>
      <PostDetail closeDetailModal={closeDetailModal} postId={detailPost} />
    </Modal>
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
  indicatorView: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.3,
    justifyContent: "center",
    alignItems: "center",
  }
});
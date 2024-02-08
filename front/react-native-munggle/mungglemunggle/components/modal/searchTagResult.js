import React, {useState} from "react";
import {
  View, TouchableOpacity, Image,
  ScrollView, Text, StyleSheet,
  Dimensions
} from "react-native";

import iconClose from "../../assets/icons/close1.png";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

const postData = [
  {
    "postId": 1,
    "postTitle": "멋진 내 강아지",
    "imageURLs": [
        "https://s3-munggle-files.s3.ap-northeast-2.amazonaws.com/1/4/63a54005-926f-480f-a222-2dc6a718e7d7png",
        "https://blancs.co.kr/web/product/big/201906/1e94a6b1b4be68347b0cd84a79b482ba.jpg",
        "https://live.staticflickr.com/7669/17268903144_b5e9d79c4e_z.jpg",
        "https://cdn.imweb.me/thumbnail/20221027/f76c4c81b7bde.jpg",
    ],
    "userId": 1,
    "profileImage": "https://images.mypetlife.co.kr/content/uploads/2023/11/17133418/61fbb115-3845-4427-b72d-76c5e650cd3c.jpeg",
    "nickname": "멍멍123",
    "likeCnt": 123,
    "isLiked": true,
    "createdAt": "2024-01-29T15:18:38.236335"
  },
  {
    "postId": 2,
    "postTitle": "강아지와 산책",
    "imageURLs": [
        "https://blancs.co.kr/web/product/big/201906/1e94a6b1b4be68347b0cd84a79b482ba.jpg",
        "https://live.staticflickr.com/7669/17268903144_b5e9d79c4e_z.jpg",
        "https://cdn.imweb.me/thumbnail/20221027/f76c4c81b7bde.jpg",
        "https://s3-munggle-files.s3.ap-northeast-2.amazonaws.com/1/4/63a54005-926f-480f-a222-2dc6a718e7d7png",
    ],
    "userId": 1,
    "profileImage": "http://www.evermodel.com/uploaded/model/414/d3d415e8cad046393ac6aa22f0bfd5980_slide.jpg",
    "nickname": "멍12",
    "likeCnt": 23,
    "isLiked": false,
    "createdAt": "2024-01-25T15:18:38.236335"
  },
  {
    "postId": 3,
    "postTitle": "강아지 생일",
    "imageURLs": [
        "https://cdn.imweb.me/thumbnail/20221027/f76c4c81b7bde.jpg",
        "https://blancs.co.kr/web/product/big/201906/1e94a6b1b4be68347b0cd84a79b482ba.jpg",
        "https://s3-munggle-files.s3.ap-northeast-2.amazonaws.com/1/4/63a54005-926f-480f-a222-2dc6a718e7d7png",
        "https://live.staticflickr.com/7669/17268903144_b5e9d79c4e_z.jpg",
    ],
    "userId": 1,
    "profileImage": "http://www.evermodel.com/uploaded/model/414/d3d415e8cad046393ac6aa22f0bfd5983_slide.jpg",
    "nickname": "멍글34",
    "likeCnt": 3,
    "isLiked": true,
    "createdAt": "2024-01-22T15:18:38.236335"
  },
  {
    "postId": 4,
    "postTitle": "멋진 강아지와 나",
    "imageURLs": [
        "https://live.staticflickr.com/7669/17268903144_b5e9d79c4e_z.jpg",
        "https://cdn.imweb.me/thumbnail/20221027/f76c4c81b7bde.jpg",
        "https://blancs.co.kr/web/product/big/201906/1e94a6b1b4be68347b0cd84a79b482ba.jpg",
        "https://s3-munggle-files.s3.ap-northeast-2.amazonaws.com/1/4/63a54005-926f-480f-a222-2dc6a718e7d7png",
    ],
    "userId": 1,
    "profileImage": "https://images.mypetlife.co.kr/content/uploads/2023/11/17133418/61fbb115-3845-4427-b72d-76c5e650cd3c.jpeg",
    "nickname": "멍12멍",
    "likeCnt": 13,
    "isLiked": false,
    "createdAt": "2024-01-28T15:18:38.236335"
  },
];


export default function SearchTagResult (props) {
  const [modalData, setModalData] = useState(postData);

  //게시물 결과에서의 이벤트
  const handleUserPress = () => {
    //유저 상세 정보 모달(이용자 결과에서도 사용)
    console.log("handleUserPress");
  };

  const handlePostPress = () => {
    //게시물 상세 정보 모달
    console.log("handlePostPress");
  };

  const handleLikePress = () => {
    //좋아요, 좋아요 취소 전송
    console.log("handleLikePress");
  }

  return (
    <View style={styles.modalContainer}>
      <TouchableOpacity onPress={props.closeModal}>
        <Image
              style={styles.closeModalText}
              source={iconClose}
            />
      </TouchableOpacity>
      <ScrollView
        contentContainerStyle={styles.result}
        vertical
        showsVerticalScrollIndicator={false}
      >
        {
          modalData.map((item, index) => (
          <View style={styles.profileContainer} key={index}>
            <TouchableOpacity onPress={handleUserPress}>
              <Image style={styles.profileImage} source={{ uri: item.profileImage }} />
              <Text style={styles.nickname}>{item.nickname}</Text>
            </TouchableOpacity>

            <TouchableOpacity onPress={handlePostPress}>
              <Image style={styles.postImage} source={{ uri: item.imageURLs[0] }} />
              <Text style={styles.postTitle}>{item.postTitle}</Text>
              <Text style={styles.createdAt}>{item.createdAt.substring(0, 10)}</Text>
            </TouchableOpacity>

            <View style={styles.likeContainer}>
              <TouchableOpacity onPress={handleLikePress}>
                <Text>{item.isLiked ? '❤️' : '🤍'}</Text>
              </TouchableOpacity>
              <Text style={styles.likeCount}>{item.likeCnt}</Text>
            </View>
          </View>
          ))
        }           
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  //태그 눌렀을 때 나오는 모달 관련
  modalContainer: {
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'white',
    marginTop: SCREEN_HEIGHT * 0.12,
    height: SCREEN_HEIGHT * 0.95,
    width: SCREEN_WIDTH * 0.9,
    marginLeft: SCREEN_WIDTH * 0.05,
  },
  modalText: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#fff',
  },
  closeModalText: {
    position: 'absolute',
    width: SCREEN_WIDTH * 0.06,
    height: SCREEN_WIDTH * 0.06,
    top: SCREEN_WIDTH * 0.21,
    right: SCREEN_WIDTH * 0.05,
  },

  searchModalBackGround: {
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT,
    justifyContent: "center",
    alignItems: "center",
  },
  searchModalContainer: {
    alignItems: "center",
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.8,
    backgroundColor: "red",
    marginBottom: SCREEN_HEIGHT * 0.03,
    position: "relative",
    borderRadius: 30,
  },
  closeView: {
    width: SCREEN_WIDTH * 0.06,
    height: SCREEN_WIDTH * 0.06,
    position: "absolute",
    top: 10,
    right: 10,
  },
  closeImage: {
    width: SCREEN_WIDTH * 0.06,
    height: SCREEN_WIDTH * 0.06,
  },
  
  searchScrollView: {
    flexGrow: 1,
  },
  result: {
    width: SCREEN_WIDTH*0.9,
    alignItems: 'center',
    justifyContent: 'center',
  },

  searchTopView: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: SCREEN_HEIGHT * 0.05,
    width: SCREEN_WIDTH * 0.8,
    height: SCREEN_HEIGHT * 0.05,
    justifyContent: "center",
    alignItems: "center",
    borderColor: 'gray',
    borderWidth: 1,
    paddingHorizontal: 8,
  },

  searchInput: {
    flex: 1,
  },
  searchImage: {
    width: 30,
    height: 30,
  },

  searchMiddleView: {
    marginTop: SCREEN_HEIGHT * 0.03,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.07,
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "row",
    backgroundColor: 'gray'
  },

  searchBottomView: {
    marginBottom: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.215,
    alignItems: "center"
  },


  //탭바 관련
  tabButton: {
    flex: 1,
    alignItems: 'center',
    paddingVertical: 8,
    borderBottomWidth: 2,
    borderBottomColor: 'transparent',
  },

  tabButtonText: {
    fontSize: 16,
    fontWeight: 'bold',
  },


  //태그 컨테이너 관련
  tagContainer: {
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.1,
    justifyContent: "center",
    borderRadius: 10,
    backgroundColor: '#e0e0e0',
    marginVertical: 8,
  },
  tagContent: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 8,
  },
  hashSymbol: {
    fontSize: 30,
    fontWeight: 'bold',
    marginRight: 8,
    paddingLeft: 15,
  },
  tagName: {
    fontSize: 16,
    fontWeight: 'bold',
  },

});
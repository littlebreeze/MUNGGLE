import React, {useState, useRef} from 'react';
import {StyleSheet, ScrollView, Text, View, Dimensions,
TouchableOpacity, Image, TextInput, Modal} from 'react-native';

import iconClose from "../../assets/icons/close1.png";
import iconSearch from "../../assets/icons/search.png";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

//테스트 tag 데이터(추후 삭제)
const tagData = [
  {
      "tagId": 1,
      "tagNm": "Hash"
  },
  {
      "tagId": 2,
      "tagNm": "Tag"
  },
  {
    "tagId": 3,
    "tagNm": "Dog"
},
{
    "tagId": 4,
    "tagNm": "Apple"
},
{
  "tagId": 5,
  "tagNm": "Grape"
},
{
  "tagId": 6,
  "tagNm": "Train"
},
{
  "tagId": 7,
  "tagNm": "House"
},
{
  "tagId": 8,
  "tagNm": "Tree"
},
{
  "tagId": 9,
  "tagNm": "Home"
},
{
  "tagId": 10,
  "tagNm": "Tom"
}
];
//테스트 user 데이터(추후 삭제)
const userData = [{
  "id": 1,
  "profileUrlImg": "https://s3-munggle-files.s3.ap-northeast-2.amazonaws.com/1/4/63a54005-926f-480f-a222-2dc6a718e7d7png",
  "nickname": "멍1"
  },
  {
    "id": 2,
    "profileUrlImg": "http://www.evermodel.com/uploaded/model/414/d3d415e8cad046393ac6aa22f0bfd5983_slide.jpg",
    "nickname": "멍2"
  },
  {
  "id": 3,
  "profileUrlImg": "https://images.mypetlife.co.kr/content/uploads/2023/11/17133418/61fbb115-3845-4427-b72d-76c5e650cd3c.jpeg",
  "nickname": "멍3"
  },
  {
  "id": 4,
  "profileUrlImg": "http://www.evermodel.com/uploaded/model/414/d3d415e8cad046393ac6aa22f0bfd5980_slide.jpg",
  "nickname": "멍4"
  },
]
//테스트 post 데이터(추후 삭제)
const postData = [
  {
      "postId": 4,
      "postTitle": "멋진 내 강아지",
      "imageURLs": [
          "https://s3-munggle-files.s3.ap-northeast-2.amazonaws.com/1/4/63a54005-926f-480f-a222-2dc6a718e7d7png"
      ],
      "userId": 1,
      "profileImage": "https://images.mypetlife.co.kr/content/uploads/2023/11/17133418/61fbb115-3845-4427-b72d-76c5e650cd3c.jpeg",
      "nickname": "멍멍123",
      "likeCnt": 123,
      "isLiked": true,
      "createdAt": "2024-01-29T15:18:38.236335"
  },
  {
    "postId": 4,
    "postTitle": "강아지와 산책",
    "imageURLs": [
        "https://blancs.co.kr/web/product/big/201906/1e94a6b1b4be68347b0cd84a79b482ba.jpg"
    ],
    "userId": 1,
    "profileImage": "http://www.evermodel.com/uploaded/model/414/d3d415e8cad046393ac6aa22f0bfd5980_slide.jpg",
    "nickname": "멍12",
    "likeCnt": 23,
    "isLiked": false,
    "createdAt": "2024-01-25T15:18:38.236335"
},
{
  "postId": 4,
  "postTitle": "강아지 생일",
  "imageURLs": [
      "https://cdn.imweb.me/thumbnail/20221027/f76c4c81b7bde.jpg"
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
      "https://live.staticflickr.com/7669/17268903144_b5e9d79c4e_z.jpg"
  ],
  "userId": 1,
  "profileImage": "https://images.mypetlife.co.kr/content/uploads/2023/11/17133418/61fbb115-3845-4427-b72d-76c5e650cd3c.jpeg",
  "nickname": "멍12멍",
  "likeCnt": 13,
  "isLiked": false,
  "createdAt": "2024-01-28T15:18:38.236335"
},];

export default function Search(props) {
  const [searchQuery, setSearchQuery] = useState('');
  const [activeTab, setActiveTab] = useState(0);
  const [contentData, setContentData] = useState([postData, userData, tagData]);
  const [modalData, setModalData] = useState(postData);
  // const [tagData, setTagData] = useState([]);
  // const [userData, setUserData] = useState([]);
  // const [postData, setPostData] = useState([]);

  const scrollViewRef = useRef(null);
  const [selectedTag, setSelectedTag] = useState(null);
  const [isModalVisible, setModalVisible] = useState(false);


//태그 눌렀을때 모달 이벤트
  const handleTagPress = (tag) => {
    handleSearch("tag"); //modalData변경
    setSelectedTag(tag);
    setModalVisible(true);
  };

  const closeModal = () => {
    setModalVisible(false);
    setSelectedTag(null);
  };


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
  };


  //탭바 눌렀을때 해당 내용으로 이동
  const handleTabPress = async (index) => {
    setActiveTab(index);
    scrollViewRef.current.scrollTo({ x: SCREEN_WIDTH * index * 0.9, animated: true });
  };

  //검색버튼 눌렀을때 contentData 변경
  const handleSearch = (type) => {
    //api를 통해 tagDada, userData, postData 또는 modalData 변경
    //GET : /search/post에서 type(title, tag)에 따른 데이터를 받는다
    handleTabPress(0);
    console.log("handleSearch");
  }

  return (
    <View style={styles.searchModalBackGround}>
      <View style={styles.searchModalContainer}>

        {/*닫기버튼*/}
        <TouchableOpacity
          style={styles.closeView}
          onPress={props.closeSearchModal}>
          <Image
            style={styles.closeImage}
            source={iconClose}
          />
        </TouchableOpacity>

        {/*검색창,검색버튼*/}
        <View style={styles.searchTopView}>
          <TextInput
            style={styles.searchInput}
            placeholder="검색어를 입력하세요"
            value={searchQuery}
            onChangeText={(text) => setSearchQuery(text)}
          />
          <TouchableOpacity onPress={handleSearch}>
          <Image
            style={styles.searchImage}
            source={iconSearch}
          />
          </TouchableOpacity>
        </View>

        {/*탭바*/}
        <View style={styles.searchMiddleView}>
          {['게시물', '이용자', '태그'].map((tab, index) => (
            <TouchableOpacity
              key={index}
              style={[
                styles.tabButton,
                activeTab === index && styles.activeTabButton,
              ]}
              onPress={() => handleTabPress(index)}>
              <Text style={styles.tabButtonText}>{tab}</Text>
            </TouchableOpacity>
          ))}
        </View>

        {/*내용*/}
        <ScrollView
          style={styles.searchScrollView}
          horizontal
          pagingEnabled
          showsHorizontalScrollIndicator={false}
          ref={scrollViewRef}>
          {[0, 1, 2].map((idx) => (
          <ScrollView
            key={idx}
            contentContainerStyle={styles.result}
            vertical
            showsVerticalScrollIndicator={false}>

            {/*게시물 결과 컨테이너 구조*/}
            {idx === 0 && (
              contentData[idx].map((item, index) => (
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
              )}

            
            {/*이용자 결과 컨테이너 구조*/}
            {idx === 1 && (
              contentData[idx].map((item, index) => (
                <TouchableOpacity style={styles.userContainer} onPress={handleUserPress} key={index}>
                  <Image style={styles.profileImage} source={{ uri: item.profileUrlImg }} />
                  <View style={styles.userInfo}>
                    <Text style={styles.nickname}>{item.nickname}</Text>
                  </View>
                </TouchableOpacity>
              ))
            )}


            {/*태그 결과 컨테이너 구조*/}
            {idx === 2 && (
              contentData[idx].map((item, index) => (
                <TouchableOpacity key={index} style={styles.tagContainer}
                  onPress={() => {
                    handleTagPress(item.tagNm);
                  }}
                >
                  <View style={styles.tagContent}>
                    <View style={styles.circle}></View>
                    <Text style={styles.hashSymbol}>#</Text>
                    <Text style={styles.tagName}>{item.tagNm}</Text>
                  </View>
                </TouchableOpacity>
              ))
            )}
          </ScrollView>
          ))}

                    {/* Modal */}
      <Modal
      animationType="slide"
      transparent={true}
      visible={isModalVisible}
      onRequestClose={closeModal}
    >
      <TouchableOpacity onPress={closeModal}>
        <Image
              style={styles.closeModalText}
              source={iconClose}
            />
          </TouchableOpacity>
      <View style={styles.modalContainer}>
      <ScrollView
            contentContainerStyle={styles.result}
            vertical
            showsVerticalScrollIndicator={false}>

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
    </Modal>
        </ScrollView>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
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
    backgroundColor: "white",
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

  
  //프로필 컨테이너 관련
  profileContainer: {
    borderRadius: 10,
    backgroundColor: '#e0e0e0',
    width: SCREEN_WIDTH * 0.9,
    margin: 10,
    padding: 10,
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  profileImage: {
    width: 50,
    height: 50,
    borderRadius: 25,
  },
  nickname: {
    textAlign: 'center',
    marginTop: 5,
  },
  postImage: {
    width: 150,
    height: 150,
    borderRadius: 10,
  },
  postTitle: {
    marginTop: 5,
    fontWeight: 'bold',
  },
  createdAt: {
    color: 'gray',
  },
  likeContainer: {
    alignItems: 'center',
  },
  likeCount: {
    marginTop: 5,
  },


  //이용자 컨테이너 관련
  userContainer: {
    borderRadius: 10,
    width: SCREEN_WIDTH*0.9,
    margin: 10,
    padding: 10,
    flexDirection: 'row',
    backgroundColor: '#e0e0e0',
    alignItems: 'center',
  },
  profileImage: {
    width: 50,
    height: 50,
    borderRadius: 25,
  },
  userInfo: {
    marginLeft: SCREEN_WIDTH*0.1,
  },
  nickname: {
    fontWeight: 'bold',
  },


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
    fontSize: 18,
    color: 'black',
    width: SCREEN_WIDTH * 0.06,
    height: SCREEN_WIDTH * 0.06,
    top: SCREEN_WIDTH * 0.21,
    right: SCREEN_WIDTH * 0.05,
    backgroundColor: 'white'
  },
});
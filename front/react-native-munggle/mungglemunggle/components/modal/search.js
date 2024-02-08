import React, {useState, useRef} from 'react';
import {StyleSheet, ScrollView, Text, View, Dimensions,
TouchableOpacity, Image, TextInput, Modal, ActivityIndicator
} from 'react-native';

import iconClose from "../../assets/icons/close1.png";
import iconSearch from "../../assets/icons/search.png";
import iconBornWhite from "../../assets/icons/bornWhite.png";

import SearchTagResult from './searchTagResult';
import PostDetail from './postDetail';
import FollowButton from '../followButton';
import DirectMessageButton from '../directMessageButton';
import ProfileCircle from '../profileCircle';

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
  "nickname": "멍1",
  "description": "안녕하세요 멍1 입니다.",
},
{
  "id": 2,
  "profileUrlImg": "http://www.evermodel.com/uploaded/model/414/d3d415e8cad046393ac6aa22f0bfd5983_slide.jpg",
  "nickname": "멍2",
  "description": "안녕하세요 멍2 입니다.",
},
{
  "id": 3,
  "profileUrlImg": "https://images.mypetlife.co.kr/content/uploads/2023/11/17133418/61fbb115-3845-4427-b72d-76c5e650cd3c.jpeg",
  "nickname": "멍3",
  "description": "안녕하세요 멍3 입니다.",
},
{
  "id": 4,
  "profileUrlImg": "http://www.evermodel.com/uploaded/model/414/d3d415e8cad046393ac6aa22f0bfd5980_slide.jpg",
  "nickname": "멍4",
  "description": "안녕하세요 멍4 입니다.",
  },
]
//테스트 post 데이터(추후 삭제)
const postData = [
  {
    "postId": 1,
    "postTitle": "멋진 내 강아지",
    "imageURLs": [
      "https://live.staticflickr.com/7669/17268903144_b5e9d79c4e_z.jpg",
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
        "https://cdn.imweb.me/thumbnail/20221027/f76c4c81b7bde.jpg",
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
        "https://blancs.co.kr/web/product/big/201906/1e94a6b1b4be68347b0cd84a79b482ba.jpg",
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
    ],
    "userId": 1,
    "profileImage": "https://images.mypetlife.co.kr/content/uploads/2023/11/17133418/61fbb115-3845-4427-b72d-76c5e650cd3c.jpeg",
    "nickname": "멍12멍",
    "likeCnt": 13,
    "isLiked": false,
    "createdAt": "2024-01-28T15:18:38.236335"
  },
];

export default function Search(props) {
  const [searchText, setSearchText] = useState('');
  const [activeTab, setActiveTab] = useState(0);
  const [contentData, setContentData] = useState([postData, userData, tagData]);
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
  };

  const searchView = () => {
    return (
      <View style={styles.searchTopView}>
        <TextInput
          style={styles.searchInput}
          placeholder="검색어를 입력하세요"
          value={searchText}
          onChangeText={(text) => setSearchText(text)}
        />
        <TouchableOpacity 
          style={styles.searchIconView}
          onPress={handleSearch}
        >
          <Image
            style={styles.searchIcon}
            source={iconSearch}
          />
        </TouchableOpacity>
      </View>
    );
  };

  const tabView = () => {
    return (
      <View style={styles.searchMiddleView}>
        {['게시물', '이용자', '태그'].map((tab, index) => (
          <TouchableOpacity
            key={index}
            style={[
              styles.tabButton,
              activeTab === index && styles.activeTabButton,
              index != 2 && {borderRightWidth: 1, borderColor: "gray"}
            ]}
            onPress={() => handleTabPress(index)}>
            <Text style={styles.tabButtonText}>{tab}</Text>
          </TouchableOpacity>
        ))}
      </View>
    );
  };

 const postContent = () => {
  return (
    <View style={styles.searchPostBottomView}>
      {postData && postData.map((post, index) => {
        const [isDetailModalOpen, setIsDetailModalOpen] = useState(false);
        
        const openDetailModal = () => { setIsDetailModalOpen(true) };

        const closeDetailModal = () => { setIsDetailModalOpen(false) };

        return(
          <View key={index} style={styles.searchPostListView}>
            <View style={styles.searchPostListViewLeftView}>
              <ProfileCircle 
                imageProfile={post.profileImage}
                nameProfile={post.nickname}
              />
              <View style={styles.searchPostListProfileButtonView}>
                <FollowButton />
                <DirectMessageButton />
              </View>
            </View>
            
            <View style={styles.searchPostListViewRightView}>
              <TouchableOpacity 
                style={styles.searchPostListImageView}
                onPress={() => openDetailModal()}
              >
                <Image 
                  style={styles.searchPostListImage}
                  src={post.imageURLs[0]} 
                />
              </TouchableOpacity>

              <View style={styles.searchPostListBottomView}>
                <View style={styles.searchPostListTextView}>
                  <Text style={styles.searchPostListTitle}>{post.postTitle}</Text>
                  <Text style={styles.searchPostListDate}>{post.createdAt}</Text>
                </View>
                <View style={styles.searchPostListIconView}>
                  <View style={styles.searchPostLikeCountView}>
                    <Text style={styles.searchPostLikeCountText}>12</Text>
                  </View>
                  <TouchableOpacity style={styles.searchPostLikeIcon}>
                    <Image 
                      style={styles.searchPostLikeIcon}
                      source={iconBornWhite}
                    />
                  </TouchableOpacity>
                </View>
              </View>
            </View>
            <Modal
              animationType="fade"
              transparent={true}
              visible={isDetailModalOpen}
              onRequestClose={() => closeDetailModal()}>
              <PostDetail closeDetailModal={closeDetailModal} post={post} />
            </Modal>
          </View>
        );
      })}
    </View>
  );
 }

 const userContent = () => {
  return (
    userData.map((user, index) => (
      <TouchableOpacity 
        style={styles.userContentView} 
        onPress={handleUserPress} 
        key={index}
      >
        <View style={styles.userContentImageView}>
          <Image 
            style={styles.userContentImage} 
            source={{ uri: user.profileUrlImg }} 
          />
        </View>

        <View style={styles.userContentNicknameView}>
          <Text style={styles.userContentNickname}>{user.nickname}</Text>
        </View>

        <View style={styles.userContentDescriptionView}>
          <Text style={styles.userContentDescription}>{user.description}</Text>
        </View>
      </TouchableOpacity>
    ))
  );
 }

  return (
    <View style={styles.searchModalBackGround}>
      <View style={styles.searchModalContainer}>
        <TouchableOpacity
          style={styles.closeView}
          onPress={props.closeSearchModal}>
          <Image
            style={styles.closeImage}
            source={iconClose}
          />
        </TouchableOpacity>

        {searchView()}

        {tabView()}
        <ScrollView>
          <ScrollView
            horizontal
            pagingEnabled
            showsHorizontalScrollIndicator={false}
            ref={scrollViewRef}
          >
            {[0, 1, 2].map((idx) => (
              <View
                key={idx}
                style={styles.contents}
              >

                {idx === 0 && 
                    postContent()
                  }

                {/*이용자 결과 컨테이너 구조*/}
                {idx === 1 && (
                  userContent()
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
              </View>
            ))}
            <Modal
              animationType="slide"
              transparent={true}
              visible={isModalVisible}
              onRequestClose={closeModal}
            >
              <SearchTagResult closeModal={closeModal} />
            </Modal>
          </ScrollView>
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
    marginBottom: SCREEN_HEIGHT * 0.03,
    position: "relative",
    borderRadius: 30,
    borderWidth: 1,
    borderColor: "lightgrey",
    backgroundColor: "rgb(255, 255, 240)",
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

// searchView
  searchTopView: {
    marginTop: SCREEN_HEIGHT * 0.05,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.05,
    justifyContent: "center",
    alignItems: "center",
    position: "relative",
    marginVertical: SCREEN_HEIGHT * 0.02,
  },
  searchInput: {
    width: SCREEN_WIDTH * 0.7,
    height: SCREEN_HEIGHT * 0.06,
    backgroundColor: "white",
    borderWidth: 1,
    borderColor: "gray",
    borderRadius: 10,
    paddingLeft: SCREEN_WIDTH * 0.03,
    fontSize: 18,
  },
  searchIconView: {
    position: "absolute",
    width: SCREEN_WIDTH * 0.07,
    height: SCREEN_WIDTH * 0.07,
    right: SCREEN_WIDTH * 0.12,
  },
  searchIcon: {
    width: SCREEN_WIDTH * 0.07,
    height: SCREEN_WIDTH * 0.07,
  },

 
  searchMiddleView: {
    marginTop: SCREEN_HEIGHT * 0.005,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.07,
    flexDirection: "row",
  },

// tab view
  tabButton: {
    alignItems: "center",
    justifyContent: "center",
    width: SCREEN_WIDTH * 0.3,
    height: SCREEN_HEIGHT * 0.06,
    backgroundColor: "rgb(249, 250, 208)",
  },
  activeTabButton: {
    backgroundColor: "rgb(235, 233, 152)",
  },

  tabButtonText: {
    fontSize: 18,
    fontWeight: "600",
  },

  contents: {
    width: SCREEN_WIDTH * 0.9,
    alignItems: 'center',
  },

// post content
  searchPostBottomView: {
    width: SCREEN_WIDTH * 0.9,
    alignItems: "center",
  },
  searchPostListView: {
    marginVertical: 10,
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.32,
    flexDirection: "row",
    alignItems: "flex-start",
    justifyContent: "space-between",
    backgroundColor: "rgb(249, 250, 208)",
    borderRadius: 20,
    paddingVertical: SCREEN_HEIGHT * 0.01,
  },
  searchPostListViewLeftView: {
    width: SCREEN_WIDTH * 0.24,
    height: SCREEN_HEIGHT * 0.2,
    backgroundColor: "rgb(249, 250, 208)",
    borderRadius: 7,
    alignItems: "center",
    justifyContent: "space-around",
  },
  searchPostListProfileButtonView: {
    flexDirection: "row",
    width: SCREEN_WIDTH * 0.235,
    justifyContent: "space-between",
  },
  searchPostListViewRightView: {
    width: SCREEN_WIDTH * 0.6,
    height: SCREEN_HEIGHT * 0.3,
    alignItems: "center",
    backgroundColor: "rgb(249, 250, 208)",
    borderRadius: 9,
    justifyContent: "space-around",
  },
  searchPostListImageView: {
    width: SCREEN_WIDTH * 0.60,
    height: SCREEN_HEIGHT * 0.23,
    backgroundColor: "rgb(249, 250, 208)",
    borderRadius: 20,
  },
  searchPostListImage: {
    width: SCREEN_WIDTH * 0.60,
    height: SCREEN_HEIGHT * 0.22,
    borderRadius: 20,
  },
  searchPostListBottomView: {
    width: SCREEN_WIDTH * 0.60,
    height: SCREEN_HEIGHT * 0.07,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "flex-start",
  },
  searchPostListTextView: {
    width: SCREEN_WIDTH * 0.48,
    height: SCREEN_HEIGHT * 0.06,
    justifyContent: "space-between",
  },
  searchPostListTitle: {
    fontSize: 19,
    fontWeight: "600",
  },
  searchPostListDate: {
    fontSize: 13,
    color: "grey",
  },

  searchPostListIconView: {
    width: SCREEN_WIDTH * 0.12,
    height: SCREEN_HEIGHT * 0.17,
    flexDirection: "row",
    justifyContent: "space-between",
  },
  searchPostLikeIcon: {
    width: SCREEN_WIDTH * 0.055,
    height: SCREEN_WIDTH * 0.055,
    
  },
  searchPostLikeCountView: {
    width: SCREEN_WIDTH * 0.055,
    height: SCREEN_WIDTH * 0.055,
    justifyContent: "flex-end",
  },
  searchPostLikeCountText: {
    fontSize: 16,
    color: "rgb(146, 146, 0)",
  },

//프로필 컨테이너 관련
  userContentView: {
    borderRadius: 10,
    backgroundColor: '#e0e0e0',
    width: SCREEN_WIDTH * 0.85,
    height: SCREEN_HEIGHT * 0.1,
    flexDirection: 'row',
    marginVertical: SCREEN_HEIGHT * 0.01,
  },
  userContentImageView: {
    width: SCREEN_WIDTH * 0.2,
    height: SCREEN_HEIGHT * 0.1,
    justifyContent: "center",
    alignItems: "center",
  },
  userContentImage: {
    width: SCREEN_WIDTH * 0.17,
    height: SCREEN_WIDTH * 0.17,
    borderRadius: 100,
    borderWidth: 1,
    borderColor: "lightgrey",
  },
  userContentNicknameView: {
    width: SCREEN_WIDTH * 0.2,
    height: SCREEN_HEIGHT * 0.1,
    justifyContent: "flex-end",
    marginLeft: SCREEN_WIDTH * 0.005,
  },
  userContentNickname: {
    marginBottom: SCREEN_HEIGHT * 0.02,
    fontSize: 18, 
    fontWeight: "500",
  },
  userContentDescriptionView: {
    width: SCREEN_WIDTH * 0.44,
    height: SCREEN_HEIGHT * 0.1,
    justifyContent: "center",
    alignItems: "flex-start",
  },
  userContentDescription: {
    fontSize: 16,
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

  searchBottomView: {
    marginBottom: SCREEN_HEIGHT * 0.01,
    width: SCREEN_WIDTH * 0.9,
    height: SCREEN_HEIGHT * 0.215,
    alignItems: "center"
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
    width: SCREEN_WIDTH * 0.06,
    height: SCREEN_WIDTH * 0.06,
    top: SCREEN_WIDTH * 0.21,
    right: SCREEN_WIDTH * 0.05,
  },
});
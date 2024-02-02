import React from "react";
import { View, Text, Button, ScrollView, StyleSheet, Dimensions } from "react-native";
import { WebView } from "react-native-webview";

const { width: SCREEN_WIDTH, height: SCREEN_HEIGHT } = Dimensions.get("window")

const apiKey = 'c688164e37f35d82c0237627a526de76'

const html = `
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=${apiKey}&libraries=services,clusterer,drawing"></script> 
    </head>
    <body >
        <div id="map" style="width:395px;height:683px;"></div>
        <script type="text/javascript">
            (function () {
                const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
                const options = { //지도를 생성할 때 필요한 기본 옵션
                    center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
                    level: 3 //지도의 레벨(확대, 축소 정도)
                };
                
                const map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
                
                // 주소-좌표 변환 객체를 생성합니다
                const geocoder = new kakao.maps.services.Geocoder();
            })();
        </script>       
    </body>
</html>    
`;


export default function WalkScreen ({ navigation }) {
  return (
    <View style={styles.walkMainContainer}>
      <WebView
        style={styles.walkMainMap}
        source={{
          html: html
        }}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  walkMainContainer: {
    flex: 1,
    width: SCREEN_WIDTH,
    height: SCREEN_HEIGHT * 0.80,
  },
  walkMainMap: {
    flex: 0,
    width: SCREEN_WIDTH * 1.28,
    height: SCREEN_HEIGHT * 0.82,
  }
})
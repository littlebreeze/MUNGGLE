import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
  name: "user",
    initialState: {
    username: "",
    id: "",
    isLoading: false,
    isLogin: null,        
  },
  reducers: {
    // 로그인 성공
    doLoginUser: (state, action) => {
        state.username = action.payload.username;
        state.id = action.payload.id;
        return state;
    },
    // 로그인 실패
    doNotLoginUser: (state, action) => {
        state.username = "";
        state.id = "";
        return state;
    }
  }
});
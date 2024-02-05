import React from "react";
import { View, Text, Button, ScrollView } from "react-native";
import { login } from './login.js';

export default function loginPage () {

  const [id, setId] = useState('');
  const [password, setPassword] = useState('');
  const handleLogin = () => {
    login(id, password, dispatch);
  };

  return (
    <ScrollView style={{ flex: 1 }}>
      <TextInput
        placeholder="Enter ID"
        value={id}
        onChangeText={text => setId(text)}
        style={{ height: 40, borderColor: 'gray', borderWidth: 1, margin: 10, padding: 5 }}
      />
      <TextInput
        placeholder="Enter Password"
        value={password}
        onChangeText={text => setPassword(text)}
        secureTextEntry={true}
        style={{ height: 40, borderColor: 'gray', borderWidth: 1, margin: 10, padding: 5 }}
      />
      <Button
        title="Login"
        onPress={handleLogin}
      />
      <Button
        title="Logout"
        onPress={handleLogout}
      />
    </ScrollView>
  );
};
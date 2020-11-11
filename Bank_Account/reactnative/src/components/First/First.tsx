import React from 'react';
import {View, Text, Pressable} from 'react-native';
import Config from 'react-native-config';
import {RootStackParamList} from '../../App.types';
import {styles} from './First.styles';
import {Props} from './First.types';

const First = ({navigation}: Props) => {
  function handleRoute(routeName: keyof RootStackParamList) {
    navigation.navigate(routeName);
  }

  return (
    <View style={styles.flexcenter}>
      <Pressable
        onPress={() => handleRoute('Login')}
        style={({pressed}) => [
          {
            backgroundColor: pressed ? 'rgb(210, 230, 255)' : 'white',
          },
          styles.wrapperPressable,
        ]}>
        <Text>Login</Text>
      </Pressable>
      {/* <Pressable
        onPress={() => handleRoute('Register')}
        style={({pressed}) => [
          {
            backgroundColor: pressed ? 'rgb(210, 230, 255)' : 'white',
          },
          styles.wrapperPressable,
        ]}>
        <Text>Register</Text>
      </Pressable> */}
      <Text>{Config.baseUrl}</Text>
    </View>
  );
};

export default First;

import React from 'react';
import {View, Text, StyleSheet} from 'react-native';
import Config from 'react-native-config';

const Home = () => {
  return (
    <View style={styles.flexcenter}>
      <Text>{Config.baseUrl}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  flexcenter: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});

export default Home;

import React from 'react';
import {View, Text, StyleSheet} from 'react-native';

const Home = () => {
  return (
    <View style={styles.flexcenter}>
      <Text>It works</Text>
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

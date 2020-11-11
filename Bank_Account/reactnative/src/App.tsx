import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import First from './components/First/First';
import Login from './components/Login/Login';
import {RootStackParamList} from './App.types';
import {useSelector} from 'react-redux';
import {selectIsLoggedIn} from './features/authorization/authorizationSlice';
import Home from './components/Home/Home';

const Stack = createStackNavigator<RootStackParamList>();

const App = () => {
  const isLoggedIn = useSelector(selectIsLoggedIn);

  console.log(isLoggedIn);

  return (
    <NavigationContainer>
      <Stack.Navigator>
        {!isLoggedIn ? (
          <>
            <Stack.Screen
              name="First"
              component={First}
              options={{headerShown: false}}
            />
            <Stack.Screen
              name="Login"
              component={Login}
              options={{headerShown: false}}
            />
          </>
        ) : (
          <Stack.Screen
            name="Home"
            component={Home}
            options={{headerShown: false}}
          />
        )}
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;

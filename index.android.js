/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  TouchableOpacity
} from 'react-native';
import Share from './Utils';

export default class ShareDemo extends Component {

  openShare = ()=>{
      let url = 'https://www.baidu.com/';
      let title = '百度一下就知道';
      let imageUrl = 'https://www.baidu.com/img/logo_76bcbf7d7c327b5f29dd98aa4d6e9a1e.png';
      let desc = '哈哈哈哈哈哈';
      Share(url,title,imageUrl,desc);
  }

  render() {
    return (
      <View style={styles.container}>
        <TouchableOpacity onPress={this.openShare}>
          <Text style={styles.welcome}>
            打开分享菜单
          </Text>
        </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('ShareDemo', () => ShareDemo);

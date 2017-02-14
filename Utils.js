/**
 * Created by liaoye on 2017/2/14.
 */
import React,{Component} from 'react';
import {
    NativeModules
} from 'react-native';
var ShareModules = NativeModules.ShareModules;
export default function share(url,title,imageUrl,desc){
        ShareModules.shareOpen({
            url:url,
            title:title,
            thumb:imageUrl,
            description:desc
        },()=>{
            console.log('调用了这个方法')
        })

}
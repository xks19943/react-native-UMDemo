package com.sharedemo.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.util.Map;

/**
 * Created by liaoye on 2017/2/13.
 */
public class ShareModule extends ReactContextBaseJavaModule implements ActivityEventListener{
    public ShareModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ShareModules";
    }


    @ReactMethod
    public void shareOpen(final ReadableMap options, final Callback callback){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                String url= options.getString("url");
                String title= options.getString("title");
                String thumb= options.getString("thumb");
                String description= options.getString("description");

                Log.d(ShareModule.this.getName(), "shareOpenOptions: "+options.getString("url"));
                UMImage image = new UMImage(getCurrentActivity(), thumb);
                image.setTitle(title);
                image.setDescription(description);
                image.setThumb(image);

                ShareBoardConfig config = new ShareBoardConfig();
                config.setTitleText("分享到");
                config.setIndicatorVisibility(false);
                config.setShareboardBackgroundColor(Color.WHITE);
                config.setCancelButtonText("取消");
                config.setCancelButtonTextColor(Color.WHITE);
                config.setCancelButtonBackground(Color.parseColor("#0090ff"));

                new ShareAction(getCurrentActivity()).setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.SINA)
                        .withTitle(title)
                        .withMedia(image)
                        .withTargetUrl(url)
                        .withText(description)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onResult(SHARE_MEDIA platform) {
                                Log.d(this.getClass().getName(),"platform"+platform);
                                if(platform.name().equals("WEIXIN")){
                                    Toast.makeText(getCurrentActivity(), "微信好友分享成功啦!", Toast.LENGTH_SHORT).show();
                                }else if(platform.name().equals("WEIXIN_CIRCLE")){
                                    Toast.makeText(getCurrentActivity(),  "微信朋友圈分享成功啦!", Toast.LENGTH_SHORT).show();
                                }else if(platform.name().equals("QQ")){
                                    Toast.makeText(getCurrentActivity(),  "QQ好友分享成功啦!", Toast.LENGTH_SHORT).show();
                                }else if(platform.name().equals("SINA")){
                                    Toast.makeText(getCurrentActivity(),  "新浪微博分享成功啦!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(SHARE_MEDIA platform, Throwable t) {
                                Log.e(ShareModule.class.getName(),"分享失败啦:",t);
                                if(platform.name().equals("WEIXIN")){
                                    Toast.makeText(getCurrentActivity(), "微信分享失败啦", Toast.LENGTH_SHORT).show();
                                }else if(platform.name().equals("WEIXIN_CIRCLE")){
                                    Toast.makeText(getCurrentActivity(),  "微信朋友圈分享失败啦", Toast.LENGTH_SHORT).show();
                                }else if(platform.name().equals("QQ")){
                                    Toast.makeText(getCurrentActivity(),  "QQ好友分享失败啦!", Toast.LENGTH_SHORT).show();
                                }else if(platform.name().equals("SINA")){
                                    Toast.makeText(getCurrentActivity(),  "新浪微博分享失败啦!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA platform) {

                            }
                        })
                        .open(config);
                return;
            }
        });
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(activity).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}

## 使用NDK c++建立一个Android应用


### 一.工具
* ADT(集成了eclipse,cdt,ndk plug-in)
* NDK (用它来编译c/c++程序）
* JDK (Java开发包）
* ANT（eclipse打包插件）


### 二.配置环境变量
* 1.JAVA_HOME=C:\Program Files\Java\jdk1.7.0_71
* 2.ANT_HOME=D:\apache\ant
* 3.ANDROID_SDK_HOME=D:\SDK\android-sdk
* 4.NDK_HOME=D:\SDK\android-ndk-r10e


添加Path变量
JAVA_HOME%\bin;%NDK_HOME%;%ANDROID_SDK_HOME%\platform-tools;%ANT_HOME%\bin

### 三.环境监察
打开控制台，依次输入
* javac –version (检验JDK安装)
* ant –version (检验ant安装）
* ndk-build –version (检验NDK安装）
* adb devices  ( 检验android platforms tools安装）

如果以上命令都是有效命令，安装完成


### 四.为Eclipse分别配置SDK和NDK位置
* Windows->Preferences->Android设置SDK路径
* Windows->Preferences->Android->NDK设置NDK路径

### 五.创建Android应用
打开eclipse,File->new->Project->Android->Android Application Project 创建Android应用。

### 六.让Android工程支持C++编译
右键单击工程，Android Tools->Add Native Support，在弹出对话框中直接点击finish,此时eclipse会自动在工程文件中创建jni文件，含对应配置文件和源文件


### 七.编写JNI及调用代码

```c
//jni/hellojni.cpp

#include <jni.h>

jstring JniString(JNIEnv* env) {
	return env->NewStringUTF("Hello,NDK");
}
extern "C"
{
	JNIEXPORT jstring JNICALL Java_com_example_hellondk_NDKUtils_stringFromJNI(JNIEnv* env,jobject o){
	return JniString(env);
}
}

```

```java
//com.example.hellondk.NDKUtils

package com.example.hellondk;

public class NDKUtils {
	public static native String  stringFromJNI();
	static {
	      System.loadLibrary("hellondk");
	}
}

```

```java
//com.example.hellondk.MainActivity

package com.example.hellondk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView  textView01 = (TextView)findViewById(R.id.textView01);
		textView01.setText(NDKUtils.stringFromJNI());
	}
}

```




### 八.编译生成so
* 1.通过Eclipse编译
右键单击工程进行Clean Project，然后再执行Build Project，即可在libs目录下看到生成的so文件
* 2.也可以通过命令行编译(定位到jni目录下)，执行命令“ndk-build clean”进行清理，执行“ndk-build”进行编译


### 九.编译支持多架构
* 在jni目录下创建Application.mk文件
配置 APP_ABI := all 重新编译，即可在libs目录下看到编译生成所有平台so
* 也可以指定需编译支持的架构平台
APP_ABI := armeabi armeabi-v7a x86 mips arm64-v8a x86_64 mips64

如果你的工程没有Application.mk文件，则系统会用默认的，$(NDK_ROOT)/build/core/default-application.mk


### 十.本示例代码
*  https://github.com/huligong1234/hellondk

### 附：参考资料
*  [如何使用NDK c++建立第一个Android应用](http://jingyan.baidu.com/article/90808022f03fc5fd91c80fbf.html)
*  [Android 开发 之 JNI入门 - NDK从入门到精通](http://blog.csdn.net/shulianghan/article/details/18964835)
*  [Android NDK开发指南---Application.mk文件](http://hualang.iteye.com/blog/1149359)
*  [NDK Build 用法（NDK Build）](http://blog.csdn.net/smfwuxiao/article/details/8523087)
*  [通过 JNI 调用 OpenSSL 实现加密解密](http://www.oschina.net/translate/encryption-decryption-invoking-openssl-api-through-jni-calls?print)
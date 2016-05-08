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

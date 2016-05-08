LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := hellondk
LOCAL_SRC_FILES := hellondk.cpp

include $(BUILD_SHARED_LIBRARY)

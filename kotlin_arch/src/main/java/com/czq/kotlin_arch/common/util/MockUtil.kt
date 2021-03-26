package com.czq.kotlin_arch.common.util

import android.content.Context
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.google.gson.reflect.TypeToken

class MockUtil {
    companion object {
        inline fun <reified T> getMockModelList(context: Context, fileName: String, clazz: Class<T> ): List<T> {
            val jsonStr = AssetUtil.getStringFromFile(context, fileName)
            //  List<T> list = GsonUtils.parseJSON(jsonStr, new TypeToken<List<T>>() {}.getType());
            return GsonUtils.parseString2List(jsonStr,clazz)
        }

        inline fun <reified T> getMockModel(context: Context, fileName: String, clazz: Class<T> ): T {
            val jsonStr = AssetUtil.getStringFromFile(context, fileName)
            return GsonUtils.parseJSON(jsonStr,clazz)
            //return JSON.parseObject(jsonStr,clazz)
        }

        inline fun <reified T> getMockModel(context: Context, fileName: String, typeReference: TypeToken<T>): T {
            val jsonStr = AssetUtil.getStringFromFile(context, fileName)
            return GsonUtils.parseJSONArray(jsonStr,typeReference.type)
            //  return JSON.parseObject(jsonStr,typeReference)
        }
    }
}
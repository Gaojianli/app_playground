package chapter.android.aweme.ss.com.homework.model

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream
import java.util.*

/**
 * Pull解析Xml
 */
object PullParser {
    /**
     * @param is inputStream
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun pull2xml(`is`: InputStream?): List<Message?>? {
        var list: MutableList<Message?>? = null
        var msg: Message? = null
        //创建xmlPull解析器
        val parser = Xml.newPullParser()
        ///初始化xmlPull解析器
        parser.setInput(`is`, "utf-8")
        //读取文件的类型
        var type = parser.eventType
        //无限判断文件类型进行读取
        while (type != XmlPullParser.END_DOCUMENT) {
            when (type) {
                XmlPullParser.START_TAG -> when (parser.name) {
                    "messages" -> {
                        list = ArrayList()
                    }
                    "message" -> {
                        msg = Message()
                    }
                    "title" -> { //获取title属性
                        val isOfficial = parser.getAttributeValue(null, "isOfficial")
                        msg!!.isOfficial = "true" == isOfficial
                        val title = parser.nextText()
                        msg.title = title
                    }
                    "time" -> { //time
                        val time = parser.nextText()
                        msg!!.time = time
                    }
                    "hashtag" -> { //hashTag
                        val hashTag = parser.nextText()
                        msg!!.description = hashTag
                    }
                    "icon" -> { //icon
                        val icon = parser.nextText()
                        msg!!.icon = icon
                    }
                }
                XmlPullParser.END_TAG -> if ("message" == parser.name) list!!.add(msg)
            }
            //继续往下读取标签类型
            type = parser.next()
        }
        return list
    }

}
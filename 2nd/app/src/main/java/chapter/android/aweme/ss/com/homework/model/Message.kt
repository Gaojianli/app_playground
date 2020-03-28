package chapter.android.aweme.ss.com.homework.model

/**
 * 消息  data class
 */
class Message {
    //是否官方
    var isOfficial = false
    //title
    var title: String? = null
    //时间
    var time: String? = null
    //描述
    var description: String? = null
    //icon
    var icon: String? = null

    override fun toString(): String {
        return "Message{isOfficial=$isOfficial, title='$title', time='$time', description='$description', icon='$icon'}"
    }
}
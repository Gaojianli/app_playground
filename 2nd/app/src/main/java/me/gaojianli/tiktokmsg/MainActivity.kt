package me.gaojianli.tiktokmsg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var msgList: MutableList<Msg> = ArrayList<Msg>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        val recycleView = findViewById<RecyclerView>(R.id.recyle_view)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = MsgAdapter(msgList)
    }

    private fun initData() {
        val nameList =
            mapOf("张三" to R.mipmap.zhang3, "李四" to R.mipmap.li4, "王二麻子" to R.mipmap.wang2mazi, "赵五" to R.mipmap.zhao5)
        for (item in nameList) {
            msgList.add((Msg(item.key, "你好，我是${item.key}", item.value)))
        }
    }
}

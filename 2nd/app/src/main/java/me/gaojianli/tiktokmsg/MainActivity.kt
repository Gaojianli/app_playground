package me.gaojianli.tiktokmsg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var msgList:MutableList<Msg> = ArrayList<Msg>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        val recycleView=findViewById<RecyclerView>(R.id.recyle_view)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter=MsgAdapter(msgList)
    }

    private fun initData(){
        msgList.add(Msg("张三","你好，我是张三",R.mipmap.zhang3))
        msgList.add(Msg("李四","你好，我是李四",R.mipmap.li4))
        msgList.add(Msg("王二麻子","你好，我是王二麻子",R.mipmap.wang2mazi))
        msgList.add(Msg("赵五","你好，我是赵五",R.mipmap.zhao5))
    }
}

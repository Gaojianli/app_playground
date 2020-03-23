package me.gaojianli.tiktokmsg

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MsgAdapter(private val mMsgList: List<Msg>) : RecyclerView.Adapter<MsgAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatarImage: ImageView = view.findViewById(R.id.avatar_image) as ImageView
        var userName: TextView = view.findViewById(R.id.user_name)
        var summary: TextView = view.findViewById(R.id.summary)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgAdapter.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.msg_item, parent, false)
        val holder = ViewHolder(itemView)
        holder.itemView.setOnClickListener { v: View ->
            val intent = Intent(v.context, MsgDetails::class.java)
            intent.putExtra("index", holder.adapterPosition)
            v.context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount(): Int = mMsgList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMsg = mMsgList[position]
        holder.summary.text = currentMsg.summary
        holder.avatarImage.setImageResource(currentMsg.ImageID)
        holder.userName.text = currentMsg.name
    }

}
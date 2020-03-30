package com.example.chapter3.homework

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.airbnb.lottie.LottieAnimationView


class PlaceholderFragment : Fragment() {
    private var loadingView: LottieAnimationView? = null
    private var friendListView: ListView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_placeholder, container, false)
        val data = arguments?.getStringArray("data")!!
        loadingView = view.findViewById(R.id.loading_view)
        friendListView = ListView(context)
        friendListView!!.adapter = ArrayAdapter(context!!, android.R.layout.simple_expandable_list_item_1, data)
        friendListView!!.visibility = View.VISIBLE
        friendListView!!.alpha = 0f
        (view as ViewGroup).addView(friendListView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view!!.postDelayed({
            // 这里会在 5s 后执行
            val fadeOutAnimator = AnimatorInflater.loadAnimator(context, R.animator.fade_out)
            val fadeInAnimator = AnimatorInflater.loadAnimator(context, R.animator.fade_in)
            fadeOutAnimator.setTarget(loadingView)
            loadingView?.visibility = View.VISIBLE
            fadeInAnimator.setTarget(friendListView)
            val aniSet = AnimatorSet()
            aniSet.playTogether(fadeInAnimator, fadeOutAnimator)
            aniSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    val container = (loadingView?.parent as ViewGroup)
                    container.removeView(loadingView)
                }

                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationRepeat(animation: Animator?) {}
            })
            aniSet.start()
        }, 5000)
    }
}
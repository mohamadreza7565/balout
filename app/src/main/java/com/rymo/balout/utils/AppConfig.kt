package com.rymo.balout.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AppConfig {
    companion object {
        const val UP_SCROLL = "UP_SCROLL"
        const val DOWN_SCROLL = "DOWN_SCROLL"
        const val LEFT_SCROLL = "LEFT_SCROLL"
        const val RIGHT_SCROLL = "RIGHT_SCROLL"
    }

    fun openBrowser(context: Context, link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(browserIntent)
    }

    fun checkScrollRecyclerView(recyclerView: RecyclerView,
                                 onScrollRecyclerView: OnScrollRecyclerView ): AppConfig {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dx > 0) {
                    onScrollRecyclerView.onScrollChanged(AppConfig.RIGHT_SCROLL)
                } else if (dx < 0) {
                    onScrollRecyclerView.onScrollChanged(AppConfig.LEFT_SCROLL)
                } else {
                    // No Horizontal Scrolled
                }
                if (dy > 0) {
                    onScrollRecyclerView.onScrollChanged(AppConfig.DOWN_SCROLL)
                    onScrollRecyclerView.isLastItemScroll(isLastItemDisplaying(recyclerView))
                } else if (dy < 0) {
                    onScrollRecyclerView.onScrollChanged(AppConfig.UP_SCROLL)
                } else {
                    // No Vertical Scrolled
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                    }
                    RecyclerView.SCROLL_STATE_DRAGGING -> {
                    }
                    RecyclerView.SCROLL_STATE_SETTLING -> {
                    }
                }
            }
        })
        return this
    }

    fun isLastItemDisplaying(recyclerView: RecyclerView): Boolean {
        if (recyclerView.adapter!!.itemCount != 0) {
            val lastVisibleItemPosition =
                (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.adapter!!
                    .itemCount - 1
            ) {
                return true
            }
        }
        return false
    }


    interface OnScrollRecyclerView {
        fun isLastItemScroll(isLastItem: Boolean)
        fun onScrollChanged(directionScroll: String?)
    }


}
package com.kotlin.project.ui.repos.adapter.viewholder

import android.view.View
import com.kotlin.project.base.BaseViewHolder
import com.kotlin.project.mvp.model.bean.RepoBean
import com.kotlin.project.utils.loadRound
import kotlinx.android.synthetic.main.item_repos.view.*

class ReposViewHolder(itemView: View) : BaseViewHolder(itemView) {

    companion object {
        private const val TAG = "ReposViewHolder"
    }

    override fun bindData(position: Int, item: Any?) {
        with(itemView) {
            if (item != null && item is RepoBean) {
                iv_avatar.loadRound(item?.owner?.avatar_url, true)
                tv_name.text = item.name
                tv_description.text = item.description

                // 为ViewHolder的视图定义点击监听器
                setOnClickListener {
                    LogUtils.d(TAG, "Element $adapterPosition clicked.")
                }
            }
        }
    }
}
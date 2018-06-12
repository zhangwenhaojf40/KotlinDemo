package hao.wen.zhang.kotlindemo.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import hao.wen.zhang.kotlindemo.R
import hao.wen.zhang.kotlindemo.module.FocusBean

import hao.wen.zhang.kotlindemo.module.FollowBean

/**
 * 作者：ZWH
 * 创建日期： 2018/6/11 0011   下午 4:20
 * 描述说明：
 */
class FocusItemAdapter(data: List<FollowBean.Item.Data.Item>, layout: Int) : BaseQuickAdapter<FollowBean.Item.Data.Item, BaseViewHolder>(layout, data) {
    lateinit var str: String

    override fun convert(helper: BaseViewHolder, item: FollowBean.Item.Data.Item) {
        helper.setText(R.id.tv_title, item.data.title)
        str = "#"
        for (tag in item.data.tags) {
            str = str + tag.name + "/"
        }
        helper.setText(R.id.tv_tag, str)
        var iv_cover_feed = helper.getView<ImageView>(R.id.iv_cover_feed)
        Glide.with(mContext).load(item.data.cover.detail).into(iv_cover_feed)

    }
}
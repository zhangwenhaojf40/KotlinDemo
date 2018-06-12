package hao.wen.zhang.kotlindemo.view

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import hao.wen.zhang.kotlindemo.R
import hao.wen.zhang.kotlindemo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_me_layout.*

/**
 * 作者：ZWH
 * 创建日期： 2018/6/5 0005   下午 3:01
 * 描述说明：
 */
class MeFragment : BaseFragment() {
    var items= listOf<String>("我的消息", "我的缓存", "我的下载", "意见反馈")
    var adapter=MeAdapter( android.R.layout.simple_list_item_1,items)
    override fun resetNet() {
    }

    override fun initData() {
        setAdapter()

    }

    private fun setAdapter() {
        mRecyclerView.layoutManager=LinearLayoutManager(activity)
        mRecyclerView.adapter=adapter
        adapter.setOnItemClickListener { adapter, view, position ->

            Toast.makeText(activity,adapter.data.get(position) as String ,Toast.LENGTH_LONG).show()
        }


    }


    override fun initView(view: View) {

    }

    override fun getRes(): Int {
        return R.layout.fragment_me_layout
    }
    companion object {

        fun newInstans(): BaseFragment {
            return MeFragment()

        }
    }
    class MeAdapter(res:Int,item:List<String>) :BaseQuickAdapter<String,BaseViewHolder>(res,item){
        override fun convert(helper: BaseViewHolder, item: String) {
            helper.setText(android.R.id.text1,item)
        }


    }
}
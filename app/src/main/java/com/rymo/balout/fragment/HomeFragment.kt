package com.rymo.balout.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rymo.balout.R
import com.rymo.balout.adapter.NewsRvAdapter
import com.rymo.balout.model.Articles
import com.rymo.balout.model.News
import com.rymo.balout.utils.AppConfig
import com.rymo.balout.view_model.HomeViewModel
import com.rymo.ciel.app.RetrofitRequestOptions
import com.rymo.ciel.dao.NewsDao
import com.rymo.ciel.database.AppDatabase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    var viewModel = HomeViewModel(RetrofitRequestOptions.getAPI())
    lateinit var disposable: Disposable
    lateinit var adapter: NewsRvAdapter
    lateinit var db: AppDatabase
    lateinit var newsDao: NewsDao
    var list: ArrayList<Articles> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getAppDatabase(requireContext())
        newsDao = db.getNewsDao()

        AppConfig().checkScrollRecyclerView(rv_news, object : AppConfig.OnScrollRecyclerView {
            override fun isLastItemScroll(isLastItem: Boolean) {
                if (isLastItem) {
                    getNews(false)
                }
            }

            override fun onScrollChanged(directionScroll: String?) {
                // Nothing to do
            }

        })


        rv_news.layoutManager = LinearLayoutManager(context)
        adapter = NewsRvAdapter(requireContext(), list)
        rv_news.adapter = adapter

        list.addAll(newsDao.getAll())
        adapter.notifyDataSetChanged()

        getNews(false)

    }

    private fun getNews(loading: Boolean) {
        viewModel.getNews("us", loading)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<News> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(t: News) {

                    list.addAll(t.articles)

                    newsDao.nukeTable()

                    for (i: Int in 0..t.articles.size - 1)
                        newsDao.insert(t.articles[i])

                    adapter.notifyDataSetChanged()

                }

                override fun onError(e: Throwable) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

            })
    }

}
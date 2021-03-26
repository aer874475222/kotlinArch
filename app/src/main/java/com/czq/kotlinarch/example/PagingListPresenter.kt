package com.czq.kotlinarch.example

import android.annotation.SuppressLint
import autodispose2.autoDispose
import com.czq.kotlin_arch.basePage.base.BasePagingPrensenterImpl
import com.czq.kotlin_arch.paging.PagingStrategy
import com.czq.kotlin_arch.paging.normal.NormalPagingInfo
import com.czq.kotlin_arch.paging.normal.NormalPagingStrategy
import com.czq.kotlinarch.data.converter.ChallengeRecomondCoverter
import com.czq.kotlinarch.data.remote.RemoteDataRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

open class PagingListPresenter(override val mView: PagingListContact.PagingListView) : BasePagingPrensenterImpl(mView),
    PagingListContact.PagingListPresenter {

    val mRemoteDataRepository: RemoteDataRepository by lazy {
        RemoteDataRepository()
    }

    override fun getPagingStrategy(): PagingStrategy? {
        return NormalPagingStrategy()
    }

    @SuppressLint("CheckResult")
    override fun onLoadData(pagingStrategy: PagingStrategy?) {
        val pageInfo = pagingStrategy!!.getPageInfo() as NormalPagingInfo
        mRemoteDataRepository.getChallengeRecommond(pageInfo.pageNum, pageInfo.pageSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(mView.autoDispose())
            .subscribe({ it ->
                if (pageInfo.isFirstPage()) {
                    datasource.clear()
                    pagingList.clear()
                }
                pagingList.addAll(it?.listData?: arrayListOf())//用于计算分页的数据
                it.listData?.forEach {
                    datasource.addAll(ChallengeRecomondCoverter.convert(it))//真实显示在列表上的数据
                }
                loadSuccess(it)
            }, {
                loadFail(it)
            })
    }
}
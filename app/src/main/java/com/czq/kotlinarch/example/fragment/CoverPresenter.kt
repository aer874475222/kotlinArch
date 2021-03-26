package com.czq.kotlinarch.example.fragment

import autodispose2.autoDispose
import com.czq.kotlinarch.data.remote.RemoteDataRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CoverPresenter(var mView: CoverContact.CoverView) : CoverContact.CoverPrensenter {
    val mRemoteDataRepository: RemoteDataRepository by lazy {
        RemoteDataRepository()
    }

    override fun start() {

        mView.showLoading()
        mRemoteDataRepository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(mView.autoDispose())
            .subscribe({ it ->
                mView.showContent()
            }, {
                mView.showError(it)
            })
    }

}
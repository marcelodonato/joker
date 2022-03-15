package com.marcelodonato.joker.presentation


import com.marcelodonato.joker.data.CategoryRemoteDataSource
import com.marcelodonato.joker.data.ListCategoryCallback
import com.marcelodonato.joker.model.Category
import com.marcelodonato.joker.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
) : ListCategoryCallback {

    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {
        val categories = response.map { Category(it, 0xFFFF0000) }
        view.showCategories(categories)
    }

    override fun onError(response: String) {
        view.showFailure(response)

    }

    override fun onComplete() {
        view.hideProgress()
    }
}
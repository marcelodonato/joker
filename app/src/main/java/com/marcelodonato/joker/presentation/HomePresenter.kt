package com.marcelodonato.joker.presentation


import android.graphics.Color
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
        val start = 40
        val end = 190
        val dif = (end - start ) / response.size

        val categories = response.mapIndexed{ index, s ->
            val hsv = floatArrayOf(
                start + (dif * index).toFloat(),
                100.0f,
                100.0f,
            )



            Category(s, Color.HSVToColor(hsv).toLong() )
        }
        view.showCategories(categories)
    }

    override fun onError(response: String) {
        view.showFailure(response)

    }

    override fun onComplete() {
        view.hideProgress()
    }
}
package org.buffer.android.boilerplate.ui.browse

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import org.buffer.android.boilerplate.presentation.browse.BrowseBufferoosContract
import org.buffer.android.boilerplate.presentation.model.BufferooView
import org.buffer.android.boilerplate.ui.R
import org.buffer.android.boilerplate.ui.mapper.BufferooMapper
import javax.inject.Inject

class BrowseActivity: AppCompatActivity(), BrowseBufferoosContract.View {

    @Inject lateinit var onboardingPresenter: BrowseBufferoosContract.Presenter
    @Inject lateinit var browseAdapter: BrowseAdapter
    @Inject lateinit var mapper: BufferooMapper

    override fun setPresenter(presenter: BrowseBufferoosContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun hideProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun showProgress() {
        progress.visibility = View.GONE
    }

    override fun showBufferoos(bufferoos: List<BufferooView>) {
        browseAdapter.bufferoos = bufferoos.map { mapper.mapToViewModel(it) }
        browseAdapter.notifyDataSetChanged()
        recycler_browse.visibility = View.VISIBLE
    }

    override fun hideBufferoos() {
        recycler_browse.visibility = View.VISIBLE
    }

    override fun showErrorState() {
    }

    override fun hideErrorState() {
    }

    override fun showEmptyState() {
    }

    override fun hideEmptyState() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        AndroidInjection.inject(this)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        onboardingPresenter.start()
    }

    private fun setupBrowseRecycler() {
        recycler_browse.layoutManager = LinearLayoutManager(this)
        recycler_browse.adapter = browseAdapter
    }

}
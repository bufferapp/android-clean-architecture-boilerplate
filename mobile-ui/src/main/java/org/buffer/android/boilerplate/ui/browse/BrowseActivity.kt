package org.buffer.android.boilerplate.ui.browse

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import org.buffer.android.boilerplate.presentation.browse.BrowseBufferoosViewModel
import org.buffer.android.boilerplate.presentation.browse.BrowseBufferoosViewModelFactory
import org.buffer.android.boilerplate.presentation.data.ResourceState
import org.buffer.android.boilerplate.presentation.data.Resource
import org.buffer.android.boilerplate.presentation.model.BufferooView
import org.buffer.android.boilerplate.ui.R
import org.buffer.android.boilerplate.ui.mapper.BufferooMapper
import org.buffer.android.boilerplate.ui.widget.empty.EmptyListener
import org.buffer.android.boilerplate.ui.widget.error.ErrorListener
import javax.inject.Inject

class BrowseActivity: AppCompatActivity() {

    @Inject lateinit var browseAdapter: BrowseAdapter
    @Inject lateinit var mapper: BufferooMapper
    @Inject lateinit var viewModelFactory: BrowseBufferoosViewModelFactory
    private lateinit var browseBufferoosViewModel: BrowseBufferoosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        AndroidInjection.inject(this)

        browseBufferoosViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(BrowseBufferoosViewModel::class.java)

        setupBrowseRecycler()
        setupViewListeners()
    }

    override fun onStart() {
        super.onStart()
        browseBufferoosViewModel.getBufferoos().observe(this,
                Observer<Resource<List<BufferooView>>> {
                    if (it != null) this.handleDataState(it.status, it.data, it.message) })
    }

    private fun setupBrowseRecycler() {
        recycler_browse.layoutManager = LinearLayoutManager(this)
        recycler_browse.adapter = browseAdapter
    }

    private fun handleDataState(resourceState: ResourceState, data: List<BufferooView>?,
                                message: String?) {
        when (resourceState) {
            ResourceState.LOADING -> setupScreenForLoadingState()
            ResourceState.SUCCESS -> setupScreenForSuccess(data)
            ResourceState.ERROR -> setupScreenForError(message)
        }
    }

    private fun setupScreenForLoadingState() {
        progress.visibility = View.VISIBLE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.GONE
    }

    private fun setupScreenForSuccess(data: List<BufferooView>?) {
        view_error.visibility = View.GONE
        progress.visibility = View.GONE
        if (data!= null && data.isNotEmpty()) {
            updateListView(data)
            recycler_browse.visibility = View.VISIBLE
        } else {
            view_empty.visibility = View.VISIBLE
        }
    }

    private fun updateListView(bufferoos: List<BufferooView>) {
        browseAdapter.bufferoos = bufferoos.map { mapper.mapToViewModel(it) }
        browseAdapter.notifyDataSetChanged()
    }

    private fun setupScreenForError(message: String?) {
        progress.visibility = View.GONE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.VISIBLE
    }

    private fun setupViewListeners() {
        view_empty.emptyListener = emptyListener
        view_error.errorListener = errorListener
    }

    private val emptyListener = object : EmptyListener {
        override fun onCheckAgainClicked() {
            browseBufferoosViewModel.fetchBufferoos()
        }
    }

    private val errorListener = object : ErrorListener {
        override fun onTryAgainClicked() {
            browseBufferoosViewModel.fetchBufferoos()
        }
    }

}
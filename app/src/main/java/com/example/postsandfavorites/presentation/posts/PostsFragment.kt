package com.example.postsandfavorites.presentation.posts

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsandfavorites.R
import com.example.postsandfavorites.core.extentions.gone
import com.example.postsandfavorites.core.extentions.observe
import com.example.postsandfavorites.core.extentions.show
import com.example.postsandfavorites.entites.posts.PostsResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : Fragment() {

    val viewModel: PostsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_posts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.screenState, ::onScreenStateChange)
        viewModel.getPosts()
        loading_progress.gone()
    }

    private fun onScreenStateChange(states: PostsStates){
        when (states) {
            is PostsStates.Loading -> showLoadingState()
            is PostsStates.LocalSuccess -> bindLocalSuccessState(states.data)
            is PostsStates.RemoteSuccess -> bindSuccessState(states.data)
            is PostsStates.Error -> showErrorSnackBar()
        }
    }

    private fun showErrorSnackBar(message: String = getString(R.string.error_message)){
        Snackbar.make(parent_layout ,message ,Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(requireContext(),R.color.red))
            .show()
    }

    private fun showLoadingState() {
        loading_progress.show()
        post_recycler.gone()
    }

    private fun hideLoadingState() {
        loading_progress.gone()
        post_recycler.show()
    }

    private fun bindLocalSuccessState(list: List<PostsResponse>) {
        hideLoadingState()
        showErrorSnackBar(getString(R.string.no_internet))
        with(post_recycler) {
            adapter = PostsAdapter(list ,::clickAction)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun bindSuccessState(list: List<PostsResponse>) {
        hideLoadingState()
        with(post_recycler) {
            adapter = PostsAdapter(list ,::clickAction)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun clickAction(id: Int) {
        val action = PostsFragmentDirections.toDetailsScreen(id)
        findNavController().navigate(action)

    }
}
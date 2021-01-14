package com.example.postsandfavorites.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsandfavorites.R
import com.example.postsandfavorites.core.extentions.gone
import com.example.postsandfavorites.core.extentions.observe
import com.example.postsandfavorites.core.extentions.show
import com.example.postsandfavorites.entites.comments.Comment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_comments.*
import kotlinx.android.synthetic.main.fragment_comments.loading_progress
import kotlinx.android.synthetic.main.fragment_comments.parent_layout
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentsFragment : Fragment() {

    val viewModel: CommentsViewModel by viewModel()

    private val args: CommentsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_comments, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.screenState, ::onScreenStateChange)
        arguments?.let {
            viewModel.getPosts(postId = args.postId)
        }
    }

    private fun onScreenStateChange(states: CommentsStates){
        when (states) {
            is CommentsStates.Loading -> showLoadingState()
            is CommentsStates.Success -> bindSuccessState(states.data)
            is CommentsStates.Error -> showErrorSnackBar()
        }
    }

    private fun showErrorSnackBar(){
        Snackbar.make(parent_layout ,getString(R.string.error_message) , Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(requireContext(),R.color.red))
            .show()
    }

    private fun showLoadingState() {
        loading_progress.show()
        comments_recycler.gone()
    }

    private fun hideLoadingState() {
        loading_progress.gone()
        comments_recycler.show()
    }

    private fun bindSuccessState(list: List<Comment>) {
        hideLoadingState()
        with(comments_recycler) {
            adapter = CommentAdapter(list)
            layoutManager = LinearLayoutManager(context)
        }
    }
}
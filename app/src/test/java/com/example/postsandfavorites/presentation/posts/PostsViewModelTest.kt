package com.example.postsandfavorites.presentation.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.postsandfavorites.core.dataloading.RemoteData
import com.example.postsandfavorites.core.extentions.RxCommonsTest
import com.example.postsandfavorites.domain.posts.repository.PostsRepository
import com.example.postsandfavorites.entites.posts.PostsResponse
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PostsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: PostsRepository

    private lateinit var viewModel: PostsViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = PostsViewModel(postsRepository = repository, rxCommons = RxCommonsTest())
    }

    @Test
    fun `getPosts() when getPost first Then post Loading state`() {

        `when`(repository.getPosts()).thenReturn(Single.never())

        viewModel.getPosts()

        //assert
        Assertions.assertEquals(PostsStates.Loading, viewModel.screenState.value)
    }

    @Test
    fun `getPosts() when getPost success Then post Success state`() {

        val posts = listOf(
            PostsResponse(0 ,0 ,"" ,""),
            PostsResponse(0 ,0 ,"" ,""),
            PostsResponse(0 ,0 ,"" ,"")
        )

        `when`(repository.getPosts()).thenReturn(Single.just(RemoteData(data = posts ,null)))

        viewModel.getPosts()

        //assert
        Assertions.assertEquals(PostsStates.RemoteSuccess(posts), viewModel.screenState.value)
    }

    @Test
    fun `getPosts() when getPost fails Then post error state`() {

        `when`(repository.getPosts()).thenReturn(Single.error(Throwable()))

        viewModel.getPosts()

        //assert
        Assertions.assertEquals(PostsStates.Error, viewModel.screenState.value)
    }
}
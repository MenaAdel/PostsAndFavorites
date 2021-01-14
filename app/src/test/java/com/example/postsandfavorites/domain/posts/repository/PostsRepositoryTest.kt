package com.example.postsandfavorites.domain.posts.repository

import com.example.postsandfavorites.core.database.dao.PostsDao
import com.example.postsandfavorites.domain.posts.datasource.remote.PostsGateWay
import com.example.postsandfavorites.entites.comments.Comment
import com.example.postsandfavorites.entites.posts.PostsResponse
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PostsRepositoryTest {
    private lateinit var repository: PostsRepository

    @Mock
    private lateinit var gateWay: PostsGateWay

    @Mock
    private lateinit var dao: PostsDao

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = PostsRepository(gateWay ,dao)
    }

    @Test
    fun `getPosts() when get posts success then return list of posts`(){
        //arrange
        val posts = listOf(
            PostsResponse(0 ,0 ,"" ,""),
            PostsResponse(0 ,0 ,"" ,""),
            PostsResponse(0 ,0 ,"" ,"")
        )
        var invoke = false

        dao = object :PostsDao {
            override fun savePosts(list: List<PostsResponse>) {
            }

            override fun loadPosts(): List<PostsResponse> {
                invoke = false
                return posts
            }

        }

        gateWay = object : PostsGateWay {
            override fun getPosts(): Single<List<PostsResponse>> {
                invoke = true
                return Single.just(posts)
            }

            override fun getPostComments(id: Int): Single<List<Comment>> {
                invoke = false
                return Single.error(Throwable())
            }

        }

        repository = PostsRepository(gateWay ,dao)

        repository.getPosts().test().dispose()

        assertTrue(invoke)
    }

    @Test
    fun `getPosts() when get posts error then return list of posts from room`(){
        //arrange
        val posts = listOf(
            PostsResponse(0 ,0 ,"" ,""),
            PostsResponse(0 ,0 ,"" ,""),
            PostsResponse(0 ,0 ,"" ,"")
        )
        var invoke = false

        dao = object :PostsDao {
            override fun savePosts(list: List<PostsResponse>) {
            }

            override fun loadPosts(): List<PostsResponse> {
                invoke = true
                return posts
            }

        }

        gateWay = object : PostsGateWay {
            override fun getPosts(): Single<List<PostsResponse>> {
                invoke = false
                return Single.error(Throwable())
            }

            override fun getPostComments(id: Int): Single<List<Comment>> {
                invoke = false
                return Single.error(Throwable())
            }

        }

        dao = object :PostsDao {
            override fun savePosts(list: List<PostsResponse>) {
            }

            override fun loadPosts(): List<PostsResponse> {
                invoke = true
                return posts
            }

        }

        repository = PostsRepository(gateWay ,dao)

        repository.getPosts().test().dispose()

        assertTrue(invoke)
    }

    @Test
    fun `getPosts() when get posts error from network and room then throw error`(){
        //arrange
        val posts = listOf(
            PostsResponse(0 ,0 ,"" ,""),
            PostsResponse(0 ,0 ,"" ,""),
            PostsResponse(0 ,0 ,"" ,"")
        )

        gateWay = object : PostsGateWay {
            override fun getPosts(): Single<List<PostsResponse>> {
                return Single.error(Throwable())
            }

            override fun getPostComments(id: Int): Single<List<Comment>> {
                return Single.error(Throwable())
            }

        }
        repository = PostsRepository(gateWay)

        val testObserver = repository.getPosts().test()

        testObserver.assertError(Throwable::class.java).dispose()
    }
}
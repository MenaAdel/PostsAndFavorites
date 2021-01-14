package com.example.postsandfavorites.domain.comment.repository

import com.example.postsandfavorites.domain.posts.datasource.remote.PostsGateWay
import com.example.postsandfavorites.entites.comments.Comment
import com.example.postsandfavorites.entites.posts.PostsResponse
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Test

class CommentRepositoryTest {

    @Test
    fun `getComments() When get comments success Then return list of comments`() {

        val postId = 2
        val comments = listOf(
            Comment(0 ,0 ,"" ,"" ,""),
            Comment(0 ,0 ,"" ,"" ,""),
            Comment(0 ,0 ,"" ,"" ,"")
        )

        val gateWay: PostsGateWay = object :PostsGateWay {
            override fun getPosts(): Single<List<PostsResponse>> = Single.never()

            override fun getPostComments(id: Int): Single<List<Comment>> {
                return Single.just(comments)
            }
        }

        val repository = CommentRepository(gateWay)

        //act
        val result = repository.getComments(postId).blockingGet()

        //assert
        Assert.assertEquals(comments, result)

    }

    @Test
    fun `getComments() When get comments error Then return throw error`() {

        val postId = 2

        val gateWay: PostsGateWay = object :PostsGateWay {
            override fun getPosts(): Single<List<PostsResponse>> = Single.never()

            override fun getPostComments(id: Int): Single<List<Comment>> {
                return Single.error(Throwable())
            }
        }

        val repository = CommentRepository(gateWay)

        val testObserver = repository.getComments(postId).test()

        testObserver.assertError(Throwable::class.java).dispose()

    }
}
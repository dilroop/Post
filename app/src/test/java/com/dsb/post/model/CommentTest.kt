package com.dsb.post.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CommentTest {
    private val comment = Comment(
        postId = 1,
        id = 0,
        name = "John Doe",
        email = "John.Doe@gmail.com",
        body = "This is an example of a comment"
    )

    private val badComment = Comment(
        postId = 1,
        id = 0,
        name = "",
        email = "",
        body = ""
    )

    @Test
    fun `getUserDetails - given good sample - return correct user details`() {
        val userDetails = comment.getUserDetails()
        assertThat(userDetails).isEqualTo("John (John.Doe@gmail.com)")
    }

    @Test
    fun `getUserDetails - given just email - return correct user details`() {
        val userDetails = comment.copy(name = "").getUserDetails()
        assertThat(userDetails).isEqualTo("John.Doe@gmail.com")
    }

    @Test
    fun `getUserDetails - given just name - return correct user details`() {
        val userDetails = comment.copy(email = "").getUserDetails()
        assertThat(userDetails).isEqualTo("John")
    }

    @Test
    fun `getUserDetails - given bad comment - return correct user details`() {
        val userDetails = badComment.getUserDetails()
        assertThat(userDetails).isEqualTo("")
    }
}
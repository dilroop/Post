package com.dsb.post.model

val commentSample = Comment(
    postId = 1,
    id = 0,
    name = "John Doe",
    email = "John.Doe@gmail.com",
    body = "This is an example of a comment"
)

val postSample = Post(id = 0, title = "Title", body = "this is the post body", userId = 1)
val userSample = User(id = 1, name = "John Doe", username = "john", email = "John.Doe@gmail.com")

val postWithUserSample = PostWithUser (post = postSample, user = userSample)
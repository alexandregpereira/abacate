package br.alexandregpereira.abacate.domain.models

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

val images = listOf(
    "https://i.picsum.photos/id/171/300/300.jpg?hmac=hEdWdS7PgEYliDZkkie5BXb5e69BDAf2jnyt4feIiWg",
    "https://i.picsum.photos/id/296/300/300.jpg?hmac=4r97lpYsrbx490hajkGt-RCnPk_e3JyP9PxFNNWiwzc",
    "https://i.picsum.photos/id/729/300/300.jpg?hmac=9UgqNGlL_rVMOLG_D40dQeKOjjk5bHfnW2RGLe6pJHU",
    "https://i.picsum.photos/id/419/300/300.jpg?hmac=feJH-n-jCmdB8adpjQSsWDMqVW4mRTKi4QgnEvpkbqs",
    "https://i.picsum.photos/id/403/300/300.jpg?hmac=ETYNjSCnfOVZJFZYzDYZ-PoMk6tjIoqoxzPj4kRrcd0",
    "https://i.picsum.photos/id/722/300/300.jpg?hmac=9W3nCepg3HA4qJrDeIB4ugk5PmSRTM4Z4RRXlGfsMhI",
    "https://i.picsum.photos/id/3/300/300.jpg?hmac=RT2JK6MzdIgNIWoIj61uPcz8aOSOi3lu2vhnwOxs7lY",
    "https://i.picsum.photos/id/704/300/300.jpg?hmac=xm4vkCgyqSsOZV7BId7MrPh6ayEtCCSVKxddUe1M3KU",
)

data class Post(
    val title: String = LoremIpsum((2..8).random()).values.first(),
    val imageUrls: List<String> = (0..(0..15).random()).map { images.random() },
    val message: String = LoremIpsum((2..25).random()).values.first()
)
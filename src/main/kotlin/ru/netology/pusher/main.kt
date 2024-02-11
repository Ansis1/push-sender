package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .setDatabaseUrl(dbUrl)
        .build()

    FirebaseApp.initializeApp(options)
    var message = Message.builder()
        .putData("action", "LIKE")
        .putData(
            "content", """{
          "userId": 1,
          "userName": 1201,
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent()
        )
        .setToken(token)
        .build()



    FirebaseMessaging.getInstance().send(message)

    message = Message.builder()
        .putData("action", "NEWPOST")
        .putData(
            "post", """{  
                "published":"",
                "content":"Hello its new post. Sended by FCM ${System.currentTimeMillis()}",
                "likedCnt":  1024,
                "sharedCnt": 0,
                "lookedCnt": 0,
                "title":"Hello post /n First push post",
                "video":"https://youtu.be/fdfdfdfdfdf"
        }""".trimIndent()
        )
        .setToken(token)
        .build()


    FirebaseMessaging.getInstance().send(message)
}

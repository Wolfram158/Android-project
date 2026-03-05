package ru.vk.project

import android.content.Context

sealed interface MainIntent {
    class OpenSecondActivityIntent(val text: String, val context: Context) : MainIntent

    class CallFriendIntent(val tel: String, val context: Context) : MainIntent

    class ShareTextIntent(val text: String, val context: Context) : MainIntent
}
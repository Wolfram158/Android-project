package ru.vk.project

import android.content.Context

sealed interface MainAction {
    class OpenSecondActivityAction(val text: String, val context: Context) : MainAction

    class CallFriendAction(val tel: String, val context: Context) : MainAction

    class ShareTextAction(val text: String, val context: Context) : MainAction
}
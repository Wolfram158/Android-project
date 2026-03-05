package ru.vk.project

import android.content.Intent

class MainModel : MainIntentHandler {
    override fun dispatchIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.CallFriendIntent -> {
                handleCallFriendIntent(intent)
            }

            is MainIntent.OpenSecondActivityIntent -> {
                handleOpenSecondActivityIntent(intent)
            }

            is MainIntent.ShareTextIntent -> {
                handleShareTextIntent(intent)
            }
        }
    }

    private fun handleCallFriendIntent(intent: MainIntent.CallFriendIntent) {
        Intent(Intent.ACTION_DIAL).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            setData(intent.tel.fromTelToUri())
            intent.context.startActivity(this)
        }
    }

    private fun handleOpenSecondActivityIntent(intent: MainIntent.OpenSecondActivityIntent) {
        Intent(intent.context, SecondActivity::class.java).apply {
            type = "text/plain"
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(Intent.EXTRA_TEXT, intent.text)
            intent.context.startActivity(this)
        }
    }

    private fun handleShareTextIntent(intent: MainIntent.ShareTextIntent) {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(Intent.EXTRA_TEXT, intent.text)
            intent.context.startActivity(this)
        }
    }

}
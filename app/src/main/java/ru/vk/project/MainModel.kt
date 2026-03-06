package ru.vk.project

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.telephony.PhoneNumberUtils
import android.widget.Toast

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
            if (!PhoneNumberUtils.isGlobalPhoneNumber(intent.tel)) {
                Toast.makeText(
                    intent.context, intent.context.getString(R.string.incorrect_phone_number),
                    Toast.LENGTH_LONG
                ).show()
                return
            }
            setData(intent.tel.fromTelToUri())
            withActivityNotFoundCatching(intent.context) {
                intent.context.startActivity(this)
            }
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
            withActivityNotFoundCatching(intent.context) {
                intent.context.startActivity(this)
            }
        }
    }

    private inline fun withActivityNotFoundCatching(
        context: Context,
        crossinline tryAction: () -> Unit
    ) {
        try {
            tryAction()
        } catch (_: ActivityNotFoundException) {
            Toast
                .makeText(
                    context,
                    context.getString(R.string.activity_not_found),
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }
}
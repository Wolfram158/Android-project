package ru.vk.project

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.telephony.PhoneNumberUtils
import android.widget.Toast

class MainModel : MainActionHandler {
    override fun dispatchAction(action: MainAction) {
        when (action) {
            is MainAction.CallFriendAction -> {
                handleCallFriendAction(action)
            }

            is MainAction.OpenSecondActivityAction -> {
                handleOpenSecondActivityAction(action)
            }

            is MainAction.ShareTextAction -> {
                handleShareTextAction(action)
            }
        }
    }

    private fun handleCallFriendAction(action: MainAction.CallFriendAction) {
        if (!PhoneNumberUtils.isGlobalPhoneNumber(action.tel)) {
            Toast.makeText(
                action.context, action.context.getString(R.string.incorrect_phone_number),
                Toast.LENGTH_LONG
            ).show()
            return
        }
        Intent(Intent.ACTION_DIAL).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            setData(action.tel.fromTelToUri())
            withActivityNotFoundCatching(action.context) {
                action.context.startActivity(this)
            }
        }
    }

    private fun handleOpenSecondActivityAction(action: MainAction.OpenSecondActivityAction) {
        Intent(action.context, SecondActivity::class.java).apply {
            type = "text/plain"
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(Intent.EXTRA_TEXT, action.text)
            action.context.startActivity(this)
        }
    }

    private fun handleShareTextAction(action: MainAction.ShareTextAction) {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(Intent.EXTRA_TEXT, action.text)
            withActivityNotFoundCatching(action.context) {
                action.context.startActivity(this)
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
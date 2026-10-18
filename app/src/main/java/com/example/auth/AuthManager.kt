package com.example.auth

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.example.R
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthManager(
  private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {
  val currentUser: FirebaseUser?
    get() = auth.currentUser

  val authStateFlow: Flow<FirebaseUser?> = callbackFlow {
    val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
      trySend(firebaseAuth.currentUser)
    }
    auth.addAuthStateListener(listener)
    awaitClose { auth.removeAuthStateListener(listener) }
  }

  suspend fun signInWithGoogle(context: Context): Result<FirebaseUser> {
    return try {
      val webClientId = context.getString(R.string.default_web_client_id)
      val credentialManager = CredentialManager.create(context)
      val googleIdOption = GetSignInWithGoogleOption.Builder(webClientId).build()
      val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

      val response = credentialManager.getCredential(context = context, request = request)
      val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(response.credential.data)
      val idToken = googleIdTokenCredential.idToken

      val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
      val authResult = auth.signInWithCredential(firebaseCredential).await()
      val user = authResult.user
      if (user != null) {
        Result.success(user)
      } else {
        Result.failure(IllegalStateException("प्रमाणीकरण विफल रहा। कृपया पुनः प्रयास करें।"))
      }
    } catch (e: GetCredentialCancellationException) {
      Log.w("AuthManager", "Google Sign-in was cancelled by user: ${e.message}")
      Result.failure(e)
    } catch (e: Exception) {
      Log.e("AuthManager", "Google Sign-in failed", e)
      Result.failure(e)
    }
  }

  fun signOut() {
    auth.signOut()
  }
}

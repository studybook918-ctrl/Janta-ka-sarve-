package com.example

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.example.data.AppScreen
import com.example.ui.ConfirmVoteDialog
import com.example.ui.EvmScreen
import com.example.ui.GoogleSignInDialog
import com.example.ui.HomeScreen
import com.example.ui.ResultsScreen
import com.example.ui.SurveyViewModel
import com.example.ui.theme.JanataKiRaiTheme
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {

    private val viewModel: SurveyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            JanataKiRaiTheme {
                MainAppContent(
                    viewModel = viewModel,
                    onTriggerGoogleSignIn = { onComplete ->
                        performGoogleSignIn(onComplete)
                    }
                )
            }
        }
    }

    private fun performGoogleSignIn(onSuccess: () -> Unit) {
        val credentialManager = CredentialManager.create(this)
        val serverClientId = try {
            getString(R.string.default_web_client_id)
        } catch (e: Exception) {
            "1046444150810-t1ju0rh54ia0qf7sbkasjaecpm58mmak.apps.googleusercontent.com"
        }

        val googleIdOption = GetSignInWithGoogleOption.Builder(serverClientId)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = this@MainActivity
                )
                val credential = result.credential
                if (credential is CustomCredential &&
                    credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                ) {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    val authCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(authCredential).await()
                    viewModel.refreshUser()
                    Toast.makeText(this@MainActivity, "सफलतापूर्वक लॉगिन हो गया!", Toast.LENGTH_SHORT).show()
                    onSuccess()
                }
            } catch (e: GetCredentialCancellationException) {
                Log.w("MainActivity", "User cancelled Google Sign-in: ${e.message}")
            } catch (e: Exception) {
                Log.e("MainActivity", "Sign-in failed", e)
                Toast.makeText(this@MainActivity, "लॉगिन असफल: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun MainAppContent(
    viewModel: SurveyViewModel,
    onTriggerGoogleSignIn: (onComplete: () -> Unit) -> Unit
) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val selectedDistrict by viewModel.selectedDistrict.collectAsState()
    val selectedConstituency by viewModel.selectedConstituency.collectAsState()
    val constituencies by viewModel.constituencies.collectAsState()
    val candidates by viewModel.candidates.collectAsState()
    val results by viewModel.results.collectAsState()
    val isVoting by viewModel.isVoting.collectAsState()
    val pendingCandidate by viewModel.pendingCandidate.collectAsState()
    val statusMessage by viewModel.statusMessage.collectAsState()
    val hasAlreadyVoted by viewModel.hasAlreadyVoted.collectAsState()

    var showSignInDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(statusMessage) {
        statusMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearStatusMessage()
        }
    }

    // Android System Back Button Handling
    BackHandler(enabled = currentScreen !is AppScreen.Home) {
        viewModel.navigateBack()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val screen = currentScreen) {
                is AppScreen.Home -> {
                    HomeScreen(
                        selectedDistrict = selectedDistrict,
                        selectedConstituency = selectedConstituency,
                        constituencies = constituencies,
                        onSelectDistrict = { viewModel.selectDistrict(it) },
                        onSelectConstituency = { viewModel.selectConstituency(it) },
                        onProceed = { viewModel.proceedToEvm() }
                    )
                }
                is AppScreen.Evm -> {
                    EvmScreen(
                        district = screen.district,
                        constituency = screen.constituency,
                        candidates = candidates,
                        hasAlreadyVoted = hasAlreadyVoted,
                        onCandidateClick = { viewModel.onCandidateClick(it) },
                        onViewResults = { viewModel.loadResults(screen.constituency) },
                        onBack = { viewModel.navigateHome() }
                    )
                }
                is AppScreen.Results -> {
                    ResultsScreen(
                        district = screen.district,
                        constituency = screen.constituency,
                        results = results,
                        chosenCandidateId = screen.chosenCandidateId,
                        onGoHome = { viewModel.navigateHome() }
                    )
                }
            }

            // Confirm Vote Dialog
            pendingCandidate?.let { candidate ->
                ConfirmVoteDialog(
                    candidate = candidate,
                    isVoting = isVoting,
                    onConfirm = {
                        viewModel.submitPendingVote(
                            onRequireSignIn = {
                                showSignInDialog = true
                            }
                        )
                    },
                    onDismiss = { viewModel.dismissConfirmation() }
                )
            }

            // Google Sign-In Required Dialog
            if (showSignInDialog) {
                GoogleSignInDialog(
                    onSignInClick = {
                        showSignInDialog = false
                        onTriggerGoogleSignIn {
                            // Retry voting after successful sign-in
                            viewModel.submitPendingVote(onRequireSignIn = {})
                        }
                    },
                    onDismiss = { showSignInDialog = false }
                )
            }
        }
    }
}

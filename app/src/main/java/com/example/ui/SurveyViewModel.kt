package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppScreen
import com.example.data.Candidate
import com.example.data.Constituency
import com.example.data.District
import com.example.data.SurveyRepository
import com.example.data.SurveyResultItem
import com.example.data.UPData
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SurveyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SurveyRepository(application.applicationContext)

    private val _currentScreen = MutableStateFlow<AppScreen>(AppScreen.Home)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _selectedDistrict = MutableStateFlow<District?>(null)
    val selectedDistrict: StateFlow<District?> = _selectedDistrict.asStateFlow()

    private val _selectedConstituency = MutableStateFlow<Constituency?>(null)
    val selectedConstituency: StateFlow<Constituency?> = _selectedConstituency.asStateFlow()

    private val _constituencies = MutableStateFlow<List<Constituency>>(emptyList())
    val constituencies: StateFlow<List<Constituency>> = _constituencies.asStateFlow()

    private val _candidates = MutableStateFlow<List<Candidate>>(emptyList())
    val candidates: StateFlow<List<Candidate>> = _candidates.asStateFlow()

    private val _results = MutableStateFlow<List<SurveyResultItem>>(emptyList())
    val results: StateFlow<List<SurveyResultItem>> = _results.asStateFlow()

    private val _currentUser = MutableStateFlow<FirebaseUser?>(repository.currentUser)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser.asStateFlow()

    private val _isVoting = MutableStateFlow(false)
    val isVoting: StateFlow<Boolean> = _isVoting.asStateFlow()

    private val _pendingCandidate = MutableStateFlow<Candidate?>(null)
    val pendingCandidate: StateFlow<Candidate?> = _pendingCandidate.asStateFlow()

    private val _statusMessage = MutableStateFlow<String?>(null)
    val statusMessage: StateFlow<String?> = _statusMessage.asStateFlow()

    private val _hasAlreadyVoted = MutableStateFlow(false)
    val hasAlreadyVoted: StateFlow<Boolean> = _hasAlreadyVoted.asStateFlow()

    fun selectDistrict(district: District) {
        _selectedDistrict.value = district
        val list = UPData.getConstituenciesForDistrict(district)
        _constituencies.value = list
        _selectedConstituency.value = null
    }

    fun selectConstituency(constituency: Constituency) {
        _selectedConstituency.value = constituency
        _candidates.value = UPData.getCandidatesForConstituency(constituency)
    }

    fun proceedToEvm() {
        val dist = _selectedDistrict.value ?: return
        val const = _selectedConstituency.value ?: return
        _candidates.value = UPData.getCandidatesForConstituency(const)

        viewModelScope.launch {
            _hasAlreadyVoted.value = repository.hasUserVoted(const.id)
            _currentScreen.value = AppScreen.Evm(dist, const)
        }
    }

    fun onCandidateClick(candidate: Candidate) {
        _pendingCandidate.value = candidate
    }

    fun dismissConfirmation() {
        _pendingCandidate.value = null
    }

    fun submitPendingVote(onRequireSignIn: () -> Unit) {
        val candidate = _pendingCandidate.value ?: return
        val constituency = _selectedConstituency.value ?: return
        val user = repository.currentUser

        if (user == null) {
            onRequireSignIn()
            return
        }

        viewModelScope.launch {
            _isVoting.value = true
            val res = repository.submitVote(constituency.id, candidate.id)
            _isVoting.value = false
            _pendingCandidate.value = null

            if (res.isSuccess) {
                _statusMessage.value = "आपकी राय सफलतापूर्वक दर्ज कर ली गई है!"
                loadResults(constituency, candidate.id)
            } else {
                _statusMessage.value = res.exceptionOrNull()?.message ?: "वोट दर्ज करने में त्रुटि हुई"
            }
        }
    }

    fun loadResults(constituency: Constituency, chosenCandidateId: String? = null) {
        val dist = _selectedDistrict.value ?: return
        viewModelScope.launch {
            val list = repository.getSurveyResults(constituency, _candidates.value)
            _results.value = list
            _currentScreen.value = AppScreen.Results(dist, constituency, chosenCandidateId)
        }
    }

    fun navigateHome() {
        _currentScreen.value = AppScreen.Home
        _pendingCandidate.value = null
        _statusMessage.value = null
    }

    fun navigateBack(): Boolean {
        return when (val screen = _currentScreen.value) {
            is AppScreen.Home -> false
            is AppScreen.Evm -> {
                _currentScreen.value = AppScreen.Home
                true
            }
            is AppScreen.Results -> {
                _currentScreen.value = AppScreen.Home
                true
            }
        }
    }

    fun refreshUser() {
        _currentUser.value = repository.currentUser
    }

    fun clearStatusMessage() {
        _statusMessage.value = null
    }
}

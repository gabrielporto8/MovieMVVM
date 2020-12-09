package com.sample.android.moviemvvm.ui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.moviemvvm.domain.Person
import com.sample.android.moviemvvm.network.PersonApi
import com.sample.android.moviemvvm.ui.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class PersonViewModel(api: PersonApi, personId: Any) : BaseViewModel() {

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person>
        get() = _person

    private val _knownAs = MutableLiveData<String>()
    val knownAs: LiveData<String>
        get() = _knownAs

    init {
        composeObservable { api.person(personId) }
                .subscribe({ person ->
                    _person.postValue(person)
                    _knownAs.postValue(person.alsoKnowAs.joinToString())
                }
                ) { throwable -> Timber.e(throwable) }.also { compositeDisposable.add(it) }
    }

    /**
     * Factory for constructing PersonViewModel with parameter
     */
    class Factory @Inject constructor(
            private val api: PersonApi,
            val person: PersonWrapper
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PersonViewModel(api, person.credit.id) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
package com.example.myapplication.presentation.presenters

import com.example.myapplication.CatsApp
import com.example.myapplication.di.modules.FavouritesModule
import com.example.myapplication.di.modules.MainModule
import com.example.myapplication.domain.interactors.FavouritesInteractor
import com.example.myapplication.presentation.views.favourites.FavouritesView
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class FavoritesPresenter : BasePresenter<FavouritesView>() {

    @Inject
    lateinit var favouritesInteractor: FavouritesInteractor

    init {
        CatsApp.appComponent?.addMainComponent(MainModule())?.addFavouritesComponent(FavouritesModule())?.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadFavourites()
    }

    fun loadFavourites() {
        viewState.showProgressBar()
        add(
            favouritesInteractor.loadFavourites()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.hideProgressBar() }
                .subscribe({ viewState.onSuccessGetFavourites(it) }, { viewState.onError() })
        )
    }
}
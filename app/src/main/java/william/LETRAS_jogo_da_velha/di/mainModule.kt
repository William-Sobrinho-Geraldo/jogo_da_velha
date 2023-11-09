package william.LETRAS_jogo_da_velha.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import william.LETRAS_jogo_da_velha.data.AppDatabase
import william.LETRAS_jogo_da_velha.data.Repository
import william.LETRAS_jogo_da_velha.viewModels.MainActivityViewModel

val mainModule = module {

    single { AppDatabase.getDatabase(androidContext()).jogadoresDao() }
    single { Repository(jogadoresDao = get()) }

    single { MainActivityViewModel(repository = get()) }
}
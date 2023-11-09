package william.LETRAS_jogo_da_velha.activities

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import william.LETRAS_jogo_da_velha.di.mainModule

class MyAplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyAplication)
            modules(mainModule)
        }
    }

}
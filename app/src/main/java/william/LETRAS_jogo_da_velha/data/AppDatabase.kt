package william.LETRAS_jogo_da_velha.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [JogadoresModel::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jogadoresDao(): JogadoresDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    Log.d("AppDatabase", "Creating new instance")
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "DB"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                    Log.d("AppDatabase", "Instance created")
                }
            }
            return INSTANCE!!
        }
    }
}



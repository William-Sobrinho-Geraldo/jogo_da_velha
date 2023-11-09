package william.LETRAS_jogo_da_velha.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [JogadoresModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jogadoresDao (): JogadoresDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "DB"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
            }
            return INSTANCE!!
        }
    }
}
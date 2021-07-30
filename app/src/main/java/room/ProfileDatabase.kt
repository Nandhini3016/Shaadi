package room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], exportSchema = false, version = 1)
abstract class ProfileDatabase : RoomDatabase() {
    abstract  fun profileDao() : ProfileDao
    companion object {
        private val  databaseInstance : ProfileDatabase? = null
        @Synchronized
        fun getDatabaseInstance(context : Context) : ProfileDatabase {
            return  if(databaseInstance == null)
            {
                Room.databaseBuilder(context, ProfileDatabase::class.java, "profile_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            else
                databaseInstance!!
        }
    }
}
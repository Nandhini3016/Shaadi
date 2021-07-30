package room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProfileDao {

    @Insert
    fun insert(profile: Profile)

    @Query("Select * from Profile")
    fun getProfiles() : List<Profile>


    @Query("UPDATE Profile SET statusAvailabilty = :statusAvailabilty, status = :status WHERE name =:name")
    fun updateStatus(statusAvailabilty : Boolean, status : String, name : String)
}
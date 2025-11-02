package com.example.lab_4.data.database

import android.util.Log
import com.example.lab_4.domain.Chiuit
import com.example.lab_4.domain.ChiuitRepository

class ChiuitDbStore(private val appDatabase: AppDatabase) : ChiuitRepository {

    override fun getAll(): List<Chiuit> {
        return appDatabase.chiuitDao().getAll().map { it.toDomainModel() }
    }

    override fun addChiuit(chiuit: Chiuit) {
        // TODO 2: Add the new chiuit by invoking the DAO; make sure to use the designated mapper.
        try {
            appDatabase.chiuitDao().insert(chiuit.toDbModel())
        } catch (e: Exception) {
            Log.e("ChiuitDbStore", "Error inserting chiuit: ${e.message}")
        }
    }

    override fun removeChiuit(chiuit: Chiuit) {
        // TODO 5: Remove the chiuit by invoking the DAO; make sure to use the designated mapper.
        try {
            appDatabase.chiuitDao().delete(chiuit.toDbModel())
        } catch (e: Exception) {
            Log.e("ChiuitDbStore", "Error deteleting chiuit: ${e.message}")
        }
    }


    private fun Chiuit.toDbModel() = ChiuitEntity(timestamp, description)

    private fun ChiuitEntity.toDomainModel() = Chiuit(timestamp, description)

}
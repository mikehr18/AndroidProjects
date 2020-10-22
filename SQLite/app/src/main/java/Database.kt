import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context:Context) :
    SQLiteOpenHelper(context,DATABASE_NAME, null,DATABASE_VERSION){

    companion object{
        const val DATABASE_NAME="Users.db"
        const val DATABASE_VERSION=1
    }


    override fun onCreate(db: SQLiteDatabase) {
    println("ANTES DE CREAR LA TABLA")
        db.execSQL("CREATE TABLE users("+
                        "id INTEGER PRIMARY KEY,"+
                "user TEXT,"+
                "password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
         println("NO NECESARIO")
    }


}


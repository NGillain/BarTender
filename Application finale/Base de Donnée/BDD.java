package com.example.thomas.blocnote;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;

public class BDD {

    private static final int VERSION_BDD = 5;
    private static final String NOM_BDD = "bartender.db";

    private SQLiteDatabase bdd;

    private DataBaseHandler handler;

    public BDD(Context context) {
        //On créer la BDD et sa table
        handler = new DataBaseHandler(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open() {
        //on ouvre la BDD en écriture
        bdd = handler.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD() {
        return bdd;
    }

    /*public long insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put(PSEUDO, user.getPseudo());
        values.put(PASSWORD, user.getPassword());
        return bdd.insert(TABLE_USERS, null, values);
    }

    public int updateUser(int id, User user) {
        ContentValues values = new ContentValues();
        values.put(PSEUDO, user.getPseudo());
        values.put(PASSWORD, user.getPassword());
        return bdd.update(TABLE_USERS, values, ID + " = " + id, null);
    }

    public int removeUserWithID(int id) {
        return bdd.delete(TABLE_USERS, ID + " = " + id, null);
    }

    public User getUserWithPseudo(String pseudo) {
        Cursor c = bdd.query(TABLE_USERS, new String[]{ID, PSEUDO, PASSWORD}, PSEUDO + " LIKE \"" + pseudo + "\"", null, null, null, null);
        return cursorToUser(c);
    }

    public User getUserWithId(int id) {
        Cursor c = bdd.rawQuery("select "+ ID + ", "  + PSEUDO + ", "
                + PASSWORD + " from " + TABLE_USERS + " where id = ?", new String[] {Integer.toString(id)});
        return cursorToUser(c);
    }

    private User cursorToUser(Cursor c) {
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        User user = new User();
        user.setId(c.getInt(N_ID));
        user.setPseudo(c.getString(N_PSEUDO));
        user.setPassword(c.getString(N_PASSWORD));
        c.close();

        return user;
    }*/

    public String[] getAllAllergies()
    {
        Cursor c = bdd.rawQuery("select " + DataBaseHandler.Allergene + " from " + DataBaseHandler.TABLE_ALLERGENE + " group by " + DataBaseHandler.Allergene,
                null);
        String[] s = new String[c.getCount()];
        int i = 0;

        while(c.moveToNext())
        {
            s[i] = c.getString(0);
            i++;
        }
        return s;
    }

    public String[] getAllBoissons()
    {
        Cursor c = bdd.rawQuery("select Nom from Boisson", null);
        String[] s = new String[c.getCount()];
        int i = 0;

        while(c.moveToNext())
        {
            s[i] = c.getString(0);
            i++;
        }
        return s;
    }
}

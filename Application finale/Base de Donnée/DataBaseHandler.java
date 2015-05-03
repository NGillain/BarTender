package com.example.thomas.blocnote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataBaseHandler extends SQLiteOpenHelper {

    // USERS
    public static final String TABLE_USER = "Utilisateur";
    public static final String Login = "Login";
    public static final String MotDePasse = "MotDePasse";
    public static final String Status = "Status";
    public static final String Sexe = "Sexe";
    public static final String Religion = "Religion";
    public static final String Age = "Age";
    public static final String Nom = "Nom";
    public static final String CREATE_USER = "CREATE TABLE " + TABLE_USER + "( "+ Login + " char(32) not null, " + MotDePasse +
            " char(32) not null, " + Status + " char(16) not null DEFAULT 'Client', " + Sexe +
            " char(1), " + Religion + " char(32), " + Age + " int(3), " + Nom + " Char(64),Primary key ("+Login+"));";
    //BOISSON
    public static final String TABLE_BOISSON= "Boisson";
    public static final String PrixAchat = "PrixAchat";
    public static final String PrixVente = "PrixVente";
    public static final String Description = "Description";
    public static final String Photo = "Photo";
    public static final String Type = "Type";
    public static final String Stock = "Stock";
    public static final String Seuil = "Seuil";
    public static final String Max = "Max";
    public static final String CREATE_BOISSON = " CREATE TABLE " + TABLE_BOISSON + " (" + Nom + " char(32) PRIMARY KEY NOT NULL DEFAULT (null), " + PrixAchat +
            " int NOT NULL DEFAULT (null), " + PrixVente + " int NOT NULL DEFAULT (null), " + Description + " char(256) DEFAULT (null), " + Photo  + " char(32) " +
            "DEFAULT (null), " + Type + " char(16) NOT NULL DEFAULT (null), " +
            Stock + " int NOT NULL DEFAULT (null), " + Seuil + " int NOT NULL DEFAULT (null), " + Max + " int NOT NULL DEFAULT (null));";

    //ALLERGENES
    public static final String TABLE_ALLERGENE = "Allergene";
    public static final String Allergene = "Allergene";
    public static final String Boisson = "Boisson";
    public static final String CREATE_ALLERGENE = "CREATE TABLE " + TABLE_ALLERGENE + " (" + Boisson + " char(32) NOT NULL  DEFAULT (null), " +
            Allergene + " char(32) NOT NULL DEFAULT (null));";

    //ALLERGIES
    public static final String TABLE_ALLERGIE = "Allergie";
    public static final String CREATE_ALLERGIE = "CREATE TABLE "+ TABLE_ALLERGIE +
            " (" + Login + " char(32) not null REFERENCES Utilisateur(login), " + Allergene + " char(32) not null);";

    //ADDITION
    public static final String TABLE_ADDITION = "Addition";
    public static final String AddNum = "AddNum";
    public static final String Date = "Date";
    public static final String ServeurLogin = "ServeurLogin";
    public static final String ClientLogin = "ClientLogin";
    public static final String NumTable = "NumTable";
    public static final String CREATE_ADDITION = "CREATE TABLE " + TABLE_ADDITION + " ("+ AddNum + " int(9) not null," +
            Date + " DATETIME not null, " + ServeurLogin + " char(32) not null REFERENCES Utilisateur(login)" +
            ", " + ClientLogin + " char(32) not null REFERENCES Utilisateur(Login), " + NumTable + " int(2) not null, primary key ("+AddNum+"));";

    //CONSOMMATIONS
    public static final String TABLE_CONSO = "Consommation";
    public static final String Quantite = "Quantite";
    public static final String CREATE_CONSO = "CREATE TABLE " + TABLE_CONSO + " (" + AddNum +
            " int(9) NOT NULL ," + Boisson + " char(32) NOT NULL ," + Quantite + " int(2) NOT NULL  DEFAULT (null) );";

    //Images
    public static final String TABLE_IMAGE = "Image";
    public static final String Logo = "Logo";
    public static final String CREATE_IMAGE = "CREATE TABLE " + TABLE_IMAGE + " (" + Type + " int NOT NULL  DEFAULT (null) ," + Logo + " char(32) NOT NULL  DEFAULT (null) );";


    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_BOISSON);
        db.execSQL(CREATE_ALLERGENE);
        db.execSQL(CREATE_ADDITION);
        db.execSQL(CREATE_ALLERGIE);
        db.execSQL(CREATE_CONSO);
        db.execSQL(CREATE_IMAGE);

        String s;
        try {
            BufferedReader b = new BufferedReader(new InputStreamReader(MainActivity.getContext().getAssets().open("commandes.sql")));
            while((s = b.readLine()) != null)
            {
                db.execSQL(s);
            }
            b.close();
        }catch (IOException e) {
            throw new ArithmeticException("impossible d'ouvrir le fichier ! commandes");
        }
        try {
            BufferedReader b = new BufferedReader(new InputStreamReader(MainActivity.getContext().getAssets().open("allergenes.sql")));
            while((s = b.readLine()) != null)
            {
                db.execSQL(s);
            }
            b.close();
        }catch (IOException e) {
            throw new ArithmeticException("impossible d'ouvrir le fichier ! allergenes");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOISSON + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALLERGENE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDITION + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALLERGIE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONSO + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE + ";");
        onCreate(db);
    }

}
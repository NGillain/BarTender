package be.uclouvain.lsinf1225.bartender.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.util.ArrayList;

import be.uclouvain.lsinf1225.bartender.MySQLiteHelper;


/**
 * Représente un utilisateur et gère l'authentification de celui-ci à l'application.
 *
 * Cette classe représente un utilisateur de l'application. Elle utilise pour cela la base de
 * données par l'intermédiaire du MySQLiteHelper.
 *
 * Les méthodes statiques permettent de récupérer la liste des utilisateurs, récupérer l'utilisateur
 * actuellement connecté (s'il y en a un) et de déconnecter l'utilisateur.
 *
 * @author Damien Mercier
 * @version 1
 */
public class User {

    private static final String DB_COLUMN_ID = "U_ID";
    private static final String DB_COLUMN_NAME = "Login";
    private static final String DB_COLUMN_PASSWORD = "MotDePasse";
    private static final String DB_TABLE = "Utilisateur";
    /**
     * Identifiant unique de l'utilisateur courant. Correspond à _id dans la base de données.
     */
    private final int id;
    /**
     * Nom (unique) de l'utilisateur courant. Correspond à u_nom dans la base de données.
     */
    private String name;
    /**
     * Mot de passe de l'utilisateur courant. Correspond à u_password dans la base de données.
     */
    private String password;

    /**
     * Constructeur de l'utilisateur. Initialise une instance de l'utilisateur présent dans la base
     * de données.
     *
     * @note Ce constructeur est privé (donc utilisable uniquement depuis cette classe). Cela permet
     * d'éviter d'avoir deux instances différentes d'un même utilisateur.
     */
    private User(int uId, String uName, String uPassword) {

        this.id = uId;
        this.name = uName;
        this.password = uPassword;
        User.userSparseArray.put(uId, this);
    }

    /**
     * Fournit l'id de l'utilisateur courant.
     */
    public int getId() {
        return id;
    }

    /**
     * Fournit le nom de l'utilisateur courant.
     */
    public String getName() {
        return name;
    }

    /**
     * Connecte l'utilisateur courant.
     *
     * @param passwordToTry le mot de passe entré.
     *
     * @return Vrai (true) si l'utilisateur à l'autorisation de se connecter, false sinon.
     */
    public boolean login(String passwordToTry) {
        if (this.password.equals(passwordToTry)) {
            // Si le mot de passe est correct, modification de l'utilisateur connecté.
            User.connectedUser = this;
            return true;
        }
        return false;
    }

    public static boolean signin(String login, String password, String confirmpassword) {

        if (password.equals(confirmpassword)) {
            // Récupération de la base de données.
            SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

            // Définition des valeurs pour le nouvel élément dans la table "boisson".
            ContentValues cv = new ContentValues();
            cv.put(DB_COLUMN_NAME, login);
            cv.put(DB_COLUMN_PASSWORD, password);

            // Ajout à la base de données (table boisson).
            int user_id = (int) db.insert(DB_TABLE, null, cv);

            if (user_id == -1) {
                return false; // En cas d'erreur d'ajout, on retourne false directement.
            }
            cv.clear();
            return true;
        }
        return false;
    }

    /**
     * Fournit une représentation textuelle de l'utilisateur courant. (Ici le nom)
     *
     * @note Cette méthode est utilisée par l'adaptateur ArrayAdapter afin d'afficher la liste des
     * utilisateurs. (Voir LoginActivity).
     */
    public String toString() {
        return getName();
    }

    /******************************************************************************
     * Partie static de la classe.
     ******************************************************************************/

    /**
     * Contient les instances déjà existantes des utilisateurs afin d'éviter de créer deux instances
     * du même utilisateur.
     */
    private static SparseArray<User> userSparseArray = new SparseArray<User>();

    /**
     * Utilisateur actuellement connecté à l'application. Correspond à null si aucun utilisateur
     * n'est connecté.
     */
    private static User connectedUser = null;

    /**
     * Fournit l'utilisateur actuellement connecté.
     */
    public static User getConnectedUser() {
        return User.connectedUser;
    }


    /**
     * Déconnecte l'utilisateur actuellement connecté à l'application.
     */
    public static void logout() {
        User.connectedUser = null;
    }

    /**
     * Fournit la liste des utilisateurs.
     */
    public static ArrayList<User> getUtilisateurs() {
        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer
        String[] colonnes = {DB_COLUMN_ID, DB_COLUMN_NAME, DB_COLUMN_PASSWORD};

        // Requête de selection (SELECT)
        Cursor cursor = db.query(DB_TABLE, colonnes, null, null, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Initialisation la liste des utilisateurs.
        ArrayList<User> users = new ArrayList<User>();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int uId = cursor.getInt(0);
            String uNom = cursor.getString(1);
            String uPassword = cursor.getString(2);

            // Vérification pour savoir s'il y a déjà une instance de cet utilisateur.
            User user = User.userSparseArray.get(uId);
            if (user == null) {
                // Si pas encore d'instance, création d'une nouvelle instance.
                user = new User(uId, uNom, uPassword);
            }

            // Ajout de l'utilisateur à la liste.
            users.add(user);

            // Passe à la ligne suivante.
            cursor.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return users;
    }

}


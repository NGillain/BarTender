package be.uclouvain.lsinf1225.bartender.activity;

/**
 * Created by maximehanot on 29/04/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import be.uclouvain.lsinf1225.bartender.BarTenderApp;
import be.uclouvain.lsinf1225.bartender.R;
import be.uclouvain.lsinf1225.bartender.model.Boisson;
import be.uclouvain.lsinf1225.bartender.model.User;

/**
 * Gère l'écran d'ajout des utilisateurs à l'application.
 *
 * @author Damien Mercier
 * @version 1
 */
public class SigninActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add, menu);
        return true;
    }

    /**
     * Enregistre les données saisies par l'utilisateur dans un nouvel élément.
     *
     * Cette méthode va s'occuper de récupérer les différentes valeurs remplie par l'utilisateur.
     * Ensuite il va créer un nouvel élément de collection avec ces données.
     *
     * @pre Le champ nom ne doit pas être vide.
     * @post Soit un nouvel élément de collection a été créé dans la base de données et l'activité
     * est fermée soit un message d'erreur est affiché à l'utilisateur.
     */
    private void signin() {

        String login = getLogin();
        // Vérification de la présence d'un nom.
        if (login == null) {
            return;
        }

        String password = getPassword();
        String confirmpassword = getPassword();

        /* Création de l'élément */
        if (User.signin(login, password, confirmpassword)) {
            BarTenderApp.notifyLong(R.string.add_success_msg);
            finish(); // On termine l'activité d'ajout afin de retourner au menu principal.
        } else {
            BarTenderApp.notifyLong(R.string.add_error_on_create);
        }

    }

    private String getLogin() {
        /* Récupération du nom  */
        EditText nameEditText = (EditText) findViewById(R.id.add_name);
        String name = String.valueOf(nameEditText.getText());

        if (name.isEmpty()) {
            BarTenderApp.notifyShort(R.string.add_error_name_required);
            return null;
        }
        return name;
    }

    private String getPassword() {

        /* Récupération de la description */
        EditText descriptionEditText = (EditText) findViewById(R.id.add_description);
        return String.valueOf(descriptionEditText.getText());
    }
}

package be.uclouvain.lsinf1225.musicplayer.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.FileOutputStream;
import java.util.Date;

import be.uclouvain.lsinf1225.musicplayer.MusicPlayerApp;
import be.uclouvain.lsinf1225.musicplayer.R;


/**
 * Gère l'écran d'ajout d'un nouvel élément à la collection de l'utilisateur actuellement connecté.
 *
 * @author Damien Mercier
 * @version 1
 */
public class PlayerActivity extends Activity {

    /**
     * Code de requête pour les activités externes.
     *
     * @note On note les codes de requêtes dans des constantes pour faciliter la lecture et éviter
     * les confusions. Les nombres (int) donnés doivent être différents pour chaque type de requêtes
     * afin de pouvoir les distinguer (voir switch dans onActivityResult)
     */
    private static final int REQUEST_PICK_IMAGE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    /**
     * L'image actuellement choisie par l'utilisateur. Par défaut, aucune.
     */
    private Bitmap currentBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }


    /**
     * Initialise le menu.
     *
     * Cette méthode permet d'indiquer à Android qu'il faut charger un menu. Ici nous chargeons le
     * menu res/menu/add.xml qui contient les boutons "Enregistrer" et "Annuler"
     *
     * Voir http://d.android.com/guide/topics/ui/menus.html et
     * http://d.android.com/reference/android/app/Activity.html#onCreateOptionsMenu%28android.view.Menu%29
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add, menu);
        return true;
    }


    /**
     * Effectue une action lors de la sélection (typiquement lorsqu'on clique) d'un élément du
     * menu.
     *
     * @param item Élément sélectionné par l'utilisateur.
     *
     * @return Retourne vrai (true) si on a pris en compte la sélection de l'élément du menu. Sinon
     * retourne faux (false) si rien n'a été fait.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                return true; //On retourne true pour indiquer que l'action a été gérée.

            case R.id.action_quit:
                finish(); // On termine l'activité d'ajout sans enregistrer les données saisies.
                return true; //On retourne true pour indiquer que l'action a été gérée.

        }
        return false;
    }


    /**
     * Lance l'activité de sélection d'une photo dans l'appareil Android.
     *
     * Cette méthode va demander à Android d'afficher à l'utilisateur un utilitaire permettant de
     * choisir une image. Cette méthode est appelée lors d'un clic sur le bouton de sélection d'une
     * image grâce à l'attribut "onClick" défini dans le layout activity_add.xml
     *
     * @param view Vue sur laquelle le clic est effectué (paramètre obligatoire lorsqu'on utilise
     *             l'attribut onClick)
     */
    public void selectPicture(View view) {
        /**
         * Création d'une action implicite.
         * Lisez http://d.android.com/training/basics/intents/sending.html pour comprendre comment
         * envoyer l'utilisateur vers une autre application présente dans l'appareil android.
         *
         * Ici ACTION_PICK est l'action de sélection d'un élément dans les fichiers. La valeur de
         * retour étant l'élément choisi.
         */
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // Démarrage de l'activité en indiquant qu'un résultat est attendu, le code de la requête est
        // ici donné afin de pouvoir distinguer quel résultat revient dans la méthode onActivityResult
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    /**
     * Lance l'activité de prise d'une photo.
     *
     * Cette méthode va demander à Android d'afficher à l'utilisateur l'appareil photo afin de
     * prendre une nouvelle photo. Cette méthode est appelée lors d'un clic sur le bouton de prise
     * d'une photo grâce à l'attribut "onClick" défini dans le layout activity_add.xml
     *
     * @param view Vue sur laquelle le clic est effectué (paramètre obligatoire lorsqu'on utilise
     *             l'attribut onClick)
     */
    public void takePicture(View view) {

        /**
         * Création d'une action implicite.
         * Lisez http://d.android.com/training/basics/intents/sending.html pour comprendre comment
         * envoyer l'utilisateur vers une autre application présente dans l'appareil android.
         *
         * Ici ACTION_IMAGE_CAPTURE est l'action de prise d'une photo avec l'appareil photo.
         */
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Démarrage de l'activité en indiquant qu'un résultat est attendu, le code de la requête est
        // ici donné afin de pouvoir distinguer quel résultat revient dans la méthode onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    /**
     * Récupère la réponse donnée par une activité.
     *
     * Cette méthode est appelée pour récupérer la réponse des activités lancées avec la méthode
     * startActivityForResult. Dans notre cas : soit la sélection d'une photo dans les fichiers,
     * soit la prise d'une nouvelle photo avec l'appareil photo.
     *
     * Lisez http://d.android.com/training/basics/intents/result.html pour plus d'explications.
     *
     * @param requestCode Code de requête (donné en argument de startActivityForResult)
     * @param resultCode  Code indiquant le résultat
     * @param intent      Un Intent contenant les résultats de la requête.
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        Bitmap imageBitmap = null;

        // On regarde d'où provient le résultat et on traite le résultat en fonction.
        switch (requestCode) {
            case REQUEST_PICK_IMAGE:
                if (resultCode == RESULT_OK) { // Si l'utilisateur a sélectionné une image et validé.

                    // On récupère l'adresse de l'image.
                    Uri imageUri = intent.getData();
                    try {
                        // On charge l'image depuis le nom de fichier dans la variable.
                        imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    } catch (Exception e) {
                        // Les exceptions pouvant survenir sont :
                        // FileNotFoundException, IOException, OutOfMemoryError,...
                        e.printStackTrace();
                    }
                }
                break;
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) { // Si l'utilisateur a pris une photo et validé.
                    // On charge l'image dans la variable.
                    imageBitmap = (Bitmap) intent.getExtras().get("data");
                }
                break;
        }

        // Si on a récupérer une image
        if (imageBitmap != null) {
            // On la redimensionne afin qu'elle ne soit pas trop grande pour l'affichage (sinon
            // il est possible qu'elle ne s'affiche pas si elle est trop grande).
            int nh = (int) (imageBitmap.getHeight() * (300.0 / imageBitmap.getWidth()));
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageBitmap, 300, nh, true);

            if (scaledBitmap != null) {
                currentBitmap = scaledBitmap;
                ImageView mImageView = (ImageView) findViewById(R.id.add_picture_preview);
                //On affiche l'image dans la vue prévue à cet effet.
                mImageView.setImageBitmap(currentBitmap);
            }
        }

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
    private void save() {

        String name = getName();
        // Vérification de la présence d'un nom.
        if (name == null) {
            return;
        }

        String description = getDescription();
        float rating = getRating();
        String pictureFilename = getPicture();

        /* Création de l'élément */
        if (Song.create(name, description, rating, pictureFilename)) {
            MusicPlayerApp.notifyLong(R.string.add_success_msg);
            finish(); // On termine l'activité d'ajout afin de retourner au menu principal.
        } else {
            MusicPlayerApp.notifyLong(R.string.add_error_on_create);
        }

    }

    private String getName() {
        /* Récupération du nom  */
        EditText nameEditText = (EditText) findViewById(R.id.add_name);
        String name = String.valueOf(nameEditText.getText());

        if (name.isEmpty()) {
            MusicPlayerApp.notifyShort(R.string.add_error_name_required);
            return null;
        }
        return name;
    }

    private String getDescription() {

        /* Récupération de la description */
        EditText descriptionEditText = (EditText) findViewById(R.id.add_description);
        return String.valueOf(descriptionEditText.getText());
    }

    private float getRating() {
        /* Récupération de la note  */
        RatingBar ratingBar = (RatingBar) findViewById(R.id.add_rating);
        return ratingBar.getRating();
    }

    private String getPicture() {

        // Récupération de l'image
        FileOutputStream outputStream;

        // Le temps (en seconde depuis le 1 Jan 1970) est utilisé pour avoir un nom de fichier unique.
        String filename = new Date().getTime() + ".png";
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            currentBitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return filename;
    }
}

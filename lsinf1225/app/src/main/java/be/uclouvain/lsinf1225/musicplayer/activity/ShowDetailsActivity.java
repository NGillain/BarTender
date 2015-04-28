package be.uclouvain.lsinf1225.musicplayer.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import be.uclouvain.lsinf1225.musicplayer.MusicPlayerApp;
import be.uclouvain.lsinf1225.musicplayer.R;

/**
 * Gère l'affichage des détails d'un élément ainsi que la modification de la note de celui-ci.
 *
 * @author Damien Mercier
 * @version 1
 */
public class ShowDetailsActivity extends Activity implements RatingBar.OnRatingBarChangeListener {

    private Song currentSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        // Récupération de l'id de l'élément de collection ou si rien n'est trouvé, -1 est la valeur
        // par défaut.
        // Lire http://d.android.com/training/basics/firstapp/starting-activity.html#ReceiveIntent
        int id = getIntent().getIntExtra("s_id", -1);

        if (id == -1) {
            // Ne devrait jamais arriver.
            throw new RuntimeException("Aucun id d'élément n'a été spécifié.");
        }

        // Récupération de l'élément de collection.
        currentSong = be.uclouvain.lsinf1225.musicplayer.model.Song.get(id);

        // Complétition des différents champs avec les données de l'élément de collection.
        TextView title = (TextView) findViewById(R.id.show_details_title);
        title.setText(currentSong.gettitle());

        TextView artist = (TextView) findViewById(R.id.show_details_artist);
        artist.setText(currentSong.getartist());

        RatingBar rating = (RatingBar) findViewById(R.id.show_details_rating);
        rating.setRating(currentSong.getRating());

        // Indique que cette classe recevra les modifications de note (rating) grâce à la méthode
        // onRatingChanged.
        rating.setOnRatingBarChangeListener(this);

        // Récupération et affichage de l'image.
        // S'il n'y a pas d'image, l'emplacement prévu doit être masqué.
        Bitmap bitmap = currentSong.getfilename();
        if (bitmap != null) {
            ImageView filename = (ImageView) findViewById(R.id.show_details_filename);
            filename.setImageBitmap(bitmap);
        } else {
            View filenameLL = findViewById(R.id.show_details_filename_ll);
            // La visibilité GONE implique que l'élément ne prend aucune place (contrairement à INVISIBLE).
            filenameLL.setVisibility(View.GONE);
        }

    }

    /**
     * Enregistre les changements de la note (rating).
     *
     * @param ratingBar La RatingBar concernée (ici il n'y en a qu'une dont l'id est
     *                  show_details_rating).
     * @param rating    La valeur de la nouvelle note (rating).
     * @param fromUser  Indique si le changement de note (rating) est effectué par l'utilisateur ou
     *                  par le programme (par exemple par appel de la méthode
     *                  ratingBar.setRating(x)).
     */
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if (fromUser) {
            if (!currentSong.setRating(rating)) {
                // En cas d'erreur, il faut notifier l'utilisateur et afficher la valeur qui est
                // réellement enregistrée.
                MusicPlayerApp.notifyShort(R.string.show_details_rating_change_error);
                ratingBar.setRating(currentSong.getRating());
            }
        }
    }
}

package be.uclouvain.lsinf1225.collector.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import be.uclouvain.lsinf1225.collector.CollectorApp;
import be.uclouvain.lsinf1225.collector.R;
import be.uclouvain.lsinf1225.collector.model.CollectedItem;

/**
 * Gère l'affichage des détails d'un élément ainsi que la modification de la note de celui-ci.
 *
 * @author Damien Mercier
 * @version 1
 */
public class ShowDetailsActivity extends Activity implements RatingBar.OnRatingBarChangeListener {

    private CollectedItem currentCollectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        // Récupération de l'id de l'élément de collection ou si rien n'est trouvé, -1 est la valeur
        // par défaut.
        // Lire http://d.android.com/training/basics/firstapp/starting-activity.html#ReceiveIntent
        int id = getIntent().getIntExtra("ci_id", -1);

        if (id == -1) {
            // Ne devrait jamais arriver.
            throw new RuntimeException("Aucun id d'élément n'a été spécifié.");
        }

        // Récupération de l'élément de collection.
        currentCollectedItem = CollectedItem.get(id);

        // Complétition des différents champs avec les données de l'élément de collection.
        TextView name = (TextView) findViewById(R.id.show_details_name);
        name.setText(currentCollectedItem.getName());

        TextView description = (TextView) findViewById(R.id.show_details_description);
        description.setText(currentCollectedItem.getDescription());

        RatingBar rating = (RatingBar) findViewById(R.id.show_details_rating);
        rating.setRating(currentCollectedItem.getRating());

        // Indique que cette classe recevra les modifications de note (rating) grâce à la méthode
        // onRatingChanged.
        rating.setOnRatingBarChangeListener(this);

        // Récupération et affichage de l'image.
        // S'il n'y a pas d'image, l'emplacement prévu doit être masqué.
        Bitmap bitmap = currentCollectedItem.getPicture();
        if (bitmap != null) {
            ImageView picture = (ImageView) findViewById(R.id.show_details_picture);
            picture.setImageBitmap(bitmap);
        } else {
            View pictureLL = findViewById(R.id.show_details_picture_ll);
            // La visibilité GONE implique que l'élément ne prend aucune place (contrairement à INVISIBLE).
            pictureLL.setVisibility(View.GONE);
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
            if (!currentCollectedItem.setRating(rating)) {
                // En cas d'erreur, il faut notifier l'utilisateur et afficher la valeur qui est
                // réellement enregistrée.
                CollectorApp.notifyShort(R.string.show_details_rating_change_error);
                ratingBar.setRating(currentCollectedItem.getRating());
            }
        }
    }
}

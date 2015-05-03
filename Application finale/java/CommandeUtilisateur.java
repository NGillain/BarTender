package com.example.thomas.blocnote;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Jadin on 30/04/2015.
 */
public class CommandeUtilisateur extends ListActivity {

    private Button addBoisson;
    private Button cloture;
    Commande commande=new Commande();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commande_utilisateur);
        addBoisson = (Button) findViewById(R.id.addBoissonButton);

        addBoisson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CommandeUtilisateur.this, AddBoisson.class);
                CommandeUtilisateur.this.startActivity(intent);
            }
        });

        cloture = (Button) findViewById(R.id.clotureButton);

        cloture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCommandeinDB();//TODO rajouter cette fonction
                Intent intent = new Intent(CommandeUtilisateur.this, AddBoisson.class);
                CommandeUtilisateur.this.startActivity(intent);
            }
        });
        ArrayAdapter<BoissonQuantity> = new ArrayAdapter<BoissonQuantity>(CommandeUtilisateur.this,android.R.layout.simple_list_item_1);
        //ArrayList<Commande> commandes=getCommandeWithID(int ID);

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
        Intent intent = new Intent(this, ShowBoisson.class);
        startActivity(intent);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, AddBoisson.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}

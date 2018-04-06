package salasca_valerio.trustbet;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AdapterPari extends RecyclerView.Adapter<AdapterPari.ViewHolderSinglePari> {


    @Override
    public int getItemCount() {
        return AccueilActivity.mainUser.getParis().size();
    }

    @Override
    public ViewHolderSinglePari onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cell_pari, parent, false);
        return new ViewHolderSinglePari(view);
    }

    @Override
 
    public void onBindViewHolder(ViewHolderSinglePari holder, int position) {
        Pari pari = AccueilActivity.mainUser.getParis().get(position);
        holder.display(pari);
     }

    public class ViewHolderSinglePari extends RecyclerView.ViewHolder {

 
        private final TextView titre;
        private final TextView description;
        private final TextView montant;
        private final TextView number;
 
        private Pari currentPari;

        public ViewHolderSinglePari(final View itemView) {
            super(itemView);

 
            titre = itemView.findViewById(R.id.titre_pari_cell);
            number = itemView.findViewById(R.id.pari_number_cell);
            montant = itemView.findViewById(R.id.pari_montant_cell);
            description = itemView.findViewById(R.id.pari_description_cell);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   /*
                   que se passe t-il si on clique dessus ?
                    */
                }
            });
        }

        public void display(Pari pari) {
            currentPari = pari;
            titre.setText(pari.getTitre());
            number.setText("Pari " + pari.getId());
            montant.setText(pari.getMontant());
            description.setText(pari.getDescription());
         }


    }

}
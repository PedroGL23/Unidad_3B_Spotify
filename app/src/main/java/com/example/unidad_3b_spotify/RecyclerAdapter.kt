package layout

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad_3b_spotify.PlaylistName
import com.example.unidad_3b_spotify.R
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val cardTitles: List<PlaylistName>) : RecyclerView.Adapter<RecyclerAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.playlist_item_block, parent, false)
        return MainViewHolder(v)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = cardTitles.get(position)
        holder.bindItems(data)

        holder.itemView.setOnClickListener {

            Log.i("ha FUNCIONADO", data.nameOfPlaylist.toString())
        }
    }
    override fun getItemCount(): Int {
        return cardTitles.size
    }
    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {

        val cardImage: ImageView = itemView.findViewById(R.id.playlistImage)
        lateinit var playlistCover: String

        fun bindItems(data: PlaylistName) {
            //  Título de la playlist
            var titlePlaylist = v.findViewById<TextView>(R.id.playlistTitle)
            titlePlaylist.text = data.nameOfPlaylist

            //  Título que va dentro de la cardview
            var titleInsideCard = v.findViewById<TextView>(R.id.cardTitlePlaylist)
            titleInsideCard.text = data.nameOfPlaylist

            //  Imagen que se carga del archivo json.
            playlistCover = data.cover
            Picasso.get().load(playlistCover).into(cardImage)

            //  Número de seguidores asignados al azar entre 100.000 y 100.000.000
            var randomNumber = v.findViewById<TextView>(R.id.numberOfFollowers)
            randomNumber.text = (100000..100000000).random().toString()

            Log.i("cover", playlistCover.toString())

        }
    }
}
package br.com.fiap.ecosmart

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class HomeActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mediaPlayer = MediaPlayer.create(this, R.raw.btn_click)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        findViewById<ConstraintLayout>(R.id.homeLayout).startAnimation(fadeIn)

        val buttonMap = findViewById<Button>(R.id.btnPontosDeColeta)
        buttonMap.setOnClickListener {
            playClickSound()
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) // Animação de transição ao abrir MenuActivity
        }

        val buttonDicas = findViewById<Button>(R.id.btnDicas)
        buttonDicas.setOnClickListener {
            playClickSound()
            showDicasPopup()
        }
    }

    private fun playClickSound() {
        mediaPlayer.start()
    }

    private fun showDicasPopup() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.activity_popup_dicas, null)

        val scaleIn = AnimationUtils.loadAnimation(this, R.anim.scale_in)
        dialogView.startAnimation(scaleIn)

        val builder = AlertDialog.Builder(this)
            .setView(dialogView)

        val dialog = builder.create()
        dialog.show()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val buttonYoutube = dialogView.findViewById<Button>(R.id.btnYoutube)
        buttonYoutube.setOnClickListener {
            playClickSound()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=4OVW4SRYRp0"))
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}

package com.example.espacobemestar

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    val TAG = "FIREBASE_EMS"

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d(TAG,"Novo Token: $token")
        Prefs.setString("FB_TOKEN", token!!)
    }

    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {
        //super.onMessageReceived(mensagemRemota)
        Log.d (TAG,"onMessageReceived")

        if(mensagemRemota?.notification != null){
            val titulo = mensagemRemota?.notification?.title
            var corpo = mensagemRemota?.notification?.body
            Log.d(TAG,"Titulo:$titulo")
            Log.d(TAG,"Corpo:$corpo")

            if(mensagemRemota?.data.isNotEmpty()){
                val servicoId = mensagemRemota.data.get("servicoId")
                corpo = "$corpo($servicoId)"
            }

            val intent = Intent (this, ServicoActivity:: class.java)
            NotificationUtil.create(this, 1 , intent, titulo!!, corpo!!)
        }

    }
}
package sdk.chat.messenger.messages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*
import sdk.chat.messenger.NewMessageActivity
import com.example.workmate.R
import sdk.chat.messenger.models.ChatMessage
import sdk.chat.messenger.models.User

class ChatLogActivity : AppCompatActivity() {

    companion object{
        val TAG="ChatLog"
    }

    val adapter=GroupAdapter<ViewHolder>()
    var toUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        //allows to add objects inside the adapter
        recyclerview_chat_log.adapter= adapter

        toUser = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)

        supportActionBar?.title = toUser?.username

//        if (user != null) {
//            supportActionBar?.title = user.username
//        }

        //setupDummyData()

        listenForMessages()

        // send message on pressing the button
        send_button_chat_log.setOnClickListener {
            Log.d(TAG, "Attempt to send msg...")
            performSendMessage()

        }
    }
    //to listen for new incoming msges
    private fun listenForMessages(){
        val fromId= FirebaseAuth.getInstance().uid
        val toId= toUser?.uid
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")

        ref.addChildEventListener(object: ChildEventListener {

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)

                if (chatMessage != null) {
                    Log.d(TAG, chatMessage.text)

                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        val currentUser= LatestMessagesActivity.currentUser
                        adapter.add(ChatFromItem(chatMessage.text, currentUser!!))
                    } else {
                        val toUser=intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
                        adapter.add(ChatToItem(chatMessage.text, toUser!!))
                    }
                }

                recyclerview_chat_log.scrollToPosition(adapter.itemCount- 1)
            }
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

        })

    }

    // Send msges to firebase
    private fun performSendMessage()
    {
        val text = edittext_chat_log.text.toString()

        val fromId = FirebaseAuth.getInstance().uid
        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)

        val toId = user!!.uid



        if (fromId == null) return

        //get reference to firebase database
       // val reference = FirebaseDatabase.getInstance().getReference("/messages").push() //push() generates a new node automatically in the firebase database
        val reference = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()

        //to keep track of chat at receiver's end as well
        val toreference = FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()

        val chatMessage = ChatMessage(reference.key!!, text, fromId, toId, System.currentTimeMillis() / 1000)
        reference.setValue(chatMessage)
                .addOnSuccessListener {
                    Log.d(TAG, "Saved our chat message: ${reference.key}")
                    edittext_chat_log.text.clear() //to clear message from the keyboard after it is sent
                    recyclerview_chat_log.scrollToPosition(adapter.itemCount -1)// To scroll to the last msg automatically
                }
        toreference.setValue(chatMessage)

        // to keep track of latest msg on sender end
        val latestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId/$toId")
        latestMessageRef.setValue(chatMessage)

    // to keep track of latest msg on receiver end
        val latestMessageToRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$toId/$fromId")
        latestMessageToRef.setValue(chatMessage)
    }

}

//left row for the person sending you the message, right rows for your messages
class ChatFromItem(val text: String, val user: User): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
    viewHolder.itemView.textview_from_row.text= text

        //load sender img into the chat log imgview
        val uri=user.profileImageUrl
        val targetImageView= viewHolder.itemView.imageView_chat_from_row

        Picasso.get().load(uri).into(targetImageView)
    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }
}

class ChatToItem(val text: String, val user: User): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
    viewHolder.itemView.textview_to_row.text= text

        //load receiver img into the chat log imgview
        val uri=user.profileImageUrl
        val targetImageView= viewHolder.itemView.imageView_chat_to_row

        Picasso.get().load(uri).into(targetImageView)
    }


    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }
}

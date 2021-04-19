package com.example.workmate.messages

import LatestMessageRow
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.workmate.MainActivity
import com.example.workmate.R
import com.example.workmate.models.ChatMessage
import com.example.workmate.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_latest_messages.*
import kotlin.collections.HashMap

class LatestMessagesActivity : AppCompatActivity() {

    companion object{
        var currentUser: User? = null
        const val TAG="LatestMessages"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_messages)

        recyclerview_latest_messages.adapter=adapter
        recyclerview_latest_messages.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        // set item click listener on your adapter
        adapter.setOnItemClickListener { item, view ->
            Log.d(TAG, "123")
            val intent = Intent(this, ChatLogActivity::class.java)

            // we are missing the chat partner user
            val row = item as LatestMessageRow
            intent.putExtra(NewMessageActivity.USER_KEY, row.chatPartnerUser)
            startActivity(intent)
        }

        // setupDummyRows()
        listenForLatestMessages()

        fetchCurrentUser()

        verifyUserIsLoggedIn()
    }

    //using hash maps so that no new rows are added anytime a new message comes in from a user
    val latestMessagesMap = HashMap<String, ChatMessage>()

    //all old messages are removed and new messages are stored in the adapter
    private fun refreshRecyclerViewMessages() {
        adapter.clear()
        latestMessagesMap.values.forEach {
            adapter.add(LatestMessageRow(it))
        }
    }

    private fun listenForLatestMessages(){
        val fromId = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId")
        ref.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java) ?: return
                latestMessagesMap[p0.key!!] = chatMessage //key belongs to the receiver
                refreshRecyclerViewMessages()
            }

            // to modify the latest msg when a new msg comes
            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java) ?: return
                latestMessagesMap[p0.key!!] = chatMessage
                refreshRecyclerViewMessages()
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }
            override fun onChildRemoved(p0: DataSnapshot) {

            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    val adapter=GroupAdapter<ViewHolder>()

    //private fun setupDummyRows(){
//
//    adapter.add(LatestMessageRow)
//    adapter.add(LatestMessageRow)
//    adapter.add(LatestMessageRow)
//
//}
    private fun fetchCurrentUser(){
        var uid= FirebaseAuth.getInstance().uid
        val ref= FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                currentUser= p0.getValue(User::class.java)
                Log.d("LatestMessages", "Current User ${currentUser?.username}")
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
    //verifying that the user is logged in or not
    private fun verifyUserIsLoggedIn() {
        val uid = FirebaseAuth.getInstance().uid
        //when user is not registered
        if (uid == null) {
            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this,"Must be signed in",Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }

    // to perform some action on clicking "new messages" or "sign out"
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.menu_new_message -> {
                val intent = Intent(this, NewMessageActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
    //including buttons "sign out" and "new meassage" on the navigation bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu1, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
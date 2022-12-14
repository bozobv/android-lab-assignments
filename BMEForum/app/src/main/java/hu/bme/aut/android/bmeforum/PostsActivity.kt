package hu.bme.aut.android.bmeforum

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import hu.bme.aut.android.bmeforum.adapter.PostsAdapter
import hu.bme.aut.android.bmeforum.data.Post
import hu.bme.aut.android.bmeforum.databinding.ActivityPostsBinding

class PostsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPostsBinding
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setSupportActionBar(binding.appBarPosts.toolbar)

        binding.appBarPosts.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        postsAdapter = PostsAdapter(applicationContext)
        binding.appBarPosts.contentPosts.rvPosts.layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        binding.appBarPosts.contentPosts.rvPosts.adapter = postsAdapter

        binding.navView.setNavigationItemSelectedListener(this)

        binding.appBarPosts.fab.setOnClickListener {
            val createPostIntent = Intent(this, CreatePostActivity::class.java)
            startActivity(createPostIntent)
        }

        initPostsListener()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initPostsListener() {
        val db = Firebase.firestore
        db.collection("posts")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED -> postsAdapter.addPost(dc.document.toObject<Post>())
                        DocumentChange.Type.MODIFIED -> Toast.makeText(this, dc.document.data.toString(), Toast.LENGTH_SHORT).show()
                        DocumentChange.Type.REMOVED -> Toast.makeText(this, dc.document.data.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

}

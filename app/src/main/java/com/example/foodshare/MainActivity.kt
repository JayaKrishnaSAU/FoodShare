import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodshare.HomeActivity
import com.example.foodshare.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the user is already logged in
        val isLoggedIn = checkIfUserIsLoggedIn()

        // If the user is logged in, start the HomeActivity
        if (isLoggedIn) {
            startHomeActivity()
        } else {
            // If the user is not logged in, start the LoginActivity
            startLoginActivity()
        }

        // Finish the MainActivity so that the user cannot go back to it
        finish()
    }

    private fun checkIfUserIsLoggedIn(): Boolean {
        // Perform your logic here to check if the user is logged in
        // Return true if the user is logged in, false otherwise
        return false
    }

    private fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}

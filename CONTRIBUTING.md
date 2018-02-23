# Contributor Guidelines
Contributors to this project are very much welcome, but there are a few constraints we had to put to make the project more standardised and more beginner friendly.

If you are a beginner to Git Workflow and how to contribute to a git repository, then follow the below steps before you start coding. We assume that you have some knowledge about Android Studio, Java and how Android App Development workflow goes.

## Steps for Beginners
1.  **Fork** the repository before you clone it. This is important. If you clone it just like this, you don't have the privilege to push to this repository your committed changes. So, you must fork the repository before you clone.
 2. After **cloning** the **forked** repository on your computer, make a new branch. You can do so for this(also applicable to other repositories if you change the name, also just see the git reference documentations) repository by typing `git checkout -b <your-name>/<your-feature> master`. This will create a new branch based off on the branch `master` of our repository, and will give you freedom to create your new feature or fixing our code without messing the whole thing up.
 3. After you have created the branch and made some awesome changes in our code, you can submit a **Pull Request** to our repository to get your code pulled in our repository (See pull request template for more details).
 
 ## Code standards
 We follow the default Android Open Source Project code standards. Here's a snippet of a code from our MainActivity
 ```java
 public class MainActivity extends AppCompatActivity
         implements NavigationView.OnNavigationItemSelectedListener {
     FragmentManager fragmentManager;
     FragmentTransaction fragmentTransaction;
     ScanFragment scanFragment;
 
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         FirebaseCrash.log("MainActivity Created");
         Toolbar toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
     }
 }
 ```
 So yeah, that's all. Don't be afraid to fork it and use it. Have fun.
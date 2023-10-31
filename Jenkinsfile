pipeline {
  agent {
    label 'jenkins-ssh-agent'
  }

 stages {
         stage('Checkout') {
             steps {
                 //Checkout the repository from GitHub by Chris
                 //ssh credential has been set
                 def branchName = params.branchName

                 echo "User selected branch is ${branchName}"
                 git branch: ${branchName} , url: 'git@github.com:strider73/adventuretube_server.git'
             }
         }

         stage('Build with Gradle') {
             steps {
                 // Build the project with Gradle
                 sh './gradlew  clean build'
             }
         }

         stage('Restart Web Application') {
             steps {
                 // Restart the web application container!!!!
                 //Test jenkins  agasin !!!!!!
                 sh 'docker compose restart web'
             }
         }
     }
}
pipeline {
  agent {
    label 'ssh-docker-agent'
  }

 stages {
         stage('Checkout') {
             steps {
                 // Checkout the repository from GitHub
                 git url: 'https://github.com/strider73/adventuretube_server.git'
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
                 // Restart the web application container
                 sh 'docker-compose restart web'
             }
         }
     }
}
pipeline {
  agent {
    label 'ssh-docker-agent'
  }
  stages {
   stage('Checkout') {
            steps {
                 git credentialsId: 'jenkins-git-credential', url: 'https://github.com/strider73/adventuretube_server.git'
            }
        }
    stage('Build'){
            steps{
                sh './gradlew build'
            }
    }

     stage('Test'){
            steps{
                sh './gradlew test'
            }
    }

  }
}
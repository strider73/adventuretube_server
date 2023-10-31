pipeline {
    agent {
        label 'jenkins-ssh-agent'
    }



    stages {
        stage('Prepare and Checkout') {
            steps {
                script {
                    def branchName = params.branchName

                    echo "User selected branch is ${branchName}"
                }
            }
        }

        stage('Checkout') {
            steps {
                script {
                    def branchName = params.branchName
                    echo "User selected branch is ${branchName}"

                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: "*/${branchName}"]],
                        userRemoteConfigs: [[url: 'git@github.com:strider73/adventuretube_server.git']]
                    ])
                }
            }
        }

        stage('Build with Gradle') {
            steps {
                // Build the project with Gradle
                sh './gradlew clean build'
            }
        }

        stage('Restart Web Application') {
            steps {
                // Restart the web application container
                sh 'docker compose restart web'
            }
        }
    }
}

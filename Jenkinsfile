pipeline {
    agent {
        label 'jenkins-ssh-agent'
    }

    stages {



        stage('Build tool versions') {
              steps {
                // Print the Java version
                echo 'Java version: ' + sh(script: 'java -version', returnStdout: true).trim()

                // Print the Gradle version
                echo 'Gradle version: ' + sh(script: './gradlew --version', returnStdout: true).trim()

                // Print the Docker version
                echo 'Docker version: ' + sh(script: 'docker version --format "{{.Server.Version}}"', returnStdout: true).trim()

                // Print the Docker Compose version
                echo 'Docker Compose version: ' + sh(script: 'docker-compose version', returnStdout: true).trim()
               }
        }


        stage('Checkout') {
            steps {
                script {
                    def branchName = params.branchName.substring('refs/heads/'.length())
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

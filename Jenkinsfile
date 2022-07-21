pipeline {
    agent any
    tools {
        maven 'Maven 3.8.6'
    }
    stages {
        stage('build jar') {
            steps {
                echo "building jar file ..."
                sh 'mvn package'
            }
        }
        stage ('build image') {
            steps {
                echo "building image ..."
                withCredentials([
                    usernamePassword(
                        credentialsId: 'docker-hub',
                        username: 'docker-hub-username',
                        password: 'docker-hub-password'
                    )
                ]) {
                    sh 'docker build -t  clashia/java-maven-app:jma-2.0 .'
                    sh 'echo $docker-hub-password | docker login -u $docker-hub-username --password-stdin'
                    sh 'docker push clashia/java-maven-app:jma-2.0'
                }
            }
        }
        stage ('deploy') {
            steps {
                echo "deploying..."
            }
        }
    }
}
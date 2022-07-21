pipeline {
    agent any
    tools {
        maven {
            version "3.6.3"
        }
    }
    stages {
        stage('build') {
            steps {
                echo "building ..."
                sh "mvn --version"
            }
        }
        stage ('test') {
            steps {
                echo "testing..."
            }
        }
        stage ('deploy') {
            steps {
                echo "deploying..."
            }
        }
    }
}
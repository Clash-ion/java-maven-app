pipeline {
    agent any
    tools {
        maven
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